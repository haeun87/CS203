package hsuh_HW3;

public class ITEmployee extends HospitalEmployee {
	
	private String team;
	
	/**
	 * This function is constructor and set up the given value to the class properties.
 	 * @param String name
 	 * @param String blazerId
 	 * @param String team
	 * @return
	 */
	public ITEmployee(String name, String blazerId, String team) {
		super(name, blazerId);
		this.team = team;
	}

	/**
	 * This function returns the String property 'team'.
	 * @return String team
	 */
	public String getTeam() {
		return this.team; 
	}
	
    /**
     * This function sets new value to the String property 'team'.
     * @return
     */
	public void setTeam(String team) {
        this.team = team;
    }

    /**
	 * This function returns out the ITEmployee info.
	 * @return String toString
	 */
	public String toString() {
		String teamName = 
				(team.equals("D")) ? "Developers" :
				(team.equals("W")) ? "Web services" : 
				(team.equals("N")) ? "Networking" : team;
		return String.format("Name: %s BlazerId: %s Team: %s", this.getName(), this.getBlazerId(), teamName);
	}
}
