package lambda.part1.exercise;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import data.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
@SuppressWarnings({"Guava", "Convert2Lambda", "Anonymous2MethodRef"})
public class Lambdas02Exercise {
    @Test
    public void sortPersonsByAge() {
        Person[] persons = {
                new Person("name 3", "lastName 3", 20),
                new Person("name 1", "lastName 2", 40),
                new Person("name 2", "lastName 1", 30)
        };
        Arrays.sort(persons, (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        assertArrayEquals(persons, new Person[]{
                new Person("name 3", "lastName 3", 20),
                new Person("name 2", "lastName 1", 30),
                new Person("name 1", "lastName 2", 40),
        });
    }

    @Test
    public void findFirstWithAge30() {
        List<Person> persons = ImmutableList.of(
                new Person("name 3", "lastName 3", 20),
                new Person("name 1", "lastName 2", 30),
                new Person("name 2", "lastName 1", 30)
        );
        Person person = null;

        Optional<Person> personOptional = FluentIterable.from(persons).firstMatch(p -> p.getAge() == 30);
        assertEquals(personOptional.get(), new Person("name 1", "lastName 2", 30));
    }
}
