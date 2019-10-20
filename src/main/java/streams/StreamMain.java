package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N35",
                "B10", "B5",
                "G53", "G42", "G60", "G65",
                "I25", "I17", "I29",
                "O71"
        );
        List<String> gNumbers = new ArrayList<>();

        someBingoNumbers.forEach(number -> {
            if (number.startsWith("G")) {
//                Lambda scoping rules
//                The scope of this lambda is within the scope of the main method body.
//                We can use variables declared in the main in lambda expressions inside
//                main. As long as variable are effectively final.

//                In this case, we're changing the contents of our gNumbers list,
//                but the object reference stored in the gNumbers variable doesn't change.
//                gNumbers is effectively final and therefore we can use it within lambda.
                gNumbers.add(number);
                System.out.println(number);
            }
        });

//        Before stream
//        gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
        gNumbers.forEach((String s) -> System.out.println(s));

        System.out.println("\nIn stream:");
        someBingoNumbers
                .stream()   // sequence of elements supporting sequential and parallel aggregate operations
                // Stream is a set of object references. The stream method creates a stream
                // from a collection. Each object reference in the stream corresponds to an object
                // in the collection and the ordering of the object reference matches
                // the ordering of the collection.
                .map(String::toUpperCase) // s -> s.toUpperCase()
                .filter(s -> s.startsWith("G")) // filter wants a predicate (boolean-valued function of one argument).
                // Resulting stream will contain only those items
                // for which the predicate returns true.
                .sorted()
                .forEach(System.out::println);  // Accepts a consumer as a parameter and evaluate
        // the consumer for each item in the stream.

//        Stream operations requirements:   - non-interfering (don't change the source),
//                                          - stateless
//                                              (it's result can't depend on any state outside of the operation)

        System.out.println("\n\nStream part 2");
        Stream<String> ioNumberStream = Stream.of("I25", "I17", "I29", "O71");
        Stream<String> inNumberStream = Stream.of("N40", "N7", "I26", "I17", "O71", "I29", "O71");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
//        System.out.println("Count all elements: " + concatStream.count());
        //You can not use the same stream after terminal operation.
//        System.out.println("Count unique elements: " + concatStream.distinct().count());

        // To print out item in a stream without ending the chain or to debug stream
        // peak method can be used.
        System.out.println("Print unique elements without ending the stream: ");
        System.out.println("Count unique elements: " + concatStream
                .distinct()
                .peek(System.out::println)
                .count());

//        FlatMap
        System.out.println("\n\n>>>>>>> FlatMap <<<<<<");

        Employee justyna = new Employee("Justyna Łąk", 28);
        Employee robert = new Employee("Robert Prus", 32);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee snow = new Employee("Snow White", 23);
        Employee robin = new Employee("Robin Hood", 35);
        Employee batman = new Employee("Bruce Wayne", 40);

        Department hr = new Department("Human Resources");
        hr.addEmployee(tim);
        hr.addEmployee(justyna);
        Department it = new Department("IT");
        it.addEmployee(robin);
        it.addEmployee(robert);
        it.addEmployee(batman);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(snow);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(it);
        departments.add(accounting);


        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);


        System.out.println("----------------------");
//        List<String> sortedGNumbers = someBingoNumbers
//                .stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("G"))
//                .sorted()
//                .collect(Collectors.toList());

        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (String s : sortedGNumbers) {
            System.out.println(s);
        }

        System.out.println("\nGroup employees by age using Collectors.groupBy()");
        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        System.out.println("Employees by age: " + groupedByAge);

        System.out.println("\nPrint the youngest employee using reduce()");

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

//        Stream operations are lazily evaluated. Nothing happens until there is a terminal operation.
        Stream.of("ABC", "AC", "BAA", "CCCCC", "ST", "zzzz")
                .filter(s -> {
                            System.out.println(s);
                            return s.length() == 3;
                        });
        System.out.println("\nStream has terminal operation. Now, intermediate printing operation can be executed.");
        Stream.of("ABC", "AC", "BAA", "CCCCC", "ST", "zzzz")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                }).count();
    }

}
