import java.util.Random;
import java.util.Scanner;

public class Main {

    public static String length;
    public static int lengthInt;
    public static int range;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");

        length = scanner.nextLine();

        if (length.equalsIgnoreCase("1") || length.equalsIgnoreCase("2")
                || length.equalsIgnoreCase("3") || length.equalsIgnoreCase("4")
                || length.equalsIgnoreCase("5") || length.equalsIgnoreCase("6")
                || length.equalsIgnoreCase("7") || length.equalsIgnoreCase("8")
                || length.equalsIgnoreCase("9") || length.equalsIgnoreCase("10")
                || length.equalsIgnoreCase("11")) {

            lengthInt = Integer.parseInt(length);

            System.out.println("Input the number of possible symbols in the code:");

            range = scanner.nextInt();

            if (lengthInt <= range) {
                if (range <= 36) {

                    StringBuilder chars = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");

                    StringBuilder newChars = new StringBuilder(chars.substring(0, range));

                    StringBuilder hidedCode = new StringBuilder(lengthInt);

                    for (int i = 0; i < lengthInt; i++) {
                        hidedCode.append("*");
                    }

                    System.out.println("The secret is prepared: " + hidedCode + " (0-9, a-" + newChars.charAt(newChars.length() - 1) + ").");

                    Random random = new Random();

                    StringBuilder code = new StringBuilder(lengthInt);

                    for (int i = 0; i < lengthInt; i++) {
                        String character = String.valueOf(newChars.charAt(random.nextInt(newChars.length())));
                        code.append(character);
                        newChars.deleteCharAt(newChars.indexOf(character));
                    }

                    code.toString();

                    System.out.println("Okay, let's start a game!");


                    int counter = 1;
                    boolean flag = false;

                    while (flag == false) {

                        System.out.println("Turn " + counter + ":");
                        String guess = scanner.next();

                        Grader grade = new Grader(code, guess);

                        grade.cowGrader(String.valueOf(code), guess);
                        grade.bullGrader(String.valueOf(code), guess);
                        flag = grade.printGrade(String.valueOf(code));
                        counter++;
                    }

                } else {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                }

            } else {
                System.out.println("Error: it's not possible to generate a code with a length of " + lengthInt +
                        " with " + range + " unique symbols.");
            }
        } else {
            System.out.println("Error: " + length + " isn't a valid number.");
        }


    }

    public static class Grader {
        private String code;
        private String guess;
        private int bull = 0;
        private int cow = 0;

        boolean flag = false;

        public Grader(StringBuilder code, String guess) {
            this.code = String.valueOf(code);
            this.guess = guess;
        }

        public int cowGrader(String code, String guess) {
            String[] splitGuess = guess.split("");

            for (int i = 0; i <= splitGuess.length - 1; i++) {
                if (code.contains(splitGuess[i])) {
                    cow++;
                }
            }
            return cow;
        }

        public int bullGrader(String code, String guess) {
            String[] splitedCode = code.split("");
            String[] splitedGuess = guess.split("");

            int index = 0;
            for (int i = 0; i <= splitedGuess.length - 1; i++) {
                if (splitedGuess[index].equalsIgnoreCase(splitedCode[i])) {
                    bull++;
                }
                index++;
            }
            return bull;

        }

        public boolean printGrade(String code) {

            if (this.bull == code.length()) {
                System.out.println("Grade: " + this.bull + " bull(s). The secret code is " + code + ".");
                System.out.println("Congratulations! You guessed the secret code.");
                return flag = true;
            } else if (this.bull == 0 && this.cow == 4) {
                System.out.println("Grade: " + this.cow + " cow(s)");
            } else if (this.bull == code.length() - 1) {
                System.out.println("Grade: " + this.bull + " bull(s)");
            } else if (this.bull == 0 && this.cow == code.length() - 1) {
                System.out.println("Grade: " + this.cow + " cow(s)");
            } else if (this.bull == 2 && this.cow >= 2) {
                System.out.println("Grade: " + this.bull + " bull(s) and 2 cow(s)");
            } else if (this.bull == 0 && this.cow == 2) {
                System.out.println("Grade: " + this.cow + " cow(s)");
            } else if (this.bull == 1 && this.cow >= 3) {
                System.out.println("Grade: " + this.bull + " bull(s) and 3 cow(s)");
            } else if (this.bull == 1 && this.cow >= 1) {
                System.out.println("Grade: " + this.bull + " bull(s) and 1 cow(s)");
            } else if (this.bull == 0 && this.cow == 1) {
                System.out.println("Grade: " + this.cow + " cow(s)");
            } else {
                System.out.println("Grade: None");
            }
            return flag;
        }

    }
}