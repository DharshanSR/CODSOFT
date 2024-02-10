import java.util.InputMismatchException;
import java.util.Scanner;
public class ATM {
    private BankAccount userAccount;
    private int pinCode;
    private Scanner scanner = new Scanner(System.in);

    public ATM(BankAccount userAccount, int pinCode) {
        this.userAccount = userAccount;
        this.pinCode = pinCode;
    }

    public void displayMenu() {
        System.out.println("\n************************");
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.println("************************");
    }

    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public boolean authenticateUser() {
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();
        return enteredPin == pinCode;
    }

    private double getValidAmount(String prompt) {
        double amount = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                amount = scanner.nextDouble();
                if (amount > 0) {
                    validInput = true;
                } else {
                    System.out.println("Invalid amount. Please enter a positive amount.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numerical value.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        return amount;
    }

    private void withdraw(double withdrawalAmount) {
        if (withdrawalAmount > 0 && withdrawalAmount < userAccount.getBalance() &&
                withdrawalAmount > 1000 && userAccount.getBalance() > 1000) {
            userAccount.subtractFromBalance(withdrawalAmount);
            System.out.println("Withdrawal successful. Remaining balance: " + userAccount.getBalance());
        } else if (withdrawalAmount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive amount.");
        } else if (withdrawalAmount >= userAccount.getBalance()) {
            System.out.println("You cannot withdraw the entire balance.");
        } else if (withdrawalAmount <= 1000) {
            System.out.println("Withdrawal amount must be greater than 1000. Please enter a larger amount.");
        } else if (userAccount.getBalance() <= 1000) {
            System.out.println("Insufficient funds. Withdrawal not allowed.");
        }
    }



    private void deposit(double depositAmount) {
        if (depositAmount > 0) {
            userAccount.addToBalance(depositAmount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive amount.");
        }
    }

    private void checkBalance() {
        System.out.println("Current balance: " + userAccount.getBalance());
    }

    public void runATM() {
        boolean authenticated = authenticateUser();

        if (!authenticated) {
            System.out.println("Invalid PIN. Exiting...");
            return;
        }

        boolean exit = false;

        while (!exit) {
            displayMenu();

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    double withdrawalAmount = getValidAmount("\nEnter withdrawal amount: ");
                    withdraw(withdrawalAmount);
                    break;
                case 2:
                    double depositAmount = getValidAmount("\nEnter deposit amount: ");
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please choose again.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        int pinCode = 1234;

        ATM atm = new ATM(userAccount, pinCode);
        atm.runATM();
    }
}