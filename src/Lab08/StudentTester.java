package Lab08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StudentTester {


    public static void main(String[] args) {
        
        Path resourceDirectory = Paths.get("src/Lab08");
        String inputFileName = resourceDirectory.toAbsolutePath().toString()+"/studentList.txt";
        String outputFileName = resourceDirectory.toAbsolutePath().toString()+"/studentGrade.txt";
        ArrayList<Student> studentList = new ArrayList<Student>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(inputFileName)));
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputFileName)));

            String line; String[] data;

            while((line = br.readLine()) != null) {
                data = line.split(",");
                studentList.add(new Student(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
            br.close();
            
            for(Student student: studentList) {
                System.out.println(student.toString());
                bw.write(student.toString()+"\n");
            }
            bw.close();


        } catch (FileNotFoundException fnfx) {
            System.out.println("File is missing! Please recheck the path!");
            fnfx.printStackTrace();
        } catch(NumberFormatException ne) {
            System.out.println("Some Non Numberical values are included!");
            ne.printStackTrace();
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        

    }

}
