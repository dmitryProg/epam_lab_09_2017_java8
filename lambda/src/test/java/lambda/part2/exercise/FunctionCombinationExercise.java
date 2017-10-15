package lambda.part2.exercise;

import data.Person;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FunctionCombinationExercise {

    @Test
    public void personHasNotEmptyLastNameAndFirstName0() {
        // Person -> boolean
        Predicate<Person> validate = p -> !p.getFirstName().isEmpty() && !p.getLastName().isEmpty();
        assertTrue(validate.test(new Person("a", "b", 0)));
        assertFalse(validate.test(new Person("", "b", 0)));
        assertFalse(validate.test(new Person("a", "", 0)));
    }

    // negate1: (Person -> boolean) -> (Person -> boolean)
    private Predicate<Person> negate1(Predicate<Person> test) {
        return p -> !test.test(p);
    }

    // validateFirstNameAndLastName: (Person -> boolean, Person -> boolean) -> (Person -> boolean)
    private Predicate<Person> validateFirstNameAndLastName(Predicate<Person> t1, Predicate<Person> t2) {
        return p -> t1.test(p) && t2.test(p);
    }

    @Test
    public void personHasNotEmptyLastNameAndFirstName1() {
        Predicate<Person> hasEmptyFirstName = p -> p.getFirstName().isEmpty();
        Predicate<Person> hasEmptyLastName = p -> p.getLastName().isEmpty();

        Predicate<Person> validateFirstName = negate1(hasEmptyFirstName);
        Predicate<Person> validateLastName = negate1(hasEmptyLastName);

        Predicate<Person> validate = validateFirstNameAndLastName(validateFirstName, validateLastName);

        assertTrue(validate.test(new Person("a", "b", 0)));
        assertFalse(validate.test(new Person("", "b", 0)));
        assertFalse(validate.test(new Person("a", "", 0)));
    }

    // negate: (T -> boolean) -> (T -> boolean)
    private <T> Predicate<T> negate(Predicate<T> test) {
        return t -> !test.test(t);
    }

    // and: (T -> boolean, T -> boolean) -> (T -> boolean)
    private <T> Predicate<T> and(Predicate<T> t1, Predicate<T> t2) {
        return t -> t1.test(t) && t2.test(t);
    }

    @Test
    public void personHasNotEmptyLastNameAndFirstName2() {
        Predicate<Person> hasEmptyFirstName = p -> p.getFirstName().isEmpty();
        Predicate<Person> hasEmptyLastName = p -> p.getLastName().isEmpty();

        Predicate<Person> validateFirstName = negate(hasEmptyFirstName);
        Predicate<Person> validateLastName = negate(hasEmptyLastName);
        Predicate<Person> validate = and(validateFirstName, validateLastName);

        assertTrue(validate.test(new Person("a", "b", 0)));
        assertFalse(validate.test(new Person("", "b", 0)));
        assertFalse(validate.test(new Person("a", "", 0)));
    }

    @Test
    public void personHasNotEmptyLastNameAndFirstName3() {
        Predicate<Person> hasEmptyFirstName = p -> p.getFirstName().isEmpty();
        Predicate<Person> hasEmptyLastName = p -> p.getLastName().isEmpty();

        Predicate<Person> validateFirstName = hasEmptyFirstName.negate();
        Predicate<Person> validateLastName = hasEmptyLastName.negate();
        Predicate<Person> validate = validateFirstName.and(validateLastName);

        assertTrue(validate.test(new Person("a", "b", 0)));
        assertFalse(validate.test(new Person("", "b", 0)));
        assertFalse(validate.test(new Person("a", "", 0)));
    }
}
