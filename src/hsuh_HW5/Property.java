package hsuh_HW5;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Property Class Property</h1>
 * <p>This class defines the predefined properties for the program. In this program, the properties are 
 * declared within this specific designed class 'Property' instead of using external property configure files.
 * In this class, size, label, font, color, column names of each view, title, and content messages has been defined.</p>
 * <p>This class is Singleton Class and only limited modification of this class will be allowed during the program running.
 * */
public class Property {
    
    private Map<DataType, String> databases;
    private Map<String, int[]> sizes;
    private Map<String, String> labels;
    private Map<String, Color> colors;
    private Map<String, Font> fonts;
    private Map<DataType, String> toolTips;
    private Map<DataType, String[]> tableHeaders;
    private Map<DataType, String[]> dataBaseHeaders;
    
    /**
     * <h1>SingletonHelper()</h1>
     * <P>This function initiates an instance of Class 'Property'.
     * Since the instance initiated as static final object, the initiation will occurred once and 
     * otherwise this function will return the initiated instance. 
     * This type of implements called as 'Bill Pugh Singleton Implementation'</p>
     * <P>[Link] <a href="https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples">
     * Java Singleton Design Pattern Best Practices with Examples.</a>
     * </p>
     */
    public static class SingletonHelper{
        private static final Property INSTANCE = new Property();
    }

    /**
     * <h1>getInstance()</h1>
     * <P>This function is an actual getter function to be used from the other class.
     * This function internally calls SingletonHelper function to get the instance of Class 'Property'</p>
     * @return Property instance
     */
    public static Property getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    /**
     * <h1>Property()</h1>
     * <P>This function is a constructor and initiates and presets some class attributes.</p>
     * <P>The attributes initiated from this constructor are the properties that should be preset 
     * before running the program.</p>
     */
    private Property() {
        // Database path.
        this.databases = new HashMap<DataType, String>();
        this.databases.put(DataType.BOOK, "BookList.txt");
        this.databases.put(DataType.CUSTOMER, "CustomerList.txt");
        
        // Size of components.
        this.sizes = new HashMap<String, int[]>();
        this.sizes.put("Frame", new int[]{800, 400});
        this.sizes.put("Search", new int[]{20});
        this.sizes.put("DetailGUI", new int[]{350, 250});
        this.sizes.put("RentalGUI", new int[]{300, 150});
        
        
        // Labels & Titles.
        this.labels = new HashMap<String, String>();
        this.labels.put("Frame", "CS 203 HW5 - Book Store");
        this.labels.put("Header", "Welcome to CS203 Book Store!!");
        this.labels.put("Footer", "©2022 Haeun Suh. All rights reserved.");
        
        this.labels.put("BtnB", "BOOKLIST");
        this.labels.put("BtnC", "CUSTOMERLIST");
        this.labels.put("BtnR", "RENTALLIST");
        
        this.labels.put("BtnS", "SEARCH");
        this.labels.put("BtnL", "ALLLIST");
        
        this.labels.put("BtnX", "CLOSE");
        this.labels.put("BtnY", "RENT");
        this.labels.put("BtnN", "CANCEL");

        this.labels.put("LabelS", "SEARCH");
        this.labels.put("LabelR", "[Choose Customer's Name]");
        
        this.labels.put("PopupTitle", "DETAIL");  
        this.labels.put("RentalTitle", "[RENT]");
        this.labels.put("ReturnTitle", "RETURN RESULT");
        
        this.labels.put("RetrieveTitle", "RETRIEVE BOOK");     
        this.labels.put("RetrieveContent", "Retrive this book?");
        
        this.labels.put("NoInputTitle", "NO INPUT");
        this.labels.put("NoInputContent", "Please ENTER ANY KEYWORD before search!");
        
        this.labels.put("NoInventoryTitle", "OUT OF INVENTORY");
        this.labels.put("NoInventoryContent", "Unable to rent. This book is currently out of inventory!");
        
        this.labels.put("NoDuplicateTitle", "DUPLICATED REQEUST");
        this.labels.put("NoDuplicateContent", "Unable to rent. This book has already rent from the customer!");
        
        this.labels.put("RentSuccessTitle", "RENTAL SUCCESS");
        this.labels.put("RentSuccessContent", "Rental approved for the book!");      
        
        this.labels.put("UpdateDBTitle", "SAVE DATABASE");
        this.labels.put("UpdateDBContent", "Save changes to Database?");
        
        this.labels.put("UpdateSuccessTitle", "UPDATE SUCCESS");
        this.labels.put("UpdateSuccessContent", "Update Database Successfully!");
        
        this.labels.put("UpdateFailTitle", "UPDATE FAILED");
        this.labels.put("UpdateFailContent", "Database update failed. Please contact the administrator");
        
        this.labels.put("DetailMTitle", "View Detail");
        this.labels.put("RentMTitle", "Rent This Book");
        this.labels.put("ReturnMTitle", "Return This Book");
                
        // Colors.
        this.colors = new HashMap<String, Color>();
        this.colors.put("Theme", new Color(118, 185, 71));
        this.colors.put("TextArea", new Color(198, 205, 193));
        this.colors.put("GeneralFont", new Color(68, 64, 63));
        this.colors.put("GuideFont", new Color(141, 56, 62));

        // Fonts.
        this.fonts = new HashMap<String, Font>();
        this.fonts.put("Title", new Font("Papyrus", Font.BOLD, 20));
        this.fonts.put("Subtitle", new Font("Papyrus", Font.BOLD, 15));
        this.fonts.put("Button", new Font("Papyrus", Font.BOLD, 15));
        this.fonts.put("Combobox", new Font("Tahoma", Font.PLAIN, 15));
        this.fonts.put("SearchBar", new Font("Tahoma", Font.BOLD, 15));
        this.fonts.put("Table", new Font("Tahoma", Font.PLAIN, 10));
        this.fonts.put("TextArea", new Font("Tahoma", Font.BOLD, 12));
        
        // ToolTips.
        this.toolTips = new HashMap<DataType, String>();
        this.toolTips.put(DataType.BOOK, "Enter the title of the book to search for.");
        this.toolTips.put(DataType.CUSTOMER, "Enter the name of the customer to search for.");
        this.toolTips.put(DataType.RENTAL, "Enter the last name of the customer to search for.");
        
        // View Table Headers.
        this.tableHeaders = new HashMap<DataType, String[]>();
        this.tableHeaders.put(DataType.BOOK, new String[]{"NO", "TITLE", "AUTHOR", "PUBLISHER", "YEAR", "CATEGORY", "PAGE", "INVENTORY"});
        this.tableHeaders.put(DataType.CUSTOMER, new String[]{"NO", "CUSTOMER NAME", "EMAIL", "PHONE", "ADDRESS", "RENTALS"});
        this.tableHeaders.put(DataType.RENTAL, new String[]{"RENTAL NO", "CUSTOMER NAME","BOOK TITLE", "BOOK AUTHOR"});
        
        // Database Table Headers: These contents will be set while loading the regarding text files.
        this.dataBaseHeaders = new HashMap<DataType, String[]>();
    }

