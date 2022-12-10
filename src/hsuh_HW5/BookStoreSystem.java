package hsuh_HW5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * <h1>Class BookStoreSystem</h1>
 * <p>This is a Back-bone Class that includes the data manipulating functions.
 * Including functions that are inherited from interface 'BookStore', this class contains
 * some specific functions that supports the process of inherited functions. Main data lists are
 * stored within this class as private attributes. This class is a singleton class so only initiate
 * once and no duplicate instance will be existed.</p>
 * */
public class BookStoreSystem implements BookStore {

    private String absolutefilePath;
    
    private List<Book> bookList;
    private List<Customer> customerList;
    private List<Rental> rentalList;
    
    private DataType curDataType;
    private boolean refreshData;
    
    private Property prop;
    
    /**
     * <h1>SingletonHelper()</h1>
     * <P>This function initiates an instance of Class 'BookStoreSystem'.
     * Since the instance initiated as static final object, the initiation will occurred once and 
     * otherwise this function will return the initiated instance. 
     * This type of implements called as 'Bill Pugh Singleton Implementation'.</p>
     * <P>[Link] <a href="https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples">
     * Java Singleton Design Pattern Best Practices with Examples.</a>
     * </p>
     */
    private static class SingletonHelper{
        private static final BookStoreSystem INSTANCE = new BookStoreSystem();
    }

    /**
     * <h1>getInstance()</h1>
     * <P>This function is an actual getter function to be used from the other class.
     * This function internally calls SingletonHelper function to get the instance of Class 'BookStoreSystem'.</p>
     * @return BookStoreSystem instance
     */
    public static BookStoreSystem getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    /**
     * <h1>BookStoreSystem()</h1>
     * <P>This function is a constructor and initiates some class attributes.</p>
     */
    private BookStoreSystem() {
        this.bookList = new ArrayList<Book>();
        this.customerList = new ArrayList<Customer>();
        this.rentalList  = new ArrayList<Rental>();
        this.curDataType = DataType.BOOK;
        this.prop = Property.getInstance();
    }

