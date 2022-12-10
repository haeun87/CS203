package Lab07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIOTester {


	public static void main(String[] args) {

		try {
		    Path resourceDirectory = Paths.get("src","Lab07");
	        String inputFileName = resourceDirectory.toAbsolutePath().toString()+"/tester.txt";
            String outputFileName = resourceDirectory.toAbsolutePath().toString()+"/output.txt";

			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(inputFileName)));
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputFileName)));

				String line; String[] strNums; int sum;

				while((line = br.readLine()) != null) {
					strNums = line.split(",");
					sum = 0;
					for(String nums: strNums) {
						sum += Integer.parseInt(nums);
					}
					bw.write(sum+"\n");
				}

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
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
