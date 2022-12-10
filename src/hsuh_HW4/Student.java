package hsuh_HW4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This interface defines functions to be implemented. 
 * Also, Encapsulates the StudentImpl class
 */
public interface Student {

    public abstract void readStudentData(String filePath,  boolean hasHeader) 
            throws FileNotFoundException, ArrayIndexOutOfBoundsException, NullPointerException, NumberFormatException, IOException;
    
    public abstract Set<String> getAllAdvisors();
    public abstract Set<String> getAdvisorsByDept(String department);
    public abstract List<StudentImpl> getGPAUnderThan(double gpa);
    public abstract double getAvgCH();
    public abstract double getAvgGPAByDept(String department);
    public abstract Map<String, Integer> getAllNumOfAdvByMajor();
    
}
