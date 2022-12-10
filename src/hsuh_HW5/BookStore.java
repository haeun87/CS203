package hsuh_HW5;

import java.util.List;

/**
 * <h1>Interface BookStore</h1>
 * <p>This is an Interface that defines functions to be implemented in the future.
 * Class 'BookStoreSystem' implements this interface and is encapsulated by this interface.
 * Detail explanation for the functions below will be skipped as to be explained within 
 * its implemented class.</p>
 * */
public interface BookStore {
        
    public void setCurDataType(DataType dataType);
    public DataType getCurDataType();
    
    public boolean hasRequestRefresh();
    public void requestRefresh(boolean refresh);
    
    public boolean setUpDatabase(String bookDatabase, String customerDatabase, String actionType);
    
    public <T> T getData(String no, DataType dataType);
    public <T> List<T> getDataList(DataType dataType);
    
    public List<Book> getBookList();
    public List<Customer> getCustomerList();
    public List<Rental> getRentalList();
    
    public List<Book> findBookByTitle(String title);
    public List<Customer> findCustomersByName(String name);
    public List<Rental> findBooksByLastName(String lastName);
    
    public int rentBook(int bookNo, int customerNo);
    public void retrieveBook(String rentalNo);
    
}