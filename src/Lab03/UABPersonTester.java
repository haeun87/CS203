package Lab03;

import java.util.Scanner;

public class UABPersonTester {

	public static void main(String[] args) {
		try {

			Scanner sc = new Scanner(System.in);

			UABPerson person1 = new UABPerson("Gagamel", "Male", 7, "ggml");
			System.out.println("[UABPerson1]Is your name palindrome? => " + person1.checkPalindrome());
			System.out.println("[UABPerson1]How many years are left until retirement? => " + person1.yearsUntilRetirement() + " years");
			System.out.println("[UABPerson1]" + person1.toString());

			System.out.println();

			UABPerson person2 = new UABPerson("NameToChange", "GenderToChange", 0, "BlazerIdToChange");
			person2.setName("Hannah");
			person2.setGender("Female");
			person2.setAge(21);
			person2.setBlazerID("hn0908");
			System.out.println("[UABPerson2]Is your name palindrome? => " + person2.checkPalindrome());
			System.out.println("[UABPerson2]How many years are left until retirement? => " + person2.yearsUntilRetirement() + " years");
			System.out.println("[UABPerson2]" + person2.toString());

			sc.close();

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
