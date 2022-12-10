package hsuh_HW4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class defines actual functions and properties to be executed. 
 * Also, Encapsulated by the Student interface.
 */
public class StudentImpl implements Student {

    private String name;
    private String major;
    private String degree;
    private double gpa;
    private int creditHours;
    private String ta;
    private String advisor;
    
    private Map<String,List<StudentImpl>> studentList;
    private Set<String> departments;
    
    /**
     * This function initiates the properties.
     */
    public StudentImpl() {
        this.studentList = new HashMap<String,List<StudentImpl>>();
        this.departments = new HashSet<String>();

    }
    
    /**
     * This function is a constructor and set up the given value to the class properties.
     * @param String name
     * @param String major
     * @param String degree
     * @param double gpa
     * @param int creditHours
     * @param String ta
     * @param String advisor
     */
    public StudentImpl(String name, String major, String degree, double gpa, 
            int creditHours, String ta, String advisor) {
        this.name = name;
        this.major = major;
        this.degree = degree;
        this.gpa = gpa;
        this.creditHours = creditHours;
        this.ta = ta;
        this.advisor = advisor;
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
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function returns the String property 'major'.
     * @return String major
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * This function sets new value to the String property 'major'.
     * @param String major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * This function returns the String property 'degree'.
     * @return String degree
     */
    public String getDegree() {
        return this.degree;
    }

    /**
     * This function sets new value to the String property 'degree'.
     * @param String degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    /**
     * This function returns the double property 'gpa'.
     * @return double gpa
     */
    public double getGpa() {
        return this.gpa;
    }

    /**
     * This function sets new value to the double property 'gpa'.
     * @param double gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * This function returns the int property 'creditHours'.
     * @return int creditHours
     */
    public int getCreditHours() {
        return this.creditHours;
    }

    /**
     * This function sets new value to the int property 'creditHours'.
     * @param int creditHours
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     * This function returns the String property 'ta'.
     * @return String ta
     */
    public String getTa() {
        return this.ta;
    }

    /**
     * This function sets new value to the String property 'ta'.
     * @param String ta
     */
    public void setTa(String ta) {
        this.ta = ta;
    }

    /**
     * This function returns the String property 'advisor'.
     * @return String advisor
     */
    public String getAdvisor() {
        return this.advisor;
    }

    /**
     * This function sets new value to the String property 'advisor'.
     * @param String advisor
     */
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    /**
     * This function returns the Map<String, List<StudentImpl>> property 'studentList'.
     * @return Map<String, List<StudentImpl>> studentList
     */
    public Map<String, List<StudentImpl>> getStudentList() {
        return this.studentList;
    }

    /**
     * This function sets new value to the Map<String, List<StudentImpl>> property 'studentList'.
     * @param Map<String, List<StudentImpl>> studentList
     */
    public void setStudentList(Map<String, List<StudentImpl>> studentList) {
        this.studentList = studentList;
    }

    /**
     * This function returns the Set<String> property 'departments'.
     * @return Set<String> departments
     */
    public Set<String> getDepartments() {
        return this.departments;
    }

    /**
     * This function sets new value to the Set<String> property 'departments'.
     * @param Set<String> departments
     */
    public void setDepartments(Set<String> departments) {
        this.departments = departments;
    }
    
    /**
     * This function loads student info from the file with the given path.
     * @param String filePath
     * @param boolean hasHeader
     */
    @Override
    public void readStudentData(String filePath, boolean hasHeader)
            throws FileNotFoundException, ArrayIndexOutOfBoundsException, 
            NullPointerException, NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line; 
        Boolean isHeader = hasHeader;
        while((line = br.readLine()) != null) {
            
            if(isHeader) { // Skip the header
                isHeader = false;
                continue;
            }
            
            String[] student = line.split(",");
            
            String department = student[2].trim().toLowerCase();
            
            if(!studentList.containsKey(department)) {
                studentList.put(department, new ArrayList<StudentImpl>());
                this.departments.add(department);
            }
            
            List<StudentImpl> list = studentList.get(department);
            
            /*
                 Column order of file like as below:
                 [0] First Name, [1]: Last Name, [2]: Major, [3]: Degree, [4]: GPA, [5]: Credit Hours, [6]: TA, [7]: Advisor
             */
            list.add(
                    new StudentImpl(
                            student[0].trim()+" "+student[1].trim(), 
                            student[2].trim(), 
                            student[3].trim(),
                            Double.parseDouble(student[4].trim()),
                            Integer.parseInt(student[5].trim()), 
                            student[6].trim(), 
                            student[7].trim()
                    ));
            
            this.studentList.replace(department, list);

        }
        br.close();
        
    }