    /**
     * <h1>getFilePath()</h1>
     * <p>This function assigns a retrieved variable to the String type class attribute 'absolutefilePath'.
     * This function search for a file by the given 'fileName' String type variable. If any has been found, 
     * the found file path will be transformed as a String variable then assigned to the 'absolutefilePath' variable.</p>
     * @param String fileName or null(If not exists)
     * @throws IOException
     */
    private void getFilePath(String fileName) throws IOException {
        this.absolutefilePath = null;
        Stream<Path> walkStream = Files.walk(Paths.get("."));
        walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
            if (f.toString().endsWith(fileName)) {
                this.absolutefilePath = f.toString();
            }
        });
        walkStream.close();
    }

    /**
     * <h1>loadBookList()</h1>
     * <p>This function loads a book list data, which is read and retrieved from the given file path, 
     * to the List<Book> type class attribute 'bookList'.</p>
     * @param String database
     * @throws NullPointerException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadBookList(String database) throws NullPointerException, FileNotFoundException, IOException {
        this.getFilePath(database); // Find and retrieve the absolute file path.
        BufferedReader br = new BufferedReader(new FileReader(this.absolutefilePath));

        this.prop.setDataBaseHeader(DataType.BOOK, br.readLine().split(",")); // Save Header.
        
        String line;
        while((line = br.readLine()) != null) {
               
            String[] info = line.split(",");
            
            /*
                 Column order of file like as below:
                 [0]: no [1]: title, [2]: author, [3]: numOfPages, [4]: publisher, 
                 [5]: yearPublished, [6]: numOfCopies, [7]: category
             */
            Book book = new Book(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), info[4], 
                    Integer.parseInt(info[5]), Integer.parseInt(info[6]), info[7]);
            
            this.bookList.add(book);
        }
        br.close();
    }

    /**
     * <h1>loadCustomerList()</h1>
     * <p>This function loads a customer list data, which is read and retrieved from the given file path, 
     * to the List<Customer> type class attribute 'bookList' and also to the List<Rental> type class attribute 'rentalList'.</p>
     * @param String database
     * @throws NullPointerException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadCustomerList(String database) throws NullPointerException, FileNotFoundException, IOException {
        this.getFilePath(database); // Find and retrieve the absolute file path.
        BufferedReader br = new BufferedReader(new FileReader(this.absolutefilePath));
          
        this.prop.setDataBaseHeader(DataType.CUSTOMER, br.readLine().split(",")); // Save Header.
        
        String line;
        while((line = br.readLine()) != null) {
            String[] info = line.split(",");
            String[] rentals = (info.length > 6) ? info[6].split(";") : null;
            int numOfRentals = (rentals != null) ? rentals.length:  0;
            
            /*
                 Column order of file like as below:
                 [0]: no [1]: firstName, [2]: lastName, [3]: emailAddress, [4]: phoneNumber, 
                 [5]: homeAddress, [6]: numOfRentals
            */
            Customer customer = new Customer(Integer.parseInt(info[0]), info[1], info[2], info[3], info[4], 
                    info[5], numOfRentals);
            
            this.customerList.add(customer);
            
            if(this.bookList.size() > 0 && numOfRentals > 0) {// Bring up each customer's rental list so to assign them to the rental list.
                for(String bookNo : rentals) {
                    Book book = this.findBookByNo(Integer.parseInt(bookNo));
                    if(book != null) {
                        this.rentalList.add(new Rental(customer, book));
                        book.setInventory(book.getInventory() - 1); // deduct rented book copy from the current inventory.
                        this.bookList.set(book.getBookNo()-1, book);
                    }
                }
            }     
        }
        Collections.sort(this.rentalList); // Sort rental list by customer's last name ascendantly.
        
        br.close();
    }

    /**
     * <h1>backUpFile()</h1>
     * <p>This function copies the original database files as renamed files before update them.
     * Renamed the files like as below:</p>
     * <p>[BACKUP](backup date and time, format:yyyyMMddHHmmss)<br>
     * e.g. 'BookList.txt' -> '[BACKUP]20221122113503_BookList.txt'
     * </p>
     * @param String database
     * @throws IOException
     */
    public void backUpFile(String database) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String backupStr = "[BACKUP]"+LocalDateTime.now().format(formatter)+"_";
        
        String dest = backupStr+database;
        Path original = Paths.get(this.absolutefilePath);

        Files.copy(original, original.resolveSibling(dest)); // Copy as the renamed file.
    }
  
    /**
     * <h1>savebookListToDB()</h1>
     * <p>This function saves all of the modification applied from the List<Book> class attribute 'bookList' to the given file path.</p>
     * @param String database
     * @throws NullPointerException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void savebookListToDB(String database) throws NullPointerException, FileNotFoundException, IOException {
        this.getFilePath(database); // Find and retrieve the absolute file path.
        
        this.backUpFile(database); // Back up the original file before processing.
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.absolutefilePath));

        String columns = String.join(",", this.prop.getDataBaseHeader(DataType.BOOK))+"\n"; // Get header.
        bw.write(columns); // Add header.
        
        for(Book book: this.bookList) {
            /*
                Column order of file like as below:
                [0]: no [1]: title, [2]: author, [3]: numOfPages, [4]: publisher, 
                [5]: yearPublished, [6]: numOfCopies, [7]: category
             */
            String row = String.format("%d,%s,%s,%d,%s,%d,%d,%s",
                    book.getBookNo(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getNumOfPages(),
                    book.getPublisher(),
                    book.getYearPublished(),
                    book.getNumOfCopies(),
                    book.getCategory())+"\n";
            bw.write(row);
        }
        bw.close();
    }
 
    /**
     * <h1>saveCustomerListToDB()</h1>
     * <p>This function saves all of the modification applied from the List<Customer> class attribute 'customerList'
     * (and as well as from List<Rental> class attribute 'rentalList') to the given file path.</p>
     * @param String database
     * @throws NullPointerException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveCustomerListToDB(String database) throws NullPointerException, FileNotFoundException, IOException {
        this.getFilePath(database); // Find and retrieve the absolute file path.
        
        this.backUpFile(database); // Back up the original file before processing.

        BufferedWriter bw = new BufferedWriter(new FileWriter(this.absolutefilePath));
        
        String columns = String.join(",", this.prop.getDataBaseHeader(DataType.CUSTOMER))+"\n"; // Get header.
        bw.write(columns); // Add header
        
        for(Customer customer: this.customerList) {
            // Update current rental list
            List<Integer> rentals = new ArrayList<Integer>();
            for(Rental rental : this.rentalList) {
                int no = rental.getCustomer().getCustomerNo();
                if(customer.getCustomerNo() == no) {
                    rentals.add(rental.getBook().getBookNo());
                }
            }
           
            // Transform Integer array to String variable.
            String rentalInfo = "";
            if(rentals.size()>0) {
                Collections.sort(rentals); // Sort the array first.
                for(int rental: rentals) {
                    rentalInfo += rental+";";
                }
                rentalInfo = rentalInfo.substring(0, rentalInfo.length()-1);
            }

            /*
            Column order of file like as below:
            [0]: no [1]: firstName, [2]: lastName, [3]: emailAddress, [4]: phoneNumber, 
            [5]: homeAddress, [6]: numOfRentals
             */
            String row = String.format("%d,%s,%s,%s,%s,%s,%s",
                    customer.getCustomerNo(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    rentalInfo)+"\n";
            bw.write(row);
        }
        bw.close();
    }

    /**
     * <h1>findBookByNo()</h1>
     * <p>This function searches and returns a Book object from List<Book> class attribute 'bookList' by 
     * the given Integer variable 'bNo'.</p>
     * @param int bNo - a book identifier number.
     * @return Book book or null(If not exists)
     */
    public Book findBookByNo(int bNo) {
        for(Book book : this.bookList) {
            if(book.getBookNo() == bNo) {
                return book;
            }
        }
        return null;
    }
    
    /**
     * <h1>findCustomerByNo()</h1>
     * <p>This function searches and returns a Customer object from List<Book> class attribute 'customerList' by 
     * the given Integer variable 'cNo'.</p>
     * @param int cNo - a customer identifier number.
     * @return Customer customer or null(If not exists)
     */
    public Customer findCustomerByNo(int cNo) {
        for(Customer customer : this.customerList) {
            if(customer.getCustomerNo() == cNo) {
                return customer;
            }
        }
        return null;
    }
    
    /**
     * <h1>findRentalByNo()</h1>
     * <p>This function searches and returns a Book object from List<Book> class attribute 'bookList' by 
     * the given String variable 'bNo'.</p>
     * @param String rNo - a rental identifier number combination.
     * @return Rental rental or null(If not exists)
     */
    public Rental findRentalByNo(String rNo) {
        for(Rental rental : this.rentalList) {
            if(rental.getRentelNo().equals(rNo)) {
               return rental;
            }
        }
        return null;
    }

    /**
     * <h1>setCurDataType()</h1>
     * <p>This function assigns a new value to the DataType attribute 'curDataType'.
     * This mutator changes a current type of view by the new assigned DataType variable.</p>
     * @param DataType dataType
     */
    @Override
    public void setCurDataType(DataType dataType) {
        this.curDataType = dataType;
    }

    /**
     * <h1>getCurDataType()</h1>
     * <p>This function returns the DataType attribute 'curDataType'.
     * This attribute represents a current view type of the UI program.</p>
     * @return DataType curDataType
     */
    @Override
    public DataType getCurDataType() {
        return this.curDataType;
    }
    
    /**
     * <h1>hasRequestRefresh()</h1>
     * <p>This function returns the boolean attribute 'refreshData'.
     * If this attribute is true, it means that refreshing of view has been requested.</p>
     * @return boolean refreshData
     */
    @Override
    public boolean hasRequestRefresh() {
        return this.refreshData;
    }

    /**
     * <h1>requestRefresh()</h1>
     * <p>This function sets a new value to the boolean attribute 'refreshData'.</p>
     * @param boolean refreshData
     */
    @Override
    public void requestRefresh(boolean refreshData) {
       this.refreshData = refreshData;
    }

    /**
     * <h1>setUpDatabase()</h1>
     * <p>This function processes load database to the system of save database by the given String variable 'type'.
     * Depending its request defined as 'type', this function will internally call the function regarding its requested process.</p>
     * @param String bookDatabase - a rental identifier number combination.
     * @param String customerDatabase - a rental identifier number combination.
     * @param String type - a rental identifier number combination.
     * @return boolean result - returns true when the process was successful. Otherwise, returns false.
     */
    @Override
    public boolean setUpDatabase(String bookDatabase, String customerDatabase, String type) {
        String path = null;
        try {
            path = bookDatabase;
            if(type.equals("LOAD")) {// Requested database loading.
                this.loadBookList(path);
                path = customerDatabase;
                this.loadCustomerList(path);
                return true;
            }
            else if(type.equals("SAVE")){// Requested database saving.
                this.savebookListToDB(path);
                path = customerDatabase;
                this.saveCustomerListToDB(path);
                return true;
            }
            System.out.printf("[Error] No such action exist! (Action: %s)\n",type);
            return false;
        }
        catch(NullPointerException | FileNotFoundException fnfe) {
            System.out.printf("[Warning] Database is missing! (Database filename: %s)\n", path);
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
        return false;
    }
     
    /**
     * <h1>getData()</h1>
     * <p>This function returns a data object by the given identifier number and data type.
     * It will internally discriminate the type of object by the given data type.</p>
     * @param String no
     * @param DataType dataType
     * @return T data - a generic type of object.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getData(String no, DataType dataType) {
        switch(dataType) {
            case BOOK:
                return (T) this.findBookByNo(Integer.parseInt(no));
            case CUSTOMER:
                return (T) this.findCustomerByNo(Integer.parseInt(no));
            case RENTAL:
                return (T) this.findRentalByNo(no);
            default:
                return null;
        }
    }
    
    /**
     * <h1>getDataList()</h1>
     * <p>This function returns a data object list by the given data type.
     * It will internally discriminate the type of object by the given data type.</p>
     * @param DataType dataType
     * @return List<T> dataList- a generic type of object list.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getDataList(DataType dataType) {
        switch(dataType) {
            case BOOK:
                return (List<T>) this.getBookList();
            case CUSTOMER:
                return (List<T>) this.getCustomerList();
            case RENTAL:
                return (List<T>) this.getRentalList();
            default:
                return null;
        }
    }
     
    /**
     * <h1>getBookList()</h1>
     * <p>This function returns a List<Book> class attribute 'bookList'.</p>
     * @return List<Book> bookList
     */
    @Override
    public List<Book> getBookList() {
        return this.bookList;
    }

    /**
     * <h1>getCustomerList()</h1>
     * <p>This function returns a List<Customer> class attribute 'customerList'.</p>
     * @return List<Customer> customerList
     */
    @Override
    public List<Customer> getCustomerList() {
        return this.customerList;
    }
    
    /**
     * <h1>getRentalList()</h1>
     * <p>This function returns a List<Rental> class attribute 'rentalList'.</p>
     * @return List<Rental> rentalList
     */
    @Override
    public List<Rental> getRentalList() {
        return this.rentalList;
    }
        
    /**
     * <h1>findBookByTitle()</h1>
     * <p>This function returns a List<Book> variable 'books' that contains certain part of the given String 
     * variable from the class attribute 'bookList'. It returns as a list variable since multiple objects
     * can be matched for the condition.</p>
     * @return String title - full title or partial part of title for the target books.
     * @return List<Book> books
     */
    @Override
    public List<Book> findBookByTitle(String title) {
        List<Book> books = new ArrayList<Book>();
        for(Book book : this.bookList) {
            if(book.getTitle().contains(title)) {
                books.add(book);
            }
        }
        return books;
    }

    /**
     * <h1>findCustomersByName()</h1>
     * <p>This function returns a List<Customer> variable 'customers' that contains certain part of the given String 
     * variable from the class attribute 'customerList'. It returns as a list variable since multiple objects
     * can be matched for the condition.</p>
     * @return String name - full name of partial part of name for the target customers.
     * @return List<Customer> customers
     */
    @Override
    public List<Customer> findCustomersByName(String name) {
        List<Customer> customers = new ArrayList<Customer>();
        for(Customer customer : this.customerList) {
            String fullName = String.format("%s %s", customer.getFirstName(), customer.getLastName());
            if(fullName.contains(name)) {
                customers.add(customer);
            }
        }
        return customers;
    }
      
    /**
     * <h1>findBooksByLastName()</h1>
     * <p>This function returns a List<Rental> variable 'rentals' that contains certain part of the given String 
     * variable from the class attribute 'rentalList'. It returns as a list variable since multiple objects
     * can be matched for the condition.</p>
     * @return String lastName - full lastName of partial part of lastName for the target customers who are currently renting any books.
     * @return List<Rental> rentals
     */
    @Override
    public List<Rental> findBooksByLastName(String lastName) {
        List<Rental> rentals = new ArrayList<Rental>();
        for(Rental rental: this.rentalList) {
            if(rental.getCustomer().getLastName().equals(lastName)) {
                rentals.add(rental);
            }
        }
        return rentals;
    }
    
    /**
     * <h1>rentBook()</h1>
     * <p>This function checks whether a customer of the given identifier number can rent the book of the given identifier number.
     * If approved, the function creates new rental objects and add to the correspondent list and reflect the change to inventory.
     * If not approved, the function will return the Integer type identifier that indicates the reason.</p>
     * @return int bNo
     * @return int cNo
     * @return int result - returns 1 for rental approved, returns 0 when rental rejected, and returns -1 when not enough inventory.
     */
    @Override
    public int rentBook(int bNo, int cNo) {
        Book book = this.findBookByNo(bNo);
        Customer customer = this.findCustomerByNo(cNo);
       if(book.getInventory() < 1) {
           return -1; // Not enough inventory.
       }

       List<Rental> rentals = this.findBooksByLastName(customer.getLastName());
       for(Rental rental : rentals) {
           if(rental.getBook().getTitle() == book.getTitle()) {
               return 0; // No rental for same book to same customer.
           }
       }

       book.setInventory(book.getInventory() - 1); // Deduct from inventory.
       this.bookList.set(book.getBookNo()-1, book); // Update object.
       
       customer.setNumOfRentals(customer.getNumOfRentals()+1); // Increase the number of rentals of the customer.
       this.customerList.set(customer.getCustomerNo()-1, customer); // Update object.
       
       this.rentalList.add(new Rental(customer, book)); // Add new rental object.
       
       Collections.sort(this.rentalList);
       
       return 1; // Rental success.
    }

    /**
     * <h1>retrieveBook()</h1>
     * <p>This function receives the number identifier that represents the rental to be retrieved.
     * Then, it processes as to remove old rental object from rental list and to reflect regarding changes to other attributes.</p>
     * @return String rNo
     */
    @Override
    public void retrieveBook(String rNo) {
        Rental rental = this.findRentalByNo(rNo);

        Book book = rental.getBook();
        book.setInventory(book.getInventory() + 1); // Add to inventory.
        this.bookList.set(book.getBookNo()-1, book); // Update object.
        
        Customer customer = rental.getCustomer();
        customer.setNumOfRentals(customer.getNumOfRentals()-1); // Decrease the number of rentals of the customer.
        this.customerList.set(customer.getCustomerNo()-1, customer); // Update object.
        
        this.rentalList.remove(rental); // Remove from the rental list.
    }    
}