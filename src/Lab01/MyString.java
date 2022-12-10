package Lab01;
import java.util.Scanner;

public class MyString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Please Enter any letters: ");
                String letter = sc.nextLine();
                int midLen = letter.length() / 2;
                System.out.println("The middle letter of given string: " + letter.charAt(midLen));
                break;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please Enter any letters!!");
            }
        }
        sc.close();
    }
}
