package Lab06;

public class ScientificCaculator extends Calculator {

	public ScientificCaculator(int n1, int n2) {
		super(n1, n2);
	}

	/**
	 * This function returns the square root value of the given integer number.
	 * @param int num
	 * @return double result - the square root value of the given number.
	 */
	public double getSquareRoot(int num) {
		return Math.sqrt(num);
	}

	/**
	 * This function returns the exponent value by the given integer numbers.
	 * @param int baseNum - the integer number to be the base number.
	 * @param int exponentNum - the integer number to be exponent number.
	 * @return double result - the exponent value made of by the given numbers.
	 */
	public double getExponent(int baseNum, int exponentNum) {
		return Math.pow(baseNum, exponentNum);
	}

}
