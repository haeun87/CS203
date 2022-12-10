package hsuh_HW4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentTester {


    /**
     * This function loads file data from the given path.
     * @param Student student - Student class instance
     * @param String dataFileName - File path of the target file
     * @param boolean hasHeader - Whether the file has its header
     */
    public static void readStudentData(Student student, String dataFileName, boolean hasHeader) 
            throws FileNotFoundException, ArrayIndexOutOfBoundsException, 
            NullPointerException, NumberFormatException, IOException {
        student.readStudentData(dataFileName, hasHeader);
    }
    
    /**
     * This function prints the total number and names of all existing advisors from the college.
     * @param Student student - Student class instance
     */
    public static void displayAllAdv(Student student) {
        Set<String> allAdvisors = student.getAllAdvisors();
        System.out.printf("Total number of advisors: %d\n", allAdvisors.size());
        System.out.printf("Names of advisors: %s\n", String.join(", ", allAdvisors));
    }
    
    /**
     * This function prints the information of students that are having lower GPA than given GPA. 
     * @param Student student - Student class instance
     * @param double gpa - Standard GPA that will be compared with each student's GPA
     */
    public static void diaplayUnderGPAList(Student student, double gpa) {
        List<StudentImpl> underPerformed = student.getGPAUnderThan(gpa);
        System.out.printf("Students having gpa under %.2f like as below:\n\n", gpa);
        for(StudentImpl std: underPerformed) {
            System.out.println(std.toString());
        }
    }
    
    /**
     * This function prints the average credit Hours as a college level.
     * @param Student student - Student class instance
     */
    public static void displayAvgCH(Student student) {
       System.out.printf("The Average credit hours for the college: %f\n", student.getAvgCH());
    }
    
    /**
     * This function prints the average GPA of the given department.
     * @param Student student - Student class instance
     * @param String department - The target department to calculate its GPA average
     */
    public static void displayAvgGPAByMajor(Student student, String department) {
        System.out.printf("The Average GPA of the students whose department is %s: %f", 
                department, student.getAvgGPAByDept(department));
        System.out.println();
    }
    
    /**
     * This function prints the number of advisors from each department.
     * @param Student student - Student class instance
     */
    public static void displayNumOfAdvByDept(Student student) {
        Map<String, Integer> advsByDept = student.getAllNumOfAdvByMajor();
        
        for(String department : advsByDept.keySet()) {
            System.out.printf("Total number of advisors from %s : %d\n", department, advsByDept.get(department));
        }
    }

    public static void main(String[] args) {
        
        // 1. Initiate variables
        StudentImpl student = new StudentImpl();
        String dataFileName = Paths.get("src","hsuh_HW4","students_dataset.csv").toAbsolutePath().toString();
        boolean hasHeader = true; // For file loading
        Double gpa = 2.75; // For Solution 3
        String department = "Computer Science"; // For Solution 4
        
        try {
            // 2. Read and load file data
            readStudentData(student, dataFileName, hasHeader);
            
            // 3. Execute the solution codes
            
            // Question 1. How many advisors do we have? Display their names.
            System.out.println("========== Solution 1 ==========");
            displayAllAdv(student);
            System.out.println();
            
            // Question 2. What is the full information of the students whose GPA is less than 2.75?
            System.out.println("========== Solution 2 ==========");
            diaplayUnderGPAList(student, gpa);
            System.out.println();
            
            // Question 3. What are the average credit hours of the college?
            System.out.println("========== Solution 3 ==========");
            displayAvgCH(student);
            System.out.println();
            
            // Question 4. What is the average GPA of the students whose department is Computer Science?
            System.out.println("========== Solution 4 ==========");
            displayAvgGPAByMajor(student, department);
            System.out.println();
            
            // Question 5. Display the list of departments along with the total number of advisors.
            System.out.println("========== Solution 5 ==========");
            displayNumOfAdvByDept(student);
            
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.out.println("Please check the file path again!");
          
        } catch (ArrayIndexOutOfBoundsException | 
                NullPointerException | NumberFormatException formatError) {
            formatError.printStackTrace();
            System.out.println("Please check the file format again!");
        
        } catch (Exception ioError) {
            ioError.printStackTrace();
        }
    }
}
