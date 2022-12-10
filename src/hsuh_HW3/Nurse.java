package hsuh_HW3;

public class Nurse extends HospitalEmployee {
	
	private String numOfPatients;
	
	/**
	 * This function is constructor and set up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
 	 * @param String numOfPatients
	 * @return
	 */
	public Nurse(String name, String blazerId, String numOfPatients) {
		super(name, blazerId);
		this.numOfPatients = numOfPatients;
	}

	/**
	 * This function returns the String property 'numOfPatients'.
	 * @return String numOfPatients
	 */	
	public String getNumOfPatients() {
		return this.numOfPatients;
	}
	
    /**
     * This function sets new value to the String property 'numOfPatients'.
     * @return
     */
	public void setNumOfPatients(String numOfPatients) {
        this.numOfPatients = numOfPatients;
    }

    /**
	 * This function returns out the Nurse info.
	 * @return String toString
	 */
	public String toString() {
		return String.format("Name: %s BlazerId: %s Number of Patients: %s",
				this.getName(), this.getBlazerId(), this.numOfPatients);
	}
}
