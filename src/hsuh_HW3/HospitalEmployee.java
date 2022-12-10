package hsuh_HW3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HospitalEmployee implements Employee {
     
    private String name;
    private String blazerId;
        
    private ArrayList<HospitalEmployee> E;
    
    private ArrayList<Doctor> D;
    private ArrayList<Surgeon> S;
    private ArrayList<Nurse> N;

    private ArrayList<Administrator> A;
    private ArrayList<Janitor> J;
    private ArrayList<Receptionist> R;
    private ArrayList<ITEmployee> I;
    
    private int totEmpNum;

    /**
     * This function returns the String property 'E'.
     * @return String department
     */
    public ArrayList<HospitalEmployee> getE() {
        return E;
    }

    /**
     * This function sets new value to the String property 'E'.
     * @return
     */
    public void setE(ArrayList<HospitalEmployee> e) {
        this.E = e;
    }

    /**
     * This function returns the String property 'D'.
     * @return String department
     */
    public ArrayList<Doctor> getD() {
        return this.D;
    }

    /**
     * This function sets new value to the String property 'D'.
     * @return
     */
    public void setD(ArrayList<Doctor> d) {
        this.D = d;
    }

    /**
     * This function returns the String property 'S'.
     * @return String department
     */
    public ArrayList<Surgeon> getS() {
        return this.S;
    }

    /**
     * This function sets new value to the String property 'S'.
     * @return
     */
    public void setS(ArrayList<Surgeon> s) {
        this.S = s;
    }

    /**
     * This function returns the String property 'N'.
     * @return String department
     */
    public ArrayList<Nurse> getN() {
        return this.N;
    }

    /**
     * This function sets new value to the String property 'N'.
     * @return
     */
    public void setN(ArrayList<Nurse> n) {
        this.N = n;
    }

    /**
     * This function returns the String property 'A'.
     * @return String department
     */
    public ArrayList<Administrator> getA() {
        return this.A;
    }

    /**
     * This function sets new value to the String property 'A'.
     * @return
     */
    public void setA(ArrayList<Administrator> a) {
        this.A = a;
    }

    /**
     * This function returns the String property 'J'.
     * @return String department
     */
    public ArrayList<Janitor> getJ() {
        return this.J;
    }

    /**
     * This function sets new value to the String property 'J'.
     * @return
     */
    public void setJ(ArrayList<Janitor> j) {
        this.J = j;
    }

    /**
     * This function returns the String property 'R'.
     * @return String department
     */
    public ArrayList<Receptionist> getR() {
        return this.R;
    }

    /**
     * This function sets new value to the String property 'R'.
     * @return
     */
    public void setR(ArrayList<Receptionist> r) {
        this.R = r;
    }

    /**
     * This function returns the String property 'I'.
     * @return String department
     */
    public ArrayList<ITEmployee> getI() {
        return this.I;
    }

    /**
     * This function sets new value to the String property 'I'.
     * @return
     */
    public void setI(ArrayList<ITEmployee> i) {
        this.I = i;
    }

    /**
     * This function returns the int property 'totEmpNum'.
     * @return String department
     */
    public int getTotEmpNum() {
        return this.totEmpNum;
    }

    /**
     * This function sets new value to the int property 'totEmpNum'.
     * @return
     */
    public void setTotEmpNum(int totEmpNum) {
        this.totEmpNum = totEmpNum;
    }

    /**
     * This function returns the String property 'name'.
     * @return String name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * This function sets new value to the String property 'name'.
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * This function returns the String property 'blazerId'.
     * @return String blazerId
     */
    public String getBlazerId() {
        return this.blazerId;
    }
    
    /**
     * This function sets new value to the String property 'blazerId'.
     * @return
     */
    public void setBlazerId(String blazerId) {
        this.blazerId = blazerId;
    }
    
    /**
     * This function initiates the properties.
     * @return
     */
    public HospitalEmployee() {
        this.E = new ArrayList<HospitalEmployee>();
        
        this.D = new ArrayList<Doctor>();
        this.S = new ArrayList<Surgeon>();
        this.N = new ArrayList<Nurse>();
        
        this.A = new ArrayList<Administrator>();
        this.J = new ArrayList<Janitor>();
        this.R = new ArrayList<Receptionist>();
        this.I = new ArrayList<ITEmployee>();
        
        this.totEmpNum = 0;
        
    }
    
	/**
	 * This function is constructor and set up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
	 * @return
	 */
	public HospitalEmployee(String name, String blazerId) {
        this.name = name;
        this.blazerId = blazerId;
	}

    /**
     * This function loads the data to corresponding array list from given text file path.
     * @param String dataFileName - The file path to be read.
     * @return
     */  
    @Override
    public void loadDatabase(String dataFileName) throws FileNotFoundException, ArrayIndexOutOfBoundsException, IOException  {
        BufferedReader br = new BufferedReader(new FileReader(new File(dataFileName)));
        String line; String[] empInfo;
        while((line = br.readLine()) != null) {
            empInfo = line.split(" ");
            this.add((List<String>) Arrays.asList(empInfo), true, null);
        }
        br.close();
        
    }
    
    /**
     * This function adds new employee to its corresponding array list 
     * @param List<String> empInfo - An employee data given from database. null for user managed add request.  
     * @param boolean IsSystemUpdate - It will be true for database upload and false for user managed add request.
     * @param Scanner sc - Used for user managed add request.
     * @return boolean isAdded - Returns whether the employee information has reflected to its corresponding array list.
     */
    @Override
    public boolean add(List<String> empInfo, boolean IsSystemUpdate, Scanner sc) {
        
        switch(Role.valueOf(empInfo.get(0))) {
            
            case E:
                if(!IsSystemUpdate) {
                    for(HospitalEmployee hospEmp: this.E) {
                        if(empInfo.get(2).equals(hospEmp.getBlazerId())){
                            System.out.println("The employee named '" + empInfo.get(1) + "', blazerId '" + empInfo.get(2) + "' is currently exists!");
                            return false;
                        }
                    }
                }
                HospitalEmployee hospEmp = new HospitalEmployee(empInfo.get(1), empInfo.get(2));
                this.E.add(hospEmp);
                break;
 
            case D:
                if(!IsSystemUpdate) {
                    for(Doctor doc: this.D) {
                        if(empInfo.get(2).equals(doc.getBlazerId())){
                            System.out.println("The employee named '" + empInfo.get(1) + "', blazerId '" + empInfo.get(2) + "' is currently exists!");
                            return false;
                        }
                    }
                    System.out.print("The New Employee "+ empInfo.get(1) + "'s Speciality: ");
                    empInfo.add(sc.nextLine().trim());
                }
                Doctor doc = new Doctor(empInfo.get(1), empInfo.get(2), empInfo.get(3));
                this.D.add(doc);
                break;

            case S:
                if(!IsSystemUpdate) {
                    for(Surgeon sr: this.S) {
                        if(empInfo.get(2).equals(sr.getBlazerId())){
                            System.out.println("The employee named '"+empInfo.get(1)+"', blazerId '"+empInfo.get(2)+"' is currently exists!");
                            return false;
                        }
                    }
                    System.out.print("The New Employee " + empInfo.get(1)+ "'s Speciality: ");
                    empInfo.add(sc.nextLine().trim());
                    System.out.print("Whether the New Employee " + empInfo.get(1)+ "'s Role is Operating(Y/N): ");
                    empInfo.add(sc.nextLine().trim().toUpperCase());
                }
                Surgeon sr = new Surgeon(empInfo.get(1), empInfo.get(2), empInfo.get(3), empInfo.get(4));
                this.S.add(sr);
                break;

            case N:
                if(!IsSystemUpdate) {
                    for(Nurse ns: this.N) {
                        if(empInfo.get(2).equals(ns.getBlazerId())){
                            System.out.println("The employee named '"+empInfo.get(1)+"', blazerId '"+empInfo.get(2)+"' is currently exists!");
                            return false;
                        }
                    }
                    System.out.print("The Number of Patients to be assigned to New Employee "+ empInfo.get(1) + ": ");
                    empInfo.add(sc.nextLine().trim());
                }
                Nurse ns = new Nurse(empInfo.get(1), empInfo.get(2), empInfo.get(3));
                this.N.add(ns);
                break;
                
            case A:
                if(!IsSystemUpdate) {
                    for(Administrator admin: this.A) {
                        if(empInfo.get(2).equals(admin.getBlazerId())){
                            System.out.println("The employee named '"+empInfo.get(1)+"', blazerId '" + empInfo.get(2) + "' is currently exists!");
                            return false;
                        }
                    }
                    System.out.print("The New Employee " + empInfo.get(1) + "'s Department: ");
                    empInfo.add(sc.nextLine().trim());
                }
                Administrator admin = new Administrator(empInfo.get(1), empInfo.get(2), empInfo.get(3));
                this.A.add(admin);
                break;

            case J:
                if(!IsSystemUpdate) {
                    for(Janitor js: this.J) {
                        if(empInfo.get(2).equals(js.getBlazerId())){
                            System.out.println("The employee named '"+empInfo.get(1)+"', blazerId '"+empInfo.get(2)+"' is currently exists!");
                            return false;
                        }
                    }
                    System.out.print("The New Employee "+empInfo.get(1)+"'s Department: ");
                    empInfo.add(sc.nextLine().trim());
                    System.out.print("Whether the New Employee " + empInfo.get(1) + "'s Role is Sweeping(Y/N): ");
                    empInfo.add(sc.nextLine().trim().toUpperCase());
                }
                Janitor js = new Janitor(empInfo.get(1), empInfo.get(2), empInfo.get(3), empInfo.get(4));
                this.J.add(js);
                break;
             
            case R:
                if(!IsSystemUpdate) {
                    for(Receptionist rec: this.R) {
                        if(empInfo.get(2).equals(rec.getBlazerId())){
                            System.out.println("The employee named '"+empInfo.get(1)+"', blazerId '"+empInfo.get(2)+"' is currently exists!");
                            return false;
                        }
                    }
                    System.out.print("The New Employee " + empInfo.get(1) + "'s Department: ");
                    empInfo.add(sc.nextLine().trim());
                    System.out.print("Whether the New Employee " + empInfo.get(1) + "'s Role is Answering(Y/N): ");
                    empInfo.add(sc.nextLine().trim().toUpperCase());
                }
                Receptionist rcpt = new Receptionist(empInfo.get(1), empInfo.get(2), empInfo.get(3), empInfo.get(4));
                this.R.add(rcpt);
                break;
                
            case I:
                if(!IsSystemUpdate) {
                    for(ITEmployee it: this.I) {
                        if(empInfo.get(2).equals(it.getBlazerId())){
                            System.out.println("The employee named '"+empInfo.get(1)+"', blazerId '" + empInfo.get(2) + "' is currently exists!");
                            return false;
                        }
                    }
                    System.out.println("*** Enter the Team as a Char like as below ***");
                    System.out.println("D: Developers, W: Web services, N: Networking");
                    System.out.print("The New Employee " + empInfo.get(1) + "'s Team: ");
                    empInfo.add(sc.nextLine().trim().toUpperCase());
                }
                ITEmployee it = new ITEmployee(empInfo.get(1), empInfo.get(2), empInfo.get(3));
                this.I.add(it);
                break;   
                
        }
        
        this.totEmpNum++;
        
        return true;
    }
 
    /**
     * This function deletes the employee from its corresponding array list by user input data.
     * @param String role - Role of the employee to be deleted.
     * @param String blazerId - BlazerId of the employee to be deleted.
     * @return boolean isDeleted - Returns whether the employee information has deleted from its corresponding array list.
     */
    @Override
    public boolean delete(String role, String blazerId) {
        switch(Role.valueOf(role)) {
            
            case E:
                for(HospitalEmployee hospEmp: this.E) {
                    if(blazerId.equals(hospEmp.getBlazerId())){
                        this.E.remove(hospEmp);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;
                
            case D:
                for(Doctor doc: this.D) {
                    if(blazerId.equals(doc.getBlazerId())){
                        this.D.remove(doc);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;    
            
            case S:
                for(Surgeon sr: this.S) {
                    if(blazerId.equals(sr.getBlazerId())){
                        this.S.remove(sr);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;

            case N:
                for(Nurse ns: this.N) {
                    if(blazerId.equals(ns.getBlazerId())){
                        this.N.remove(ns);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;
                
            case A:
                for(Administrator admin: this.A) {
                    if(blazerId.equals(admin.getBlazerId())){
                        this.A.remove(admin);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;


            case J:
                for(Janitor js: this.J) {
                    if(blazerId.equals(js.getBlazerId())){
                        this.J.remove(js);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;
            
            case R:
                for(Receptionist rec: this.R) {
                    if(blazerId.equals(rec.getBlazerId())){
                        this.R.remove(rec);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;
                
            case I:
                for(ITEmployee it: this.I) {
                    if(blazerId.equals(it.getBlazerId())){
                        this.I.remove(it);
                        this.totEmpNum--;
                        return true;
                    }
                }
                break;                      
                
        }
        
        return false;
    }
    
 
    /**
     * This function writes the data from array lists to given text file path.
     * @param String dataFileName - The file path to be written.
     * @return isUpdated - Return whether the employee information has deleted from its corresponding array list.
     */
    @Override
    public boolean updateDatabase(String dataFileName){

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(dataFileName))));

            for(HospitalEmployee hospEmp: this.E) {
                pw.write("E "+ hospEmp.getName() + " " + hospEmp.getBlazerId() + "\n");
            }
            
            for(Doctor doc: this.D) {
                pw.write("D "+ doc.getName() + " " + doc.getBlazerId()+ " " + doc.getSpecialty() + "\n");
            }

            for(Surgeon sr: this.S) {
                pw.write("S "+ sr.getName() + " " + sr.getBlazerId()+ " " + sr.getSpecialty()+ " " + sr.getIsOperating() + "\n");
            }
            
            for(Nurse ns: this.N) {
                pw.write("N "+ ns.getName() + " " + ns.getBlazerId()+ " " + ns.getNumOfPatients() + "\n");
            }
            
            for(Administrator admin: this.A) {
                pw.write("A "+ admin.getName() + " " + admin.getBlazerId()+ " " + admin.getDepartment() + "\n");
            }
            
            for(Receptionist rcpt: this.R) {
                pw.write("R "+ rcpt.getName() + " " + rcpt.getBlazerId()+ " " + rcpt.getDepartment() + " " + rcpt.getIsAnswering() + "\n");
            }
            
            for(Janitor js: this.J) {
                pw.write("J "+ js.getName() + " " + js.getBlazerId()+ " " + js.getDepartment() + " " + js.getIsSweeping() + "\n");
            }
            
            for(ITEmployee it: this.I) {
                pw.write("I "+ it.getName() + " " + it.getBlazerId()+ " " + it.getTeam() + "\n");
            }
            
            pw.flush();
            pw.close();
            
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    /**
     * This function prints out the employee list from each corresponding array lists.
     * @return
     */
    @Override
    public void display() {
        System.out.println("*****************************************");
        System.out.println("** Welcome to the UAB Employee System! **");
        System.out.println("*****************************************");
        System.out.println();
        
        System.out.println("The UAB Hospital System has the following employees:\n");
        
        System.out.printf("Total Number of employees=%d\n\n", this.totEmpNum);
        
        System.out.printf("Hospital Employees: %d\n", this.E.size());
        for(HospitalEmployee hospEmp: this.E) {
            System.out.println(hospEmp.toString());
        }
        System.out.println();
        
        System.out.printf("Doctors: %d\n", this.D.size());
        for(Doctor doc: this.D) {
            System.out.println(doc.toString());
        }
        System.out.println();
        
        System.out.printf("Surgeons: %d\n", this.S.size());
        for(Surgeon sr: this.S) {
            System.out.println(sr.toString());
        }
        System.out.println();
        
        System.out.printf("Nurses: %d\n", this.N.size());
        for(Nurse ns: this.N) {
            System.out.println(ns.toString());
        }
        System.out.println();
        
        System.out.printf("Administrators: %d\n", this.A.size());
        for(Administrator admin: this.A) {
            System.out.println(admin.toString());
        }
        System.out.println();
        
        System.out.printf("Receptionist: %d\n", this.R.size());
        for(Receptionist rcpt: this.R) {
            System.out.println(rcpt.toString());
        }
        System.out.println();
        
        System.out.printf("Janitors: %d\n", this.J.size());
        for(Janitor js: this.J) {
            System.out.println(js.toString());
        }
        System.out.println();
        
        System.out.printf("IT Team: %d\n", this.I.size());
        for(ITEmployee it: this.I) {
            System.out.println(it.toString());
        }
        System.out.println();
        
    }
    
    /**
     * This function returns out the HospitalEmployee info.
     * @return String toString
     */
    @Override
    public String toString() {
        return String.format("Name: %s BlazerId: %s", this.getName(), this.getBlazerId());
    }
    
}

