package Lab06;

public class Calculator {

	private int num1;
	private int num2;

	public Calculator(int n1, int n2) {
		this.num1 = n1;
		this.num2 = n2;
	}

	public void setNum1(int n1) {
		this.num1 = n1;
	}

	public int getNum1() {
		return this.num1;
	}

	public void setNum2(int n2) {
		this.num2 = n2;
	}

	public int getNum2() {
		return this.num2;
	}

	/**
	 * This function adds up the given integer numbers.
	 * @param int n1
	 * @param int n2
	 * @return int result - the addition result of n1, n2
	 */
	public int add(int n1, int n2) {
		return n1 + n2;
	}

	/**
	 * This function subtracts the second integer number from the first integer number.
	 * @param int n1
	 * @param int n2
	 * @return int result - the subtraction result of n1, n2
	 */
	public int subtract(int n1, int n2) {
		return n1 - n2;
	}

	/**
	 * This function multiplies the given integer numbers.
	 * @param int n1
	 * @param int n2
	 * @return int result - the multiplication result of n1, n2
	 */
	public int multiply(int n1, int n2) {
		return n1 * n2;
	}

	/**
	 * This function divides the first integer number by the second integer number.
	 * @param double n1
	 * @param double n2
	 * @return double result - the division result of n1, n2
	 */
	public double divide(double n1, double n2) {
		return n1/n2;
	}
}
