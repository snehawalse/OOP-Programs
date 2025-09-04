import java.util.Scanner;
class ATM {
private double balance;
public ATM(double initialBalance) {
this.balance = initialBalance;
}
public void checkBalance() {
System.out.println("Current Balance: ₹" + balance);
}
public void deposit(double amount) {
if (amount <= 0) {
throw new IllegalArgumentException("Deposit amount must be positive.");
}
balance += amount;
System.out.println("Deposited: ₹" + amount);
}
public void withdraw(double amount) {
if (amount <= 0) {
throw new IllegalArgumentException("Withdrawal amount must be positive.");
}
if (amount > balance) {
throw new IllegalArgumentException("Insufficient Balance!");
}
balance -= amount;
System.out.println("Withdrawn: ₹" + amount);
}
}
public class ATMApp {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
ATM atm = new ATM(1000); // initial balance
int choice = 0;
while (true) {
try {
System.out.println("\n--- ATM MENU ---");
System.out.println("1. Check Balance");
System.out.println("2. Deposit");
System.out.println("3. Withdraw");
System.out.println("4. Exit");
System.out.print("Enter your choice: ");
choice = sc.nextInt();
switch (choice) {
case 1:
atm.checkBalance();
break;
case 2:
System.out.print("Enter amount to deposit: ");
double dep = sc.nextDouble();
atm.deposit(dep);
break;
case 3:
System.out.print("Enter amount to withdraw: ");
double wd = sc.nextDouble();
atm.withdraw(wd);
break;
case 4:
System.out.println("Thank you for using ATM. Goodbye!");
sc.close();
System.exit(0);
default:
System.out.println("Invalid choice! Please try again.");
}
} catch (IllegalArgumentException e) {
System.out.println("Error: " + e.getMessage());
} catch (ArithmeticException e) {
System.out.println("Math Error: " + e.getMessage());
} catch (Exception e) {
System.out.println("Invalid input! Please enter valid numbers.");
sc.nextLine(); // clear invalid input
} finally {
System.out.println("Transaction finished.\n");
}
}
}
}




