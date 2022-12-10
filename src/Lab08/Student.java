package Lab08;

public class Student {
    private String name;
    private int exam1;
    private int exam2;
    private int finalExam;
    private double finalGrade;
    private String letterGrade;
    
    /**
     * This function is constructor and sets up the given value to the class properties.
     * @param String name
     * @param int exam1
     * @param int exam2
     * @param int finalExam
     * @return
     */
    public Student(String name, int exam1, int exam2, int finalExam) {
        super();
        this.name = name;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.finalExam = finalExam;
        this.setFinalGrade();
        this.setLetterGrade();
    }
    
    /**
     * This function sets new value to the String property 'name' by given String value.
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * This function returns the String property 'name'.
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This function sets new value to the Integer property 'exam1' by given Integer value.
     * @return
     */
    public void setExam1(int exam1) {
        this.exam1 = exam1;
    }
    
    /**
     * This function returns the Integer property 'exam1'.
     * @return int exam1
     */
    public int getExam1() {
        return this.exam1;
    }


    /**
     * This function sets new value to the Integer property 'exam2' by given Integer value.
     * @return
     */
    public void setExam2(int exam2) {
        this.exam2 = exam2;
    }
    
    /**
     * This function returns the Integer property 'exam2'.
     * @return int exam2
     */
    public int getExam2() {
        return this.exam2;
    }

    /**
     * This function sets new value to the Integer property 'finalExam' by given Integer value.
     * @return
     */
    public void setFinalExam(int finalExam) {
        this.finalExam = finalExam;
    }

    /**
     * This function returns the Integer property 'finalExam'.
     * @return int finalExam
     */
    public int getFinalExam() {
        return this.finalExam;
    }

    /**
     * This function sets new value to the Double property 'finalGrade' by combining and calculating the average of given properties.
     * @return
     */
    public void setFinalGrade() {
        this.finalGrade = this.exam1*0.25 + this.exam2*0.25 + this.finalExam*0.5;
    }
    /**
     * This function returns the Double property 'finalGrade'.
     * @return double finalGrade
     */
    public double getFinalGrade() {
        return this.finalGrade;
    }

    /**
     * This function sets new value to the String property 'letterGrade' by the property 'finalGrade' using a specific given logic.
     * @return
     */
    public void setLetterGrade() {
        this.letterGrade =  
                (this.finalGrade >= 90) ? "A" : 
                    (this.finalGrade >= 80) ? "B" : 
                        (this.finalGrade >= 70) ? "C" : "F";
    }
    
    /**
     * This function returns the String property 'letterGrade'.
     * @return String letterGrade
     */
    public String getLetterGrade() {
        return letterGrade;
    }
    
    /**
     * This function returns out the student's grade info.
     * @return String toString
     */
    public String toString() {
        return String.format("%s received a grade of %.2f: %s",
                this.name, this.finalGrade, this.letterGrade);
    }

}
