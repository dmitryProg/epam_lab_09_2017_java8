package lambda.part2.exercise;

import data.Person;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ArrowNotationExercise {

    @Test
    public void getAge() {
        // Person -> Integer
        final Function<Person, Integer> getAge = Person::getAge;
        assertEquals(Integer.valueOf(33), getAge.apply(new Person("", "", 33)));
    }

    @Test
    public void compareAges() {
        BiPredicate<Person, Person> compareAges = (person, person2) -> person.getAge() == person2.getAge();
        assertEquals(true, compareAges.test(new Person("a", "b", 22), new Person("c", "d", 22)));
    }

    private String getFullName(Person person) {
        return person.getFirstName() + " " + person.getLastName();
    }

    private Person ageOfPersonWithTheLongestFullName(Person person, Person person2) {
        if (getFullName(person).length() > getFullName(person2).length()) {
            return person;
        } else {
            return person2;
        }
    }

    @Test
    public void getAgeOfPersonWithTheLongestFullName() {
        // Person -> String
        final Function<Person, String> getFullName = person -> person.getFirstName() + " " + person.getLastName();

        final BiFunction<Person, Person, Integer> ageOfPersonWithTheLongestFullName = (person, person2) -> {
            if (getFullName.apply(person).length() > getFullName.apply(person2).length()) {
                return person.getAge();
            }
            return person2.getAge();
        };

        assertEquals(
                Integer.valueOf(1),
                ageOfPersonWithTheLongestFullName.apply(
                        new Person("a", "b", 2),
                        new Person("aa", "b", 1)));
    }
}
