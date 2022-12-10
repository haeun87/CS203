package hsuh_HW1;

import java.util.Scanner;

public class Homework1 {

	public static void cubeOfOdd(int n1) {
		for (int i=1; i < n1; i++) {
			if(i % 2 != 0) {
				System.out.println((int)Math.pow(i,3));
			}
		}
	}

	public static void introToJava(int n2) {
		String result = "Go Blazers";
		if(n2 % 21 == 0) {
			result = "UAB CS 203";
		}
		else if(n2 % 7 == 0) {
			result = "UAB";
		}
		else if(n2 % 3 == 0) {
			result = "CS";
		}
		else {
			for(int i= 2;i < n2; i++) {
				if (n2 % i == 0) {
					result = (int)Math.pow(n2,3)+"";
					break;
				}
			}
		}
		System.out.println(result);

	}

	public static String printJava(int n3) {
		String result = "0";
		int powOfTwo = 1;
		for(int i=1; i <=n3; i++) {
			if(i == powOfTwo) {
				result += "JAVA";
				powOfTwo *= 2;
			}
			else {
				result += i;
			}
		}
		return result;
	}

	public static int numSteps(int n) {
		int steps = 0;
		while(n > 0) {
			if(n % 2 ==0) {
				n /= 2;
			}
			else {
				n -= 1;
			}
			steps += 1;
		}

		return steps;
	}

	public static void grader(float avg_exams, float avg_hw, int attendance) {
		String result = "FAIL";
		if(attendance > 20) {
			if(avg_exams > 70 && avg_hw > 70) {
				if(avg_exams > 85 || avg_hw > 85) {
					result = "PASS";
				}
			}
		}

		System.out.println(result);
	}

	public static void cubeOfOddTest() {
		int n1 = 5;
		cubeOfOdd(n1);
		n1 = 3;
		cubeOfOdd(n1);
		n1 = 8;
		cubeOfOdd(n1);
	}

	public static void introToJavaTest() {
		int n2 = 3;
		introToJava(n2);
		n2 = 70;
		introToJava(n2);
		n2 = 4;
		introToJava(n2);
		n2 = 17;
		introToJava(n2);
	}

	public static void printJavaTest() {
		int n3 = 3;
		System.out.println(printJava(n3));
		n3 = 7;
		System.out.println(printJava(n3));
		n3 = 10;
		System.out.println(printJava(n3));
		n3 = 1;
		System.out.println(printJava(n3));
	}

	public static void numStepsTest() {
		int n = 14;
		System.out.println(numSteps(n));
	}

	public static void graderTest() {
		grader(72, 88, 22);
		grader(66, 100, 24);
		grader(100, 90, 18);
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
		/*
		 * cubeOfOdd Test
		 */
//			cubeOfOddTest(); // HW1 example
			System.out.print("[cubeOfOdd]Enter the Integer Number(n1): ");
			int n1 = Integer.valueOf(sc.nextLine());
			cubeOfOdd(n1);


		/*
		 * introToJava Test
		 */
//			introToJavaTest(); // HW1 example
			System.out.print("[introToJava]Enter an Integer Number(n2): ");
			int n2 = Integer.valueOf(sc.nextLine());
			introToJava(n2);

		/*
		 * printJava Test
		 */
			printJavaTest(); // HW1 example
			System.out.print("[printJava]Enter an Integer Number(n3): ");
			int n3 = Integer.valueOf(sc.nextLine());
			System.out.println(printJava(n3));

		/*
		 * numSteps Test
		 */
//			numStepsTest(); // HW1 example
			System.out.print("[numSteps]Enter an Integer Number(n): ");
			int n = Integer.valueOf(sc.nextLine());
			System.out.println(numSteps(n));

		/*
		 * grader Test
		 */
//			graderTest(); // HW1 example
			System.out.print("[grader]Enter an Integer Number(avg_exams): ");
			float avg_exams = Float.valueOf(sc.nextLine());
			System.out.print("[grader]Enter an Integer Number(avg_hw): ");
			float avg_hw = Float.valueOf(sc.nextLine());
			System.out.print("[grader]Enter a float Number(attendance): ");
			int attendance = Integer.valueOf(sc.nextLine());
			grader(avg_exams, avg_hw, attendance);


			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
