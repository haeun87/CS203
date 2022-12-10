package Lab03;

public class UABPerson {

	private String name;
	private String gender;
	private int age;
	private String blazerID;

	public UABPerson(String name, String gender, int age, String blazerID) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.blazerID = blazerID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void setBlazerID(String id) {
		this.blazerID = id;
	}

	public String getBlazerID() {
		return this.blazerID;
	}

	public boolean checkPalindrome() {
		String originalStr = this.name.toLowerCase();
		String reverseStr = "";
		int lastIdx = originalStr.length() - 1;
		for(int i =lastIdx; i >= 0; i--) {
			reverseStr = reverseStr + originalStr.toLowerCase().charAt(i);
		}
		return (reverseStr.equals(originalStr)) ? true: false;
	}

	public int yearsUntilRetirement() {
		return 65 - this.age;
	}

	@Override
	public String toString() {
		return this.name + " is " + this.age + " years old." ;
	}
}
