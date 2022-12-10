package hsuh_HW3;

public class Surgeon extends Doctor {
	
	private String isOperating;
	
	/**
	 * This function is constructor and set up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
 	 * @param String specialty
 	 * @param String isOperating
	 * @return
	 */
	public Surgeon(String name, String blazerId, String specialty, String isOperating) {
		super(name, blazerId, specialty);
		this.isOperating = isOperating;
	}
	
	/**
	 * This function returns the String property 'isOperating'.
	 * @return String isOperating
	 */
	public String getIsOperating() {
		return this.isOperating;
	}
	
    /**
     * This function sets new value to the String property 'isOperating'.
     * @return
     */
    public void setIsOperating(String isOperating) {
        this.isOperating = isOperating;
    }

    /**
	 * This function returns out the Surgeon info.
	 * @return String toString
	 */
	public String toString() {
		return String.format("Name: %s BlazerId: %s Speciality: %s Operating %s",
				this.getName(), this.getBlazerId(), this.getSpecialty(), this.isOperating);
	}

}
