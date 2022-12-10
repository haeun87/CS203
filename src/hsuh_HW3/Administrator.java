package hsuh_HW3;

public class Administrator extends HospitalEmployee {
	
	private String department;
	
	/**
	 * This function is constructor and sets up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
 	 * @param String department
	 * @return
	 */
	public Administrator(String name, String blazerId, String department) {
		super(name, blazerId);
		this.department = department;
	}

	/**
	 * This function returns the String property 'department'.
	 * @return String department
	 */
	public String getDepartment() {
		return this.department;
	}

    /**
     * This function sets new value to the String property 'department'.
     * @return
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
	/**
	 * This function returns out the Administrator info.
	 * @return String toString
	 */
	public String toString() {
		return String.format("Name: %s BlazerId: %s Department: %s",
				this.getName(), this.getBlazerId(), this.department);
	}
}
