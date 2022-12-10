package hsuh_HW5;

/**
 * <h1>VO Class Rental</h1>
 * <p>This class is a Value Object Class that defines all attributes regarding the 'Rental' Object.
 * This class implements Comparable interface as to be sort from some functions.</p>
 * */
public class Rental implements Comparable<Rental> {

    private String rNo;
    private Customer customer;
    private Book book;
    
    /**
     * <h1>Rental()</h1>
     * <P>This function is a constructor and sets up the given value to the class attributes.</p>
     * @param Customer customer
     * @param Book book
     */
    public Rental(Customer customer, Book book) {
        this.rNo = book.getBookNo()+":"+customer.getCustomerNo(); // Initiate as the combination of book number and customer number
        this.customer = customer;
        this.book = book;
    }

    /**
     * <h1>getRentelNo()</h1>
     * <p>This function returns a String attribute 'rNo'.
     * This attribute represents the combination identifier of a rental.</p>
     * @return String cNo
     */
    public String getRentelNo() {
        return this.rNo;
    }
    
    /**
     * <h1>getCustomer()</h1>
     * <p>This function returns a Customer object 'customer'.
     * This attribute represents the customer who rent a book.</p>
     * @return Customer customer
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * <h1>getBook()</h1>
     * <p>This function returns a Book object 'book'.
     * This attribute represents the book which is rent.</p>
     * @return Book book
     */
    public Book getBook() {
        return this.book;
    }
        
    /**
     * <h1>toString()</h1>
     * <p>This function returns a String object 'toString'. 
     * The returned String will contain the attributes of a Rental Class.</p>
     * @return Strong toString
     */
    @Override
    public String toString() {
        return String.format("%s,%s %s,%s,%s", 
                this.rNo, 
                this.customer.getFirstName(),
                this.customer.getLastName(),
                this.book.getTitle(),
                this.book.getAuthor());
    }

    /**
     * <h1>compareTo()</h1>
     * <p>This function returns an Integer variable 'compareTo'. 
     * This function compares the last name of each Rental object.
     * It returns -1 when the comparison is bigger then original, returns 1 when the original is 
     * bigger than the comparison, and returns 0 when they are the same.</p>
     * @param Rental rental - a comparison object to the original.
     * @return int compareTo
     */
    @Override
    public int compareTo(Rental rental) {
        return this.getCustomer().getLastName()
                .compareTo(rental.getCustomer().getLastName());
    }
    
    
}
