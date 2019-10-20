package streams;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamChallenge {

    public static void main(String[] args) {
        System.out.println("Challenge #1");
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";

                String[] parts = myString
                        .split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };*/

        Runnable runnable1 = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString
                    .split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        runnable1.run();

        System.out.println("\n\nChallenge #3");
//        Write the code that will execute the function with an argument of "1234567890"

//        Function<String, String> lambaFunction = s -> {
//            StringBuilder returnVal = new StringBuilder();
//            for (int i = 0; i < s.length(); i++) {
//                if (i % 2 == 1) {
//                    returnVal.append(s.charAt(i));
//                }
//            }
//            return returnVal.toString();
//        };
        Function<String, String> lambaFunction = s -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println("challenge #3: " + lambaFunction.apply("1234567890"));

        System.out.println("\n\n{ Challenge #5 :");
        String result = everysecondCharacter(lambaFunction, "1234567890");
        System.out.println("Challenge #5 result is: " + result + " }");


        System.out.println("\n\n{ Challenge #6 & #7:");
//        Supplier<String> iLoveJava = () -> "I love Java";

        Supplier<String> iLoveJava = () -> {
            return "I love Java";
        };
        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult + "} ");

    }

    public static String everySecondChar(String source) {
        System.out.println("\n\nChallenge #2");

//        Change this expression into lamba expr

        /*StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i%2==1){
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();*/


//        version #1
        String returnVal = IntStream.range(0, source.length())
                .filter(i -> i % 2 == 1)
                .mapToObj(i -> String.valueOf(source.charAt(i)))
                .collect(Collectors.joining());

        return returnVal;

//      version #2
//        Function<String, String> lambaFunction = s -> {
//            StringBuilder returnVal = new StringBuilder();
//            for (int i = 0; i < source.length(); i++) {
//                if (i % 2 == 1) {
//                    returnVal.append(source.charAt(i));
//                }
//            }
//            return returnVal.toString();
//        };
//        return lambaFunction.toString();
    }

    //    Pass the function to the method
    public static String everysecondCharacter(Function<String, String> function, String s) {
        System.out.println("\n\t{ Challenge #4 : " + function.apply(s) + " }\n\n");
        return function.apply(s);
    }
}
