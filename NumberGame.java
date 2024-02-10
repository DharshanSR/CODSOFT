import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int totalScore = 0; // Assuming 1 point per round won

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int number = random.nextInt(100) + 1;
            int attempts = 0;
            final int maxAttempts = 5;
            boolean isGuessed = false;

            System.out.println("\nGuess the number between 1 and 100. You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts && !isGuessed) {
                System.out.print("Enter your guess: ");

                int guess = 0;
                boolean validGuess = false;
                while (!validGuess) {
                    try {
                        guess = scanner.nextInt();
                        validGuess = true; // Guess was successfully retrieved
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid integer.");
                        scanner.next(); // Consume the invalid input
                    }
                }

                attempts++;

                if (guess < 1 || guess > 100) {
                    System.out.println("Your guess is out of bounds. Please guess a number between 1 and 100.");
                } else if (guess < number) {
                    System.out.println("Too low!");
                } else if (guess > number) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You've guessed the number in " + attempts + " attempts.");
                    isGuessed = true;
                    totalScore++; // Increment score for a win
                }
            }

            if (!isGuessed) {
                System.out.println("You've used all your attempts! The correct number was " + number);
            }

            totalRounds++; // Increment the number of rounds played

            System.out.print("Would you like to play another round? (yes/no): ");
            while (!scanner.hasNext("[ynYNyesYESnoNO]+")) {
                System.out.println("Please enter 'yes' or 'no'.");
                scanner.next(); // Consume the invalid input
            }
        } while (scanner.next().equalsIgnoreCase("yes"));

        System.out.println("\nGame Over. You played " + totalRounds + " rounds and won " + totalScore + " of them.");
        scanner.close();
    }
}
