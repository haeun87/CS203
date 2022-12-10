package hsuh_HW3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This interface defines functions to be implemented.
 */
public interface Employee {
    
    public abstract void loadDatabase(String dataFileName)throws FileNotFoundException, ArrayIndexOutOfBoundsException, IOException;
    public abstract boolean add(List<String> empInfo, boolean IsSystemUpdate, Scanner sc);
    public abstract boolean delete(String role, String blazerId);
    public abstract boolean updateDatabase(String dataFileName);
    public abstract void display();
}
