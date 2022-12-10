package hsuh_HW5;

/**
 * <h1>VO Class Book</h1>
 * <p>This class is a Value Object Class that defines all attributes regarding the 'Book' Object.</p>
 * */
public class Book {

    private int bNo;
    private String title;
    private String author;
    private int page;
    private String publisher;
    private int year;
    private int copy;
    private String category;
    private int inventory;
    
    /**
     * <h1>Book()</h1>
     * <P>This function is a constructor and sets up the given value to the class attributes.</p>
     * @param int bNo
     * @param String title
     * @param String author
     * @param int page
     * @param String publisher
     * @param int year
     * @param int copy
     * @param String category
     */
    public Book(int bNo, String title, String author, int page, String publisher, int year, int copy,
            String category){
        this.bNo = bNo;
        this.title = title;
        this.author = author;
        this.page = page;
        this.publisher = publisher;
        this.year = year;
        this.copy = copy;
        this.category = category;
        this.inventory = copy; // Initially saved as many as the number of copies but will be modified later on.
    }
    
    /**
     * <h1>getBookNo()</h1>
     * <p>This function returns an Integer attribute 'bNo'.
     * This attribute represents the number identifier of a book.</p>
     * @return int bNo
     */
    public int getBookNo() {
        return this.bNo;
    }

    /**
     * <h1>getTitle()</h1>
     * <p>This function returns a String attribute 'title'.
     * This attribute represents the title of a book.</p>
     * @return String title
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * <h1>getAuthor()</h1>
     * <p>This function returns a String attribute 'author'.
     * This attribute represents the author's name of a book.</p>
     * @return String author
     */
    public String getAuthor() {
        return this.author;
    }
    
    /**
     * <h1>getNumOfPages()</h1>
     * <p>This function returns an Integer attribute 'page'.
     * This attribute represents the total number of pages of a book.</p>
     * @return int page
     */
    public int getNumOfPages() {
        return this.page;
    }
    
    /**
     * <h1>getPublisher()</h1>
     * <p>This function returns a String attribute 'publisher'.
     * This attribute represents the publisher's title of a book.</p>
     * @return String publisher
     */
    public String getPublisher() {
        return this.publisher;
    }
        
    /**
     * <h1>getYearPublished()</h1>
     * <p>This function returns an Integer attribute 'year'.
     * This attribute represents the published year of a book.</p>
     * @return String year
     */
    public int getYearPublished() {
        return this.year;
    }
        
    /**
     * <h1>getNumOfCopies()</h1>
     * <p>This function returns an Integer attribute 'copy'.
     * This attribute represents the current number of copies of a book that the store possesses.</p>
     * @return int copy
     */
    public int getNumOfCopies() {
        return this.copy;
    }
        
    /**
     * <h1>getCategory()</h1>
     * <p>This function returns a String attribute 'category'.
     * This attribute represents the classification of a book.</p>
     * @return String category
     */
    public String getCategory() {
        return this.category;
    }
    
    /**
     * <h1>getInventory()</h1>
     * <p>This function returns an Integer attribute 'inventory'. 
     * This attribute represents the number of copies of a book that is currently available for rent.</p>
     * @return int inventory
     */
    public int getInventory() {
        return this.inventory;
    }

    /**
     * <h1>setInventory()</h1>
     * <p>This function sets a new value to the Integer attribute 'inventory'.
     * This mutator represents the change in current stock of a store.</p>
     * @param int inventory
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    /**
     * <h1>toString()</h1>
     * <p>This function returns a String object 'toString'. 
     * The returned String will contain the attributes of a Book Class.</p>
     * @return Strong toString
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%d,%s,%d,%d",
                this.bNo,
                this.title,
                this.author,
                this.publisher,
                this.year,
                this.category,
                this.page,
                this.inventory);
    }
}