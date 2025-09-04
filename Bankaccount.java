import java.util.Scanner;
public class BankAccount {
String name;
double balance;
int accountNo;
public BankAccount(String name, int accountNo, double balance) {
this.name = name;
this.accountno = accountNo;
this.balance = balance;
}
public void withdraw(double amount) {
if (balance < amount) {
System.out.println("Insufficient Funds \n");
} else {
balance = balance - amount;
System.out.println(amount + " amount withdrawn \n");
}
}
public void deposit(double amount) {
balance = balance + amount;
System.out.println(amount + " amount added to the account \n");
}
public void showBalance() {
System.out.println("Balance = " + balance + "\n");
}
public void displayAccount() {
System.out.println("Account Holder: " + name);
System.out.println("Account No: " + accountNo);
System.out.println("Balance: " + balance + "\n");
}
// Main function
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
BankAccount bank1 = new BankAccount("Trupti", 2084, 50000);
SavingAccount s1 = new SavingAccount("Trupti", 2084, 50000, 10);
while (true) {
System.out.println("Welcome to Bank Portal");
System.out.println("1.Display Account Details");
System.out.println("2.Deposit");
System.out.println("3.Withdraw");
System.out.println("4.Show Balance");
System.out.println("5.Show Savings Account Balance with Interest");
System.out.println("6.Exit");
System.out.print("Enter your choice (1-6): ");
int choice = sc.nextInt();
switch (choice) {
case 1:
bank1.displayAccount();
break;
case 2:
System.out.print("Enter Amount to deposit: ");
double damount = sc.nextDouble();
bank1.deposit(damount);
break;
case 3:
System.out.print("Enter Amount to withdraw: ");
double amount = sc.nextDouble();
bank1.withdraw(amount);
break;
case 4:
bank1.showBalance();
break;
case 5:
s1.with_Interest();
break;
case 6:
System.out.println("Thank You");
return;
default:
System.out.println("Invalid Choice");
break;
}
}
}
}
// Savings Account class
class SavingAccount extends BankAccount {
private int rate;
public SavingAccount(String name, int accountNo, double balance, int rate) {
super(name, accountNo, balance);
this.rate = rate;
}
public void with_Interest() {
double interest;
interest = (balance * rate) / 100;
deposit(interest);
System.out.println("Balance in Savings account (with interest) = " + balance + "\n");
}
}
