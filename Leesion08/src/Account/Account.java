package Account;

public class Account implements  IAccount{
    private String name;
    private int accountNumber;
    private String email;
    private double accountBalance;

    @Override
    public double recharge(double amount) {
        return (accountBalance-amount);
    }

    @Override
    public String changeEmail(String email) {
        this.email = email;
        return displayInfo();
    }

    @Override
    public String displayInfo() {
        return "name: " + name +
                "\n accountNumber: " + accountNumber+
                "\n email: " + email+
                "\n accountBalance: " + accountBalance;
    }

    public String searchID (int stk){
        if (this.accountNumber == stk){
            return name+accountNumber+email+accountNumber;
        }
        return "K tim thay tk";
    }

    public Account(String name, int accountNumber, String email, double accountBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.email = email;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
