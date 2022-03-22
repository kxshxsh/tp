package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ContactList;
import seedu.address.model.person.Person;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withMod("CS2100").withEmail("alice@example.com")
            .withStudentNumber("A0246813G").withGroup("T01")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withMod("CS2040S")
            .withEmail("johnd@example.com").withStudentNumber("A0123456L").withGroup("T01")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withStudentNumber("A1234567M")
            .withEmail("heinz@example.com").withMod("MA1101R").withGroup("T01").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withStudentNumber("A1357902K")
            .withEmail("cornelia@example.com").withMod("MA1521").withGroup("T01").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withStudentNumber("A1234123A")
            .withEmail("werner@example.com").withMod("ACC1701X").withGroup("T01").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withStudentNumber("A5678567C")
            .withEmail("lydia@example.com").withMod("FIN2704X").withGroup("T01").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withStudentNumber("A7890789X")
            .withEmail("anna@example.com").withMod("CS3244").withGroup("T01").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withStudentNumber("A1001001L")
            .withEmail("stefan@example.com").withMod("CS2100").withGroup("T01").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withStudentNumber("A0000001S")
            .withEmail("hans@example.com").withMod("CS2100").withGroup("T01").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY =
            new PersonBuilder().withName(VALID_NAME_AMY).withStudentNumber(VALID_STUDENT_NUMBER_AMY)
            .withEmail(VALID_EMAIL_AMY).withMod(VALID_MOD_AMY)
            .withGroup(VALID_GROUP_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withStudentNumber(VALID_STUDENT_NUMBER_BOB)
            .withEmail(VALID_EMAIL_BOB).withMod(VALID_MOD_BOB)
            .withGroup(VALID_GROUP_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code ContactList} with all the typical persons.
     */
    public static ContactList getTypicalContactList() {
        ContactList ab = new ContactList();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
