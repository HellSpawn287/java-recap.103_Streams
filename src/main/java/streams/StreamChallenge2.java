package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamChallenge2 {

    public static void main(String[] args) {

        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "olivier",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        System.out.println("\n\n { Challenge #9: ");
        List<String> firstUpperCaseList = new ArrayList<>();
        topNames2015.forEach(name ->
                firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));

        firstUpperCaseList.sort((s1, s2) -> s1.compareTo(s2));
        firstUpperCaseList.forEach(s -> System.out.println(s));
        System.out.println("} ");

        System.out.println("\n\n { Challenge #10: ");
        List<String> firstUpperCaseList2 = new ArrayList<>();
        topNames2015.forEach(name ->
                firstUpperCaseList2.add(name.substring(0, 1).toUpperCase() + name.substring(1)));

        firstUpperCaseList2.sort(String::compareTo);
        firstUpperCaseList2.forEach(System.out::println);
        System.out.println("} ");

        System.out.println("\n\n { Challenge #11: ");
        topNames2015.stream()
                .map(name -> (name.substring(0, 1).toUpperCase() + name.substring(1)))
                .sorted(String::compareTo)
                .forEach(System.out::println);
        System.out.println("} ");

        System.out.println("\n\n { Challenge #12: ");
        Long namesBeginningWithA = topNames2015.stream()
                .map(name -> (name.substring(0, 1).toUpperCase() + name.substring(1)))
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println("Number of names beginning with 'A' is: "
                + namesBeginningWithA + "\n} ");

        System.out.println("\n\n { Challenge #14: ");
        topNames2015.stream()
                .map(name -> (name.substring(0, 1).toUpperCase() + name.substring(1)))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println("} ");
    }


}
