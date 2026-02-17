import java.util.Scanner;

class BankAccount{
    private String accountNumber;
    private String accountHoldername;
    private double balance;
    private String accountType;
    private String[] transactionHistory = new String[20];
    private int count = 0;
    private int currentTransitions = 0;
    private int transactionCount;
    private double feeCharges;

    public BankAccount(String accountNumber, String accountHoldername, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHoldername = accountHoldername;
        this.balance = initialBalance;
        this.accountType = accountType;

    }
    void validateIsSaving(){
        if (getAccountType().equals("Savings")){
            if (this.balance<500){
                System.out.println("Saving Account Balance can't be less than 500, deposit it");
                this.balance=0;
            }
        }
    }

    public String getAccountType() {
        return accountType;
    }
    void addToHistory(String value , double amount){
        transactionHistory[count++] = value + " : " +  amount + "\n";
        if (count==transactionHistory.length){
            count=0;
        }
        if (currentTransitions < transactionHistory.length){
            currentTransitions++; //Show Current Number of Transactions
        }
    }

    void ifChecking(){
        if (getAccountType()=="Checking"){
            if (balance>=0 && balance<100){
                feeCharges = 5.0;
            }else feeCharges = 0.0;
        }
    }
    void deposit(double amount){
        if (isValidAmount(amount)){
            balance += amount;
            System.out.println("Amount Deposited");
            addToHistory("Deposit(" + accountType + ")" ,amount);
        }else System.out.println("Amount can't be negative or zero");

    }
    void withdraw(double amount){
        if (isValidAmount(amount)){
            balance -= amount;
            System.out.println("Amount Withdraw");
            addToHistory("Withdraw(" + accountType + ")" ,amount);
        }else System.out.println("Amount can't be negative or zero");

    }
    void transfer(BankAccount recipient , double amount){
        if (isValidAmount(amount)){
            recipient.balance = recipient.balance + amount;
            balance -= amount;
            addToHistory("Transfer to another account" ,amount);
        }else System.out.println("Amount can't be negative or zero");
    }
    double calculateInterest(){
        if (this.accountType=="Savings"){
            return 0.02*balance;
        }else if (this.accountType=="Checking"){
            return 0.005*balance;
        }
        return 0.0;
    }
    void applyInterest(){
        this.balance += this.calculateInterest();
        addToHistory("Interest Applied, New Balance" ,balance);
    }
    double getBalance(){
        return balance;
    }
    //getHistory
    String getAccountSummary(){
        ifChecking();
        return "Account Number : " + accountNumber + "\n" +
                "Account Holder Name : " + accountHoldername + "\n" +
                "Account Type : " + accountType + "\n" +
                "Current Balance : " + balance + "\n" +
                "Fee Charges : " + feeCharges;

    }
    boolean isLowBalance(){
        if (balance<100){
            return true;
        }
        return false;
    }
    boolean canWithdraw(double amount){
        if (balance>=amount){
            if (isValidAmount(amount)){
                return true;
            }else System.out.println("Amount can't be negative");
        }
        return false;
    }
    boolean isValidAmount(double amount){
        if (amount>0){
            return true;
        }
        return false;
    }
    void deposit (double amount , String description){
        balance += amount;
    }
    String [] getTransactionHistory(){
        return transactionHistory;
    }
}
public class Task5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount savings = new BankAccount("S001", "Alice", 1000, "Savings");
        savings.validateIsSaving();
        BankAccount checking = new BankAccount("C001", "Alice", 500, "Checking");

        while (true) {
            System.out.println("\n===== Banking Transaction System Menu =====");
            System.out.println("1. Deposit to Savings");
            System.out.println("2. Withdraw from Savings");
            System.out.println("3. Deposit to Checking");
            System.out.println("4. Withdraw from Checking");
            System.out.println("5. Transfer Savings to Checking");
            System.out.println("6. Transfer Checking to Savings");
            System.out.println("7. Apply Interest to Accounts");
            System.out.println("8. Check Low Balance Warnings");
            System.out.println("9. View Transaction History");
            System.out.println("10. Show Account Summaries");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount for Savings: ");
                    double Dep_amount = sc.nextDouble();
                    savings.deposit(Dep_amount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount from Savings: ");
                    double With_amount = sc.nextDouble();
                    if(savings.canWithdraw(With_amount)) {
                        savings.withdraw(With_amount);
                    } else {
                        System.out.println("Cannot withdraw. Minimum balance requirement not met.");
                    }
                    break;
                case 3:
                    System.out.print("Enter deposit amount for Checking: ");
                    double Dep_amount_ch = sc.nextDouble();
                    checking.deposit(Dep_amount_ch);
                    break;
                case 4:
                    System.out.print("Enter withdrawal amount from Checking: ");
                    double With_amount_ch = sc.nextDouble();
                    if(checking.canWithdraw(With_amount_ch)) {
                        checking.withdraw(With_amount_ch);
                    } else {
                        System.out.println("Cannot withdraw. Not enough balance.");
                    }
                    break;
                case 5:
                    System.out.print("Enter amount to transfer Savings to Checking: ");
                    double t1 = sc.nextDouble();
                    if(savings.canWithdraw(t1)) {
                        savings.transfer(checking, t1);
                    } else {
                        System.out.println("Transfer failed. Savings minimum balance restriction.");
                    }
                    break;
                case 6:
                    System.out.print("Enter amount to transfer Checking to Savings: ");
                    double t2 = sc.nextDouble();
                    if(checking.canWithdraw(t2)) {
                        checking.transfer(savings, t2);
                    } else {
                        System.out.println("Transfer failed. Insufficient funds.");
                    }
                    break;
                case 7:
                    savings.applyInterest();
                    checking.applyInterest();
                    System.out.println("Interest applied to both accounts.");
                    break;
                case 8:
                    if(savings.isLowBalance()) System.out.println("Savings account low balance!");
                    else System.out.println("No Warning for Saving");
                    if(checking.isLowBalance()) System.out.println("Checking account low balance!");
                    else System.out.println("No Warning for Checking");
                    break;
                case 9:
                    System.out.println("=====Savings Transaction History=====");
                    for(String values : savings.getTransactionHistory()) {
                        if(values != null) {
                            System.out.print(values);
                        }
                    }
                    System.out.println("=====Checking Transaction History=====");
                    for(String values : checking.getTransactionHistory()) {
                        if(values != null) {
                            System.out.print(values);
                        }
                    }
                    break;
                case 10:
                    System.out.println("Savings Account Summary:");
                    System.out.println(savings.getAccountSummary());
                    System.out.println("\nChecking Account Summary");
                    System.out.println(checking.getAccountSummary());
                    break;
                case 11:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}

