package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a mod a Person's is taking.
 * Guarantees: immutable; is valid as declared in {@link #isValidMod(String)}
 */
public class Mod {

    public static final String MESSAGE_CONSTRAINTS = "Mod codes must be in the form of 2-3 Capitalised characters, 4 "
            + "digits then an optional extra capitalised character.";

    /*
     * The first 2-3 characters of the module code must be capital letters,
     * followed by 4 digits, and an optional capital letter.
     */
    public static final String VALIDATION_REGEX = "^[A-Z]{2,3}[0-9]{4}[A-Z]?";

    public final String value;

    /**
     * Constructs an {@code Mod}.
     *
     * @param mod A valid mod.
     */
    public Mod(String mod) {
        requireNonNull(mod);
        checkArgument(isValidMod(mod), MESSAGE_CONSTRAINTS);
        value = mod;
    }

    /**
     * Returns true if a given string is a valid mod.
     */
    public static boolean isValidMod(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mod // instanceof handles nulls
                && value.equals(((Mod) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}