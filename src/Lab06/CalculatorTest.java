package Lab06;

import java.util.Scanner;

public class CalculatorTest {


	public static void main(String[] args) {
		try {

			Scanner sc = new Scanner(System.in);

			// Receive the user custom data(Integer).
			System.out.print("Enter the First Number(Num1): ");
			int num1 = Integer.parseInt(sc.nextLine().strip());
			System.out.print("Enter the Second Number(Num2): ");
			int num2 = Integer.parseInt(sc.nextLine().strip());

			System.out.println();

			ScientificCaculator cal = new ScientificCaculator(num1,num2);

			// Function tests for the inherited methods
			System.out.printf("[Addition] %d + %d = %d\n", cal.getNum1(), cal.getNum2(),cal.add(cal.getNum1(),cal.getNum2()));
			System.out.printf("[Substraction] %d - %d = %d\n", cal.getNum1(), cal.getNum2(),cal.subtract(cal.getNum1(),cal.getNum2()));
			System.out.printf("[Multiplication] %d * %d = %d\n", cal.getNum1(), cal.getNum2(),cal.multiply(cal.getNum1(),cal.getNum2()));
			System.out.printf("[Division] %d / %d = %f\n", cal.getNum1(), cal.getNum2(),cal.divide(cal.getNum1(),cal.getNum2()));

			// Function tests for its own methods
			System.out.printf("[Squre root] sqrt(%d) = %f\n", cal.getNum1(),cal.getSquareRoot(cal.getNum1()));
			System.out.printf("[Squre root] sqrt(%d) = %f\n", cal.getNum2(),cal.getSquareRoot(cal.getNum2()));
			System.out.printf("[Power] %d ^ %d = %f\n", cal.getNum1(), cal.getNum2(),cal.getExponent(cal.getNum1(),cal.getNum2()));

			sc.close();
		}
		catch(NumberFormatException ne) {
			System.out.println("Please enter the integer number only!");
			ne.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
