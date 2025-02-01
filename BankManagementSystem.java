import java.util.*;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    
    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
    
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + balance);
    }
}

class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();
    
    public void createAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        if (!accounts.containsKey(accountNumber)) {
            BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, initialDeposit);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account successfully created.");
        } else {
            System.out.println("Account number already exists.");
        }
    }
    
    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (BankAccount account : accounts.values()) {
                account.displayAccountInfo();
                System.out.println("-------------------");
            }
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        
        while (true) {
            System.out.println("\n===== Bank Account Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Account Info");
            System.out.println("5. Show All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String accountHolder = scanner.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    double deposit = scanner.nextDouble();
                    bank.createAccount(accountNumber, accountHolder, deposit);
                    break;
                
                case 2:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    BankAccount account = bank.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter Deposit Amount: ");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    account = bank.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter Withdrawal Amount: ");
                        double amount = scanner.nextDouble();
                        account.withdraw(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                
                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    account = bank.getAccount(accountNumber);
                    if (account != null) {
                        account.displayAccountInfo();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                
                case 5:
                    bank.displayAllAccounts();
                    break;
                
                case 6:
                    System.out.println("Thank you for using the Bank Account Management System.");
                    scanner.close();
                    System.exit(0);
                    break;
                
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
