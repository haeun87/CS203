package hsuh_HW3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeTest {
       
    /**
     * This function initiates the variables and executes the employee management system menu.
     * @param String dataFileName - Database file path
     * @param Employee employee - Employee class instance
     * @return
     */
    public static void run(String dataFileName, HospitalEmployee employee) throws IOException {

        // Variable used for termination
        Boolean isTerminating = false;
        
        Scanner sc = new Scanner(System.in);
        
        // Start menu
        while(true) {
            try {
                System.out.println("========= MENU SELECTION ================");
                System.out.println("1. Display Employee List");
                System.out.println("2. Add New Employee");
                System.out.println("3. Update DataBase");
                System.out.println("4. Delete Employee");
                System.out.println("5. Terminate Menu");
                System.out.println("=========================================");
                System.out.println();
                System.out.print("SELECT: ");
                int sel = Integer.parseInt(sc.nextLine().trim());
                System.out.println();

                
                switch(sel) {
                    case 1:
                        System.out.println("========= Display Employee List =========");
                        System.out.println();
                        employee.display();
                        break;
                        
                    case 2:
                        System.out.println("========= Add New Employee ==============");
                        System.out.println();
                        ArrayList<String>empInfo = new ArrayList<String>();
                        System.out.println("*** Enter the Role as a Char like as below ***");
                        System.out.println("A: Administrator, D: Doctor, E: Hospital Employee,");
                        System.out.println("I: IT professinal N: Nurse, R: Receptionist, S: Surgeon");
                        System.out.println();
                        System.out.print("The New Employee's Role: ");
                        
                        empInfo.add(sc.nextLine().trim().toUpperCase());
                        
                        System.out.print("The New Employee's Name: ");
                        empInfo.add(sc.nextLine().trim());
                        
                        System.out.print("The New Employee's BlazerId: ");
                        empInfo.add(sc.nextLine().trim());
                        
                        boolean isAdded = employee.add(empInfo, false, sc);
                        
                        if(isAdded) {
                            System.out.println("The new employee successfully added!");
                        }
                        System.out.println();
                        
                        break;
                        
                    case 3:
                        System.out.println("========= Update DataBase ===============");
                        System.out.println();
                        boolean isUpdated = employee.updateDatabase(dataFileName);
                        if(isUpdated) {
                            System.out.println("*****************************************");
                            System.out.println("********** DataBase Updated!! ***********");
                            System.out.println("*****************************************");

                        }
                        else {
                            System.out.println("Due to the unexpected error, fail to update the database. Please contact to the administrator.");
                        }
                        System.out.println();
                        break;
                        
                    case 4:
                        System.out.println("========= Delete Employee ===============");
                        System.out.println();
                        System.out.println("*** Enter the Role as a Char like as below ***");
                        System.out.println("A: Administrator, D: Doctor, E: Hospital Employee,");
                        System.out.println("I: IT professinal N: Nurse, R: Receptionist, S: Surgeon");
                        System.out.println();
                        
                        System.out.print("Role of The Employee to be Deleted: ");
                        String role = sc.nextLine().trim().toUpperCase();
                        
                        System.out.print("BlazerId of The Employee to be Deleted: ");
                        String blazerId = sc.nextLine().trim();
                        
                        boolean isDeleted = employee.delete(role, blazerId);
                        if(isDeleted) {
                            System.out.println("The employee is successfully deleted!");
                        }
                        else {
                            System.out.println("The employee doese not exists!");
                        }
                        System.out.println();
                        break;
                        
                    case 5:
                        System.out.println("========= Terminate Menu ================");
                        System.out.println();
                        System.out.println("*****************************************");
                        System.out.println("**** Thank you for using our system! ****");
                        System.out.println("*****************************************");
                        System.out.println();
                        isTerminating = true;
                        break;
                        
                    default:
                        System.out.println("*** Please ENTER the VALID INTEGER NUMBER from 1 to 5! ***");
                        break;
                }
                System.out.println("=========================================");
                System.out.println();
                if(isTerminating) {
                    break;
                }

            }
            catch(NumberFormatException nf) {
                System.out.println();
                System.out.println("*** Please ENTER the INTEGER NUMBER from 1 to 5! ***");
                System.out.println();
            }
        }

        sc.close();
    }
    
    public static void main(String[] args) {
        
        // 1. Initiate variables
        String dataFileName = Paths.get("src","hsuh_HW3","uabEmployee.txt").toAbsolutePath().toString();
        HospitalEmployee employee = new HospitalEmployee();

        try {
            // 2. Load initial database
            employee.loadDatabase(dataFileName);
            
            // 3. Run menu
            run(dataFileName, employee);
            
        } catch (ArrayIndexOutOfBoundsException ae) {
            ae.printStackTrace();
        } catch (FileNotFoundException fe) {
            System.out.println("File does not exists! Check if the path is correct.");
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        

    }

}
