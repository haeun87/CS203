package hsuh_HW5;

/**
 * <h1>VO Class Customer</h1>
 * <p>This class is a Value Object Class that defines all attributes regarding the 'Customer' Object.</p>
 * */
public class Customer {

    private int cNo;
    private String fName;
    private String lName;
    private String email;
    private String pNum;
    private String address;
    private int nRentals;
    
    /**
     * <h1>Customer()</h1>
     * <P>This function is a constructor and sets up the given value to the class attributes.</p>
     * @param int cNo
     * @param String fName
     * @param String lName
     * @param String email
     * @param String pNum
     * @param String address
     * @param int nRentals
     */
    public Customer(int cNo, String fName, String lName, String email, String pNum, String address, int nRentals) {
        this.cNo = cNo;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.pNum = pNum;
        this.address = address;
        this.nRentals = nRentals;
    }
    
    /**
     * <h1>getCustomerNo()</h1>
     * <p>This function returns an Integer attribute 'cNo'.
     * This attribute represents the number identifier of a customer.</p>
     * @return int cNo
     */
    public int getCustomerNo() {
        return this.cNo;
    }
    
    /**
     * <h1>getFirstName()</h1>
     * <p>This function returns a String attribute 'fName'. 
     * This attribute represents the first name of a customer.</p>
     * @return String fName
     */
    public String getFirstName() {
        return this.fName;
    }
    
    /**
     * <h1>getLastName()</h1>
     * <p>This function returns a String attribute 'lName'. 
     * This attribute represents the last name of a customer.</p>
     * @return String lName
     */
    public String getLastName() {
        return this.lName;
    }
    
    /**
     * <h1>getEmail()</h1>
     * <p>This function returns a String attribute 'email'. 
     * This attribute represents the email address of a customer.</p>
     * @return String email
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * <h1>getPhoneNumber()</h1>
     * <p>This function returns a String attribute 'pNum'. 
     * This attribute represents the phone number of a customer.</p>
     * @return String pNum
     */
    public String getPhoneNumber() {
        return this.pNum;
    }
    
    /**
     * <h1>getAddress()</h1>
     * <p>This function returns a String attribute 'address'. 
     * This attribute represents the home address of a customer.</p>
     * @return String address
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * <h1>getNumOfRentals()</h1>
     * <p>This function returns an Integer attribute 'nRentals'. 
     * This attribute represents the number of books that currently rented by a customer.</p>
     * @return int nRentals
     */
    public int getNumOfRentals() {
        return this.nRentals;
    }

    /**
     * <h1>setNumOfRentals()</h1>
     * <p>This function sets a new value to the Integer attribute 'nRentals'.
     * This mutator represents a change in the number of books that currently rented by a customer.</p>
     * @param int nRentals
     */
    public void setNumOfRentals(int nRentals) {
        this.nRentals = nRentals;
    }
    
    /**
     * <h1>toString()</h1>
     * <p>This function returns a String object 'toString'. 
     * The returned String will contain the attributes of a Customer Class.</p>
     * @return Strong toString
     */
    @Override
    public String toString() {
        return String.format("%d,%s %s,%s,(%s)%s-%s,%s,%d", 
                this.cNo, 
                this.fName,
                this.lName, 
                this.email,
                this.pNum.substring(0,3),
                this.pNum.substring(3,6),
                this.pNum.substring(6),
                this.address,
                this.nRentals);
    }

}
