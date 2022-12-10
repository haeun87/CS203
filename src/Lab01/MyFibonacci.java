package Lab01;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyFibonacci {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                System.out.print("Please Enter the integer number: ");
                int num = sc.nextInt();
                int num1 = 0, num2 = 1;
                for(int i = 0; i < num; i++){
                    System.out.print(num1+" ");
                    int num3 = num2 + num1;
                    num1 = num2;
                    num2 = num3;
                }
                break;
            }catch(InputMismatchException ime){
                System.out.println("Please Enter the integer number Only!!");
                sc.nextLine();
            }
        }
        sc.close();
    }
}
