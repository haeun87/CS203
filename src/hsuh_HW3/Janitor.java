package hsuh_HW3;

public class Janitor extends Administrator {
	
	private String isSweeping;
	
	/**
	 * This function is constructor and set up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
 	 * @param String department
 	 * @param String isSweeping
	 * @return
	 */
	public Janitor(String name, String blazerId, String department, String isSweeping) {
		super(name, blazerId, department);
		this.isSweeping = isSweeping;
	}

	/**
	 * This function returns the String property 'isSweeping'.
	 * @return String isSweeping
	 */
	public String getIsSweeping() {
		return this.isSweeping;
	}
	
    /**
     * This function sets new value to the String property 'isSweeping'.
     * @return
     */
	public void setIsSweeping(String isSweeping) {
        this.isSweeping = isSweeping;
    }

    /**
	 * This function returns out the Janitor info.
	 * @return String toString
	 */	
	public String toString() {
		return String.format("Name: %s BlazerId: %s Department: %s Sweeping: %s",
				this.getName(), this.getBlazerId(), this.getDepartment(), this.isSweeping);
	}

}
