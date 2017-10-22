package part2.exercise;

import data.Employee;
import data.JobHistoryEntry;
import data.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsExercise1 {

    private static class PairOfPersonEmployer {
        private final String person;
        private final String employer;

        public PairOfPersonEmployer(String employer, String person) {
            this.employer = employer;
            this.person = person;
        }

        public String getPerson() {
            return person;
        }

        public String getEmployer() {
            return employer;
        }
    }

    // "epam" -> "Alex Ivanov 23, Semen Popugaev 25, Ivan Ivanov 33"
    @Test
    public void getEmployeesByEmployer() {
        Map<String, String> result = getEmployees().stream()
                .flatMap(e -> e.getJobHistory().stream()
                        .map(JobHistoryEntry::getEmployer)
                        .map(employer -> new PairOfPersonEmployer(employer, e.getPerson().toString())))
                .collect(Collectors.
                        groupingBy(PairOfPersonEmployer::getEmployer,
                                Collectors.
                                        mapping(PairOfPersonEmployer::getPerson, Collectors.joining(", "))));

    }

    private static class PairOfPersonPositionDuration {
        private final Person person;
        private final String position;
        private final int duration;

        public PairOfPersonPositionDuration(Person person, String position, int duration) {
            this.person = person;
            this.position = position;
            this.duration = duration;
        }

        public Person getPerson() {
            return person;
        }

        public String getPosition() {
            return position;
        }

        public int getDuration() {
            return duration;
        }
    }

    @Test
    public void getTheCoolestOne() {
        Map<String, Person> coolestByPosition = getCoolestByPosition(getEmployees());

        coolestByPosition.forEach((position, person) -> System.out.println(position + " -> " + person));
    }

    private Map<String, Person> getCoolestByPosition(List<Employee> employees) {
        // First option
        // Collectors.maxBy
        // Collectors.collectingAndThen
        // Collectors.groupingBy

        // Second option
        // Collectors.toMap
        // iterate twice: stream...collect(...).stream()...
        return getEmployees().stream()
                .flatMap(e -> e.getJobHistory().stream()
                        .collect(Collectors.
                                groupingBy(JobHistoryEntry::getPosition, Collectors.summingInt(JobHistoryEntry::getDuration)))
                        .entrySet()
                        .stream()
                        .map(pair -> new PairOfPersonPositionDuration(e.getPerson(), pair.getKey(), pair.getValue())))
                .collect(Collectors
                        .groupingBy(PairOfPersonPositionDuration::getPosition,
                                Collectors
                                        .collectingAndThen(Collectors
                                                        .maxBy(Comparator.comparing(PairOfPersonPositionDuration::getDuration)),
                                                elem -> elem.get().getPerson())));

    }

    private List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(
                        new Person("John", "Galt", 20),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(2, "dev", "google")
                        )),
                new Employee(
                        new Person("John", "Doe", 21),
                        Arrays.asList(
                                new JobHistoryEntry(4, "BA", "yandex"),
                                new JobHistoryEntry(2, "QA", "epam"),
                                new JobHistoryEntry(2, "dev", "abc")
                        )),
                new Employee(
                        new Person("John", "White", 22),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "QA", "epam")
                        )),
                new Employee(
                        new Person("John", "Galt", 23),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(2, "dev", "google")
                        )),
                new Employee(
                        new Person("John", "Doe", 24),
                        Arrays.asList(
                                new JobHistoryEntry(4, "QA", "yandex"),
                                new JobHistoryEntry(2, "BA", "epam"),
                                new JobHistoryEntry(2, "dev", "abc")
                        )),
                new Employee(
                        new Person("John", "White", 25),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "QA", "epam")
                        )),
                new Employee(
                        new Person("John", "Galt", 26),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(1, "dev", "google")
                        )),
                new Employee(
                        new Person("Bob", "Doe", 27),
                        Arrays.asList(
                                new JobHistoryEntry(4, "QA", "yandex"),
                                new JobHistoryEntry(2, "QA", "epam"),
                                new JobHistoryEntry(2, "dev", "abc")
                        )),
                new Employee(
                        new Person("John", "White", 28),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "BA", "epam")
                        )),
                new Employee(
                        new Person("John", "Galt", 29),
                        Arrays.asList(
                                new JobHistoryEntry(3, "dev", "epam"),
                                new JobHistoryEntry(1, "dev", "google")
                        )),
                new Employee(
                        new Person("John", "Doe", 30),
                        Arrays.asList(
                                new JobHistoryEntry(4, "QA", "yandex"),
                                new JobHistoryEntry(2, "QA", "epam"),
                                new JobHistoryEntry(5, "dev", "abc")
                        )),
                new Employee(
                        new Person("Bob", "White", 31),
                        Collections.singletonList(
                                new JobHistoryEntry(6, "QA", "epam")
                        ))
        );
    }

}