    /**
     * <h1>getDatabase()</h1>
     * <p>This function retrieves and returns a String variable 'database' from the Map<DataType, String> attribute 'databases'. 
     * The returned value represents the database file path of the given data type.</p>
     * @param DataType dataType
     * @return String database
     */
    public String getDatabase(DataType dataType) {
        return this.databases.get(dataType);
    }

    /**
     * <h1>getSize()</h1>
     * <p>This function retrieves and returns an Integer array variable 'size' from the Map<String, int[]> attribute 'sizes'. 
     * The returned value represents the dimension or single-size coordinates for the given type of UI component.</p>
     * @param String type
     * @return int[] size
     */
    public int[] getSize(String type) {
        return this.sizes.get(type);
    }

    /**
     * <h1>getLabel()</h1>
     * <p>This function retrieves and returns a String variable 'label' from the Map<String, String> attribute 'sizes'. 
     * The returned value represents the title, label, or message text for the given type of UI component.</p>
     * @param String type
     * @return String label
     */
    public String getLabel(String type) {
        return this.labels.get(type);
    }

    /**
     * <h1>getColor()</h1>
     * <p>This function retrieves and returns a Color variable 'color' from the Map<String, Color> attribute 'colors'. 
     * The returned value represents the color property for the given type of UI component.</p>
     * @param String type
     * @return String color
     */
    public Color getColor(String type) {
        return this.colors.get(type);
    }

    /**
     * <h1>getFont()</h1>
     * <p>This function retrieves and returns a Font variable 'font' from the Map<String, Font> attribute 'fonts'. 
     * The returned value represents the font property for the given type of UI component.</p>
     * @param String type
     * @return String font
     */
    public Font getFont(String type) {
        return this.fonts.get(type);
    }

    /**
     * <h1>getToolTip()</h1>
     * <p>This function retrieves and returns a String variable 'toolTip' from the Map<DataType, String> attribute 'toolTips'. 
     * The returned value represents the tool tip property to be add for the given data type.</p>
     * @param DataType dataType
     * @return String toolTip
     */
    public String getToolTip(DataType dataType) {
        return this.toolTips.get(dataType);
    }

    /**
     * <h1>getTableHeader()</h1>
     * <p>This function retrieves and returns a String array variable 'tableHeader' from the Map<DataType, String[]> attribute 'tableHeaders'. 
     * The returned value represents the view table column header for the given data type.</p>
     * @param DataType dataType
     * @return String[] tableHeader
     */
    public String[] getTableHeader(DataType dataType) {
        return this.tableHeaders.get(dataType);
    }

    /**
     * <h1>getDataBaseHeader()</h1>
     * <p>This function retrieves and returns a String variable 'dataBaseHeader' from the Map<DataType, String[]> attribute 'dataBaseHeaders'. 
     * The return value represents the database table column header for the given data type.</p>
     * @param DataType dataType
     * @return String[] dataBaseHeader
     */
    public String[] getDataBaseHeader(DataType dataType) {
        return this.dataBaseHeaders.get(dataType);
    }
    
    /**
     * <h1>setDataBaseHeader()</h1>
     * <p>This function sets a String array to Map<DataType, String[]> attribute 'dataBaseHeaders'. 
     * This mutator saves a pair of variable like as follow:</p>
     * <p>Map key: a given database data type.<br>
     * Map value: a column name list for the header of the given database type.</p>
     * @param DataType dataType
     * @param String[] columns
     */
    public void setDataBaseHeader(DataType dataType, String[] columns) {
        this.dataBaseHeaders.put(dataType, columns);
    }
}