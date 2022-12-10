package Lab04;

import java.text.NumberFormat;
import java.util.Locale;

public class BankAccount {

	private String firstName, lastName;
	private int accountNumber;
	private double balance = 0;

	private float interestRate = 0.05f;
	private int loanTerm = 3;
	private double loanGranted = 0;


	public BankAccount(double initialBalance) {
		this.balance = initialBalance;
	}


	public void setName(String first, String last)
	{
		this.firstName = first;
		this.lastName = last;
	}

	public void setAccountNumber(int accNo)
	{
		this.accountNumber = accNo;
	}


	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}


	public int getAccountNumer() {
		return this.accountNumber;
	}

	/**
	 * This function add the given number amount to the account balance
	 * @param double money
	 * @return
	 */
	public void depositToAccount(double money) {
		this.balance += money;
	}

	/**
	 * This function deduct the given number amount to the account balance or print a warning message if the account was not enough for withdrawal
	 * @param double money
	 * @return
	 */
	public void withdrawFromAccount(double money) {
		if(this.balance < money) {
			NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
			System.out.println("Cannot withdraw "+ nf.format(money) + " from your account! (Current Balance: "+ nf.format(this.balance) + ")");
		}
		else {
			this.balance -= money;
		}
	}

	/**
	 * This function returns the current account balance
	 * @param
	 * @return double balance
	 */
	public double getAccouuntBalance() {
		return this.balance;
	}

	/**
	 * This function establishes a loan and its regarding total interest, to the customer's account
	 * @param
	 * @return
	 */
	public void setLoan() {
		this.loanGranted += 5000*(1+this.loanTerm*this.interestRate);
	}

	/**
	 * This function returns the total amount to be paid for 5% 3-Year loan
	 * @param
	 * @return double loanGranted
	 */
	public double getLoan() {
		return this.loanGranted;
	}


	/**
	 * This function returns whether the account number is in Armstrong number
	 * @param
	 * @return boolean isArmStringNumber
	 */
	public boolean isArmstrongNumber() {
		int check = this.accountNumber;
		int result = 0;
		int temp;
		if (check >= 100 && check <=999) {
			while(check > 0) {
				temp = check % 10;
				result += Math.pow(temp, 3);
				check = check / 10;
			}
			return (this.accountNumber == result) ? true: false;
		}

		return false;
	}
}
