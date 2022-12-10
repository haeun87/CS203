package Lab04;

import java.util.Scanner;

public class BankAccountTester {

	public static void main(String[] args)
	{
		try {
			// user inputs

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter the first name, last name, and account number of customer");

			System.out.print("First, enter the first name of the customer: ");
			String firstname = sc.nextLine();

			System.out.print("Enter the last name of the customer: ");
			String lastname = sc.nextLine();

			System.out.print("Finally, enter the customer's account number: ");
			int accNo = Integer.valueOf(sc.nextLine());


			// Declare BankAccount class instance
			BankAccount b1 = new BankAccount(4500);

			// Set name, accoutNumber to the declared class by given inputs
			b1.setName(firstname,lastname);
			b1.setAccountNumber(accNo);

			// Check the full name and the account number
			System.out.println(b1.getFullName());
			System.out.println(b1.getAccountNumer());

			// Check deposit, withdraw,and getBalance
			b1.depositToAccount(500);
			System.out.println(b1.getAccouuntBalance());
			b1.withdrawFromAccount(1000);
			System.out.println(b1.getAccouuntBalance());
			b1.withdrawFromAccount(5000);
			System.out.println(b1.getAccouuntBalance()); // Overdraw attempt check

			// Check setLoan, getLoan
			b1.setLoan();
			System.out.println(b1.getLoan());

			// Check account number if Armstrong number
			System.out.println(b1.isArmstrongNumber());

			sc.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}


	}

}
