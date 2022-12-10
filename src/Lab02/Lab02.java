package Lab02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lab02 {

public static void fileAnalyze(String inputFileName, String outputFileName) {

		try {
			File inputFile = new File(inputFileName);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));

			File outFile = new File(outputFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
			String appendStr = "";
			String line; int num;
			int count = 0; int min = 0; int max = 0; int sum = 0;

			while((line = br.readLine()) != null) {
				num = Integer.parseInt(line);
				appendStr += num+"\n";
				if (num < min) {
					min = num;
				}
				else if(num > max) {
					max = num;
				}

				count ++;
				sum += num;
			}
			int avg = (sum != 0) ? sum/count : 0;

			appendStr +=
					"********\n"
					+"There are " + count + " numbers in the file\n"
					+"The minimum number is " + min + "\n"
					+"Thme maximum number is " + max + "\n"
					+"The average is "+ avg;

			bw.write(appendStr);

			bw.close();
			br.close();

		} catch (FileNotFoundException fnfx) {
			fnfx.printStackTrace();
		} catch(NumberFormatException ne) {
			System.out.println("Some Non Numberical values are included!");
			ne.printStackTrace();
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public static String sec2Days(int n) {
		String result = "";
		int temp = n;
		int ss = temp % 60;
		temp = (temp - ss) / 60; // turn into minutes
		int mm = temp % 60;
		temp = (temp - mm) / 60; // turn into hours
		int hh = temp % 24;
		temp = (temp - hh) / 24; // turn into days
		int d = temp;

		result = (ss < 10) ? ":0" + ss + result: ":"+ ss + result;
		result = (mm < 10) ? ":0" + mm + result: ":"+ mm + result;
		result = (hh < 10) ? ":0" + hh + result: ":"+ hh + result;
		result = d+ result;

		return result;
	}

	public static int consonantCount(String s) {
		int count = 0;
		String result = s.toLowerCase().replaceAll("[[aiueoy]||\\W||\\d]", "");
		count = result.length();
		return count;
	}

	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in);
			/*
			 * fileAnalyze Test
			 */
			String inputFileName = "C:\\Users\\cetun\\eclipse-workspace\\CS203\\src\\Lab02\\inputFile.txt";
			String outputFileName = "C:\\Users\\cetun\\eclipse-workspace\\CS203\\src\\Lab02\\outputFile.txt";
			fileAnalyze(inputFileName, outputFileName);

			/*
			 * sec2Days Test
			 */
			System.out.print("[sec2Days]Enter the total seconds(integer): ");
			int second = Integer.valueOf(sc.nextLine());
			System.out.println(sec2Days(second));
//			lab2 example
//			int n = 750000;
//			System.out.println(sec2Days(n));
//			n = 1234;
//			System.out.println();
//			System.out.println(sec2Days(n));
//			n = 200000;
//			System.out.println();
//			System.out.println(sec2Days(n));


			/*
			 * consonantCount Test
			 */
//			lab2 example
//			String s = "abra cadabra";
//			System.out.println(consonantCount(s));
//			s = "how many consonants?";
//			System.out.println(consonantCount(s));
//			s = "This is Lab2, folks";
//			System.out.println(consonantCount(s));
			System.out.print("[consonantCount]Enter the text(String): ");
			String text = sc.nextLine();
			System.out.println(consonantCount(text));

			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