    /**
     * This function returns a non duplicated list of advisors as a college level.
     * @return Set<String>  allAdvisors
     */
    @Override
    public Set<String> getAllAdvisors() {
        HashSet<String> allAdvisors = new HashSet<String>();
        for(String department : this.departments) {
            allAdvisors.addAll(this.getAdvisorsByDept(department));
        }
        return allAdvisors;
    }

    /**
     * This function returns a non duplicated list of advisors from given department.
     * @param String department
     * @return Set<String>  advisorsByDept
     */
    @Override
    public Set<String> getAdvisorsByDept(String department) {
        Set<String> advisorsByMajor = new HashSet<String>();
        List<StudentImpl> students = this.studentList.get(department);
        for(StudentImpl student: students) {
            advisorsByMajor.add(student.getAdvisor());
        }
        return advisorsByMajor;
    }

    /**
     * This function returns a list of students that have lower GPA than the given GPA.
     * @param double gpa
     * @return List<StudentImpl> underPerformed
     */
    @Override
    public List<StudentImpl> getGPAUnderThan(double gpa) {
        List<StudentImpl> underPerformed = new ArrayList<StudentImpl>();
        for(String department : this.departments) {
            List<StudentImpl> students = this.studentList.get(department);
            for(StudentImpl student : students) {
                if(student.gpa < gpa) underPerformed.add(student);
            }
        }
        return underPerformed;
    }

    /**
     * This function returns an average of Credit Hours as a college level.
     * @return double avgCreditHours
     */
    @Override
    public double getAvgCH() {
        int sum = 0;
        int size = 0;
        for(String department : this.departments) {
            List<StudentImpl> students = this.studentList.get(department);
            size += students.size();
            for(StudentImpl student : students) {
                sum += student.getCreditHours();
            }
        }
        return (size != 0) ? (double) sum/size : 0;
    }

    /**
     * This function returns an average of GPA from the given department name.
     * @param String department
     * @return double avgGPAByDept
     */
    @Override
    public double getAvgGPAByDept(String department) {
        double sum = 0;
        
        List<StudentImpl> students = this.studentList.get(department.toLowerCase());
        int size = students.size();
        
        for(StudentImpl student : students) {
            sum += student.getGpa();
        }

        return (size != 0) ? sum/size : 0;
    }
    
    /**
     * This function returns a list of Strings that each of them contains department name and the number of advisors within it.
     * @return Map<String, Integer> advsByDept
     */
    @Override
    public Map<String, Integer> getAllNumOfAdvByMajor() {
        Map<String, Integer> advisors = new HashMap<String, Integer>();
        for(String department : this.departments) {
            advisors.put(department, this.getAdvisorsByDept(department).size());
        }
        return advisors;
    }
    
    /**
     * This function returns out the StudentImpl info.
     * @return String toString
     */
    @Override
    public String toString() {
        return String.format("Name: %s Major: %s Degree: %s GPA: %.2f Credit Hours: %d TA: %s Advior: %s",
                this.getName(), this.getMajor(), 
                this.getDegree(), this.getGpa(), this.getCreditHours(), 
                this.getTa(), this.getAdvisor()
                );
    }
}
