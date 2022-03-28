package seedu.address.logic.commands;

import com.opencsv.exceptions.CsvValidationException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

public class ImportCsvCommand extends Command {
    public static final String COMMAND_WORD = "import-csv";

    private static final String MESSAGE_SUCCESS = "Opening app for bulk emailing";

    private static final String MESSAGE_USAGE = "";

    public static final String IMPORT_SUCCESSFUL = "Import of the student list successful";

    public static final String EXPECTED_HEADERS = "Name,Student Number,Email,Group";

    public static final String EXPECTED_HEADERS_MISSING = "";

    public static final String FILE_DOES_NOT_EXIST = "";

    public static final String NOT_A_CSV_FILE = "";

    public static final String UNEXPECTED_ERROR = "";

    public static final String NOTHING_NEW_TO_IMPORT = "";

    private final Path path;

    public ImportCsvCommand(Path path) {
        this.path = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> listToAddToModel = extractUniquePersonList(model);

        if (isNull(listToAddToModel)) {
            return new CommandResult(NOTHING_NEW_TO_IMPORT);
        }

        listToAddToModel.forEach(model::addPerson);

        return new CommandResult(IMPORT_SUCCESSFUL);
    }

    private List<Person> extractUniquePersonList(Model model) throws CommandException {
        boolean passingChecks = passingChecks();

        if (passingChecks) {
            try {
                List<Name> nameList;
                List<StudentNumber> studentNumberList;
                List<Email> emailList;
                List<Mod> modList = new ArrayList<>();
                List<Group> groupList = new ArrayList<>();

                nameList = CsvUtil.extractColumn("Name", this.path).stream().map(Name::new)
                                .collect(Collectors.toList());

                studentNumberList = CsvUtil.extractColumn("Student Number", this.path).stream()
                                .map(StudentNumber::new).collect(Collectors.toList());

                emailList = CsvUtil.extractColumn("Email", this.path).stream()
                                .map(Email::new).collect(Collectors.toList());

                CsvUtil.extractColumn("Group", this.path).forEach(s -> {
                    String[] splitModAndGroup = s.split(" ");
                    String group = splitModAndGroup[0].trim();
                    groupList.add(new Group(group));
                    String mod = splitModAndGroup[1].trim().substring(1).replace(")", "");
                    modList.add(new Mod(mod));
                });

                assert nameList.size() == studentNumberList.size();
                assert nameList.size() == emailList.size();
                assert nameList.size() == modList.size();
                assert nameList.size() == groupList.size();

                List<Person> toReturn = IntStream.range(0, emailList.size())
                        .mapToObj(i -> new Person(nameList.get(i), studentNumberList.get(i),
                                emailList.get(i), modList.get(i), groupList.get(i), null)).collect(Collectors.toList());

                return toReturn.stream().filter(person -> !model.hasPerson(person)).collect(Collectors.toList());

            } catch (FileNotFoundException e) {
                throw new CommandException(FILE_DOES_NOT_EXIST);
            } catch (CsvValidationException e) {
                throw new CommandException(NOT_A_CSV_FILE);
            } catch (IOException e) {
                throw new CommandException(UNEXPECTED_ERROR);
            }
        }

        return null;
    }


    private boolean passingChecks() throws CommandException {
        boolean fileConforms;

        try {
            fileConforms = CsvUtil.isTheFileConformingToHeaders(this.path, EXPECTED_HEADERS);
        } catch (FileNotFoundException e) {
            throw new CommandException(FILE_DOES_NOT_EXIST);
        } catch (CsvValidationException e) {
            throw new CommandException(NOT_A_CSV_FILE);
        } catch (IOException e) {
            throw new CommandException(UNEXPECTED_ERROR);
        }

        if (!fileConforms) {
            throw new CommandException(EXPECTED_HEADERS_MISSING);
        }

        return true;
    }

}
