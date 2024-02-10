import java.util.Scanner;

public class StudentCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continueCalculating;

        // Display a welcome message
        System.out.println("Welcome to the Student Grade Calculator!");

        do {
            // Display options to the user
            System.out.println("\nPlease follow the instructions below to calculate grades:");

            int numberOfSubjects = 0;
            while (numberOfSubjects <= 0) {
                System.out.println("Enter the number of subjects (must be positive):");
                if (!scanner.hasNextInt()) {
                    scanner.next(); // Consume the non-integer input
                    System.out.println("Invalid input. Please enter a positive integer for the number of subjects.");
                    continue;
                }
                numberOfSubjects = scanner.nextInt();
                if (numberOfSubjects <= 0) {
                    System.out.println("Invalid number of subjects. Please enter a positive number.");
                }
            }

            scanner.nextLine(); // Consume the leftover newline

            int[] marks = new int[numberOfSubjects];
            int totalMarks = 0;

            for (int i = 0; i < numberOfSubjects; i++) {
                marks[i] = getValidatedMark(scanner, i);
                totalMarks += marks[i];
            }

            double averagePercentage = (double) totalMarks / numberOfSubjects;
            char grade = calculateGrade(averagePercentage);

            System.out.printf("Total Marks: %d\n", totalMarks);
            System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
            System.out.println("Grade: " + grade);

            // Ask the user if they want to continue
            System.out.println("Do you want to calculate another set of grades? (yes/no):");
            continueCalculating = scanner.nextLine().trim().toLowerCase();

        } while (!continueCalculating.equals("no"));
    }

    private static int getValidatedMark(Scanner scanner, int subjectIndex) {
        int mark = 0;
        do {
            System.out.printf("Enter marks (out of 100) for subject %d:\n", subjectIndex + 1);
            if (!scanner.hasNextInt()) {
                scanner.next(); // Consume the non-integer input
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }
            mark = scanner.nextInt();
            if (mark < 0 || mark > 100) {
                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
            }
        } while (mark < 0 || mark > 100);
        scanner.nextLine(); // Consume the newline
        return mark;
    }

    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 50) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
