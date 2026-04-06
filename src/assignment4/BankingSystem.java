package assignment4;

import java.io.*;
import java.util.*;

public class BankingSystem {

    static final String FILE_NAME = "customers.dat";
    static Scanner sc = new Scanner(System.in);

    @SuppressWarnings("unchecked")
    static List<Customer> loadFromFile() {
        List<Customer> list = new ArrayList<>();
        File f = new File(FILE_NAME);
        if (!f.exists()) return list;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            list = (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    static void saveToFile(List<Customer> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(list);
            System.out.println("Record saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    static void validateCID(int cid) throws InvalidCustomerIDException {
        if (cid < 1 || cid > 20)
            throw new InvalidCustomerIDException(
                    "Customer ID " + cid + " is out of range! CID must be between 1 and 20.");
    }

    static void validateAmount(double amount) throws NegativeAmountException {
        if (amount <= 0)
            throw new NegativeAmountException(
                    "Amount must be a positive value! You entered: " + amount);
    }

    static void validateMinBalance(double amount) throws MinimumBalanceException {
        if (amount < 1000)
            throw new MinimumBalanceException(
                    "Account cannot be created! Minimum opening balance is Rs.1000. You entered: Rs." + amount);
    }

    static void validateWithdrawal(double wthAmt, double balance) throws InsufficientFundsException {
        if (wthAmt > balance)
            throw new InsufficientFundsException(
                    "Insufficient funds! Withdrawal amount Rs." + wthAmt
                            + " exceeds available balance Rs." + String.format("%.2f", balance));
    }

    static void createAccount() {
        try {
            System.out.print("Enter Customer ID (1-20): ");
            int cid = sc.nextInt();
            validateCID(cid);

            List<Customer> list = loadFromFile();

            for (Customer c : list) {
                if (c.cid == cid) {
                    System.out.println("Error: Customer with CID " + cid + " already exists!");
                    return;
                }
            }

            sc.nextLine();
            System.out.print("Enter Customer Name: ");
            String cname = sc.nextLine().trim();

            System.out.print("Enter Opening Amount (min Rs.1000): ");
            double amount = sc.nextDouble();

            validateAmount(amount);
            validateMinBalance(amount);

            Customer newCustomer = new Customer(cid, cname, amount);
            list.add(newCustomer);
            saveToFile(list);

            System.out.println("Account created successfully! --> " + newCustomer);

        } catch (InvalidCustomerIDException | NegativeAmountException | MinimumBalanceException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid input type. Please enter correct values.");
            sc.nextLine();
        }
    }

    static void deposit() {
        try {
            System.out.print("Enter Customer ID: ");
            int cid = sc.nextInt();
            validateCID(cid);

            List<Customer> list = loadFromFile();
            Customer found = null;
            for (Customer c : list) {
                if (c.cid == cid) {
                    found = c;
                    break;
                }
            }

            if (found == null) {
                System.out.println("No customer found with CID: " + cid);
                return;
            }

            System.out.print("Enter Deposit Amount: ");
            double amt = sc.nextDouble();
            validateAmount(amt);

            found.amount += amt;
            saveToFile(list);
            System.out.println("Deposit successful! Updated Record --> " + found);

        } catch (InvalidCustomerIDException | NegativeAmountException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid input.");
            sc.nextLine();
        }
    }

    static void withdraw() {
        try {
            System.out.print("Enter Customer ID: ");
            int cid = sc.nextInt();
            validateCID(cid);

            List<Customer> list = loadFromFile();
            Customer found = null;
            for (Customer c : list) {
                if (c.cid == cid) {
                    found = c;
                    break;
                }
            }

            if (found == null) {
                System.out.println("No customer found with CID: " + cid);
                return;
            }

            System.out.print("Enter Withdrawal Amount: ");
            double wthAmt = sc.nextDouble();
            validateAmount(wthAmt);
            validateWithdrawal(wthAmt, found.amount);

            found.amount -= wthAmt;
            saveToFile(list);
            System.out.println("Withdrawal successful! Updated Record --> " + found);

        } catch (InvalidCustomerIDException | NegativeAmountException | InsufficientFundsException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid input.");
            sc.nextLine();
        }
    }

    static void displayCustomer() {
        try {
            System.out.print("Enter Customer ID: ");
            int cid = sc.nextInt();
            validateCID(cid);

            List<Customer> list = loadFromFile();
            for (Customer c : list) {
                if (c.cid == cid) {
                    System.out.println("Customer Details --> " + c);
                    return;
                }
            }
            System.out.println("No customer found with CID: " + cid);

        } catch (InvalidCustomerIDException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid input.");
            sc.nextLine();
        }
    }

    static void displayAll() {
        List<Customer> list = loadFromFile();
        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n===== All Customer Records =====");
        for (Customer c : list) {
            System.out.println(c);
        }
        System.out.println("================================");
    }

    static void deleteAccount() {
        try {
            System.out.print("Enter Customer ID to delete: ");
            int cid = sc.nextInt();
            validateCID(cid);

            List<Customer> list = loadFromFile();
            boolean removed = list.removeIf(c -> c.cid == cid);

            if (removed) {
                saveToFile(list);
                System.out.println("Account with CID " + cid + " deleted successfully.");
            } else {
                System.out.println("No customer found with CID: " + cid);
            }

        } catch (InvalidCustomerIDException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid input.");
            sc.nextLine();
        }
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║      BANKING SYSTEM MENU     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Create Account            ║");
            System.out.println("║ 2. Deposit Amount            ║");
            System.out.println("║ 3. Withdraw Amount           ║");
            System.out.println("║ 4. Display Customer          ║");
            System.out.println("║ 5. Display All Customers     ║");
            System.out.println("║ 6. Delete Account            ║");
            System.out.println("║ 7. Exit                      ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> displayCustomer();
                case 5 -> displayAll();
                case 6 -> deleteAccount();
                case 7 -> System.out.println("Exiting... Thank you for using the Banking System!");
                default -> System.out.println("Invalid choice! Please enter between 1-7.");
            }

        } while (choice != 7);

        sc.close();
    }
}