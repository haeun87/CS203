package hsuh_HW3;

public class Doctor extends HospitalEmployee {
	
	private String specialty;
	
	/**
	 * This function is constructor and sets up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
 	 * @param String specialty
	 * @return
	 */
	public Doctor(String name, String blazerId, String specialty) {
		super(name, blazerId);
		this.specialty = specialty;
	}

	/**
	 * This function returns the String property 'specialty'.
	 * @return String specialty
	 */
	public String getSpecialty() {
		return this.specialty;
	}
	
	/**
     * This function sets new value to the String property 'specialty'.
     * @return
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
	
	/**
	 * This function returns out the Doctor info.
	 * @return String toString
	 */
	public String toString() {
		return String.format("Name: %s BlazerId: %s Specialty: %s",
				this.getName(), this.getBlazerId(), this.specialty);
	}
}
