package hsuh_HW5;

/**
 * <h1>Class BookStoreTester</h1>
 * <p>This Class is a Main Class of this program. If there's no error exists reading data files, 
 * main function of this class will invoke run() function to activate the GUI program.</p>
 * */
public class BookStoreTester {

    public static void main(String[] args) {
        try {
            Property prop = Property.getInstance(); // Initiates from this point.
            BookStore store = BookStoreSystem.getInstance(); // Initiates from this point.
            
            // Initially, check if database will successfully read. Only continue processing when it was successful.
            if(store.setUpDatabase(prop.getDatabase(DataType.BOOK), prop.getDatabase(DataType.CUSTOMER), "LOAD")) {
                new BookStoreGUI<Object>().run(); // Trigger to activate a Main GUI.
            }
            else{ // In case of error, raise Execution to terminate the process.
                throw new Exception("[INFO] Database Error. Please recheck the files!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
