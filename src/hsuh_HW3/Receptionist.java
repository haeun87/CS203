package hsuh_HW3;

public class Receptionist extends Administrator {

	private String isAnswering;
	
	/**
	 * This function is constructor and set up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
 	 * @param String department
 	 * @param String isAnswering
	 * @return
	 */
	public Receptionist(String name, String blazerId, String department, String isAnswering) {
		super(name, blazerId, department);
		this.isAnswering = isAnswering;
	}
	
    /**
	 * This function returns the String property 'isAnswering'.
	 * @return String isAnswering
	 */
	public String getIsAnswering() {
		return this.isAnswering;
	}
	
	/**
     * This function sets new value to the String property 'isAnswering'.
     * @return
     */
	public void setIsAnswering(String isAnswering) {
        this.isAnswering = isAnswering;
    }
	
	/**
	 * This function returns out the Receptionist info.
	 * @return String toString
	 */
	public String toString() {
		return String.format("Name: %s BlazerId: %s Department: %s Answering: %s",
				this.getName(), this.getBlazerId(), this.getDepartment(), this.isAnswering);
	}

}
