package hsuh_HW5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * <h1>Main-GUI Class BookStoreGUI<T></h1>
 * <p>This class is a main GUI Class that declares a GUI object JFrame and activates it. 
 * Also, it includes creating of its detail components, implementing event handler,
 * and calling back-end functions from 'BookStore(System)' as to process its triggered events. 
 * This Class serves as a parent component for JDialog Class 'DetailViewGUI' and 'RentalViewGUI'.</p>
 * */
public class BookStoreGUI<T> implements ActionListener {

    private BookStore store;
    
    private JFrame frame;
    
    private JPanel panelM;
    
    private JButton btnB;
    private JButton btnC;
    private JButton btnR;
    private JButton btnS;
    private JButton btnL;
    
    private JTextField sBar;

    private JTable table;
    
    private JMenuItem menuD;
    private JMenuItem menuL;
    private JMenuItem menuR;
    
    private DetailViewGUI popupD;
    private RentalViewGUI popupR;
    
    private Property prop;
    
    /**
     * <h1>BookStoreGUI()</h1>
     * <P>This function is a constructor and sets up the class attributes.
     * Mainly this brings up the Singleton Class instances and assigns them to its class attributes.</p>
     */
    public BookStoreGUI() {
        this.store = BookStoreSystem.getInstance();
        this.prop = Property.getInstance();
   }
    
    /**
     * <h1>run()</h1>
     * <p>This function runs the GUI by creating the 
     * frame and panels needed to show this Java Application.</p>
     * @throws IOException 
     */
    public void run() throws IOException {
        // Declare main frame.
        this.frame = new JFrame();

        // Set up the frame's size, title, and close operation.
        int[] frameSize = this.prop.getSize("Frame");
        Dimension dim = new Dimension(frameSize[0],frameSize[1]);
        this.frame.setSize(dim);
        this.frame.setPreferredSize(dim);
        this.frame.setResizable(false);
        this.frame.setTitle(this.prop.getLabel("Frame"));
        
        // Apply LookAndFeel to beautify the UI.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Add Header Panel. This part is immutable. Added as Index: 0.
        this.frame.add(this.createMessagePanel("Header"), BorderLayout.PAGE_START, 0);
        
        // Add Body Panel. This part is mutable. So it is especially assigned as to a class level variable. Added as Index: 1.
        this.panelM = this.createBodyPanel();
        this.frame.add(this.panelM, BorderLayout.CENTER, 1);

        // Add Footer Panel. This part is immutable. Added as Index: 2.
        this.frame.add(this.createMessagePanel("Footer"), BorderLayout.PAGE_END, 2);
        
        // Set to show the frame window at the center of the screen.
        this.frame.setLocationRelativeTo(null);
        
        // Activate the frame.
        this.frame.setVisible(true);

        // Assigns window closing event as to update database files by a user selection. 
        this.frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int ans = JOptionPane.showConfirmDialog(
                frame, 
                prop.getLabel("UpdateDBContent"), 
                prop.getLabel("UpdateDBTitle"),
                JOptionPane.YES_NO_OPTION);
                if(ans == JOptionPane.YES_OPTION) {// User selection to save the change from the system to the database(text files). 
                    boolean result = store.setUpDatabase(prop.getDatabase(DataType.BOOK), prop.getDatabase(DataType.CUSTOMER), "SAVE");
                    if(result) {
                        JOptionPane.showMessageDialog(// Report Success.
                                frame, 
                                prop.getLabel("UpdateSuccessContent"), 
                                prop.getLabel("UpdateSuccessTitle"), 
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(// Report Failure.
                                frame, 
                                prop.getLabel("UpdateFailContent"), 
                                prop.getLabel("UpdateFailTitle"), 
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                e.getWindow().dispose(); // Program will be terminated after the event.
            }
        }); 
    }
    
    /**
     * <h1>createMessagePanel()</h1>
     * <p>This function creates a JPanel to display the title messages of the program.
     * This function is used for both header and footer parts as to make them be unified except each contained message.</p>
     * @param String labelType - Get label attribute by a given label key of the map attribute from Class 'Property'.
     * @return JPanel panel
     */
    public JPanel createMessagePanel(String labelType) {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(this.prop.getColor("Theme"));
        
        JLabel title = new JLabel(this.prop.getLabel(labelType));
        title.setFont(this.prop.getFont("Title"));
        title.setForeground(this.prop.getColor("GeneralFont"));
        
        panel.add(title);
        
        return panel;
    };
    
    /**
     * <h1>createBodyPanel()</h1>
     * <p>This function creates a JPanel to display the main part of the program. Since this part is mutable,
     * will be assigned to a class level variable to be called from other function later. This part contains 
     * a button group that are used to trigger view changes,a table components to show list,
     * and a search bar that can filter list by its keyword.</p>
     * @return JPanel panelMain
     */
    public JPanel createBodyPanel() {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.setBackground(this.prop.getColor("Theme"));
        
        // Add Button Group Panel. This part is immutable. Added as Index: 0.
        panelMain.add(this.createBtnGroupPanel(), BorderLayout.PAGE_START, 0);
        
        // As a default, the program starts its view from a 'Book' Section.
        DataType dataType = this.store.getCurDataType();
        String[] colums = this.prop.getTableHeader(dataType);
        List<T> list = this.store.getDataList(dataType);
        
        // Add Table Panel. This part is mutable. So its component JTable is especially assigned as to a class level variable. Added as Index: 1.
        panelMain.add(this.createTablePanel(colums, list), BorderLayout.CENTER, 1);
        
        // This part is mutable. So its component JTextField is especially assigned as to a class level variable. Added as Index: 2.
        panelMain.add(this.createSearchBarPanel(), BorderLayout.PAGE_END, 2);

        return panelMain;
    };
    
    /**
     * <h1>createBtnGroupPanel()</h1>
     * <p>This function creates a JPanel that contains the button group of the program.
     * The buttons are used to change the view of the program.</p>
     * <p>e.g. Button 'Book' -> 'Book' list view Button 'Customer' -> 'Customer' list view.</p>
     * <p>Buttons are each assigned as to class level variables to handle its events respectively.</p>
     * @return JPanel bPanel
     */
    public JPanel createBtnGroupPanel() {
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new GridLayout(1,3,0,5));
        bPanel.setBackground(this.prop.getColor("Theme"));
        
        this.btnB = this.createBtn("BtnB"); // Book Button.
        this.btnC = this.createBtn("BtnC"); // Customer Button.
        this.btnR = this.createBtn("BtnR"); // Rental Button.
        
        bPanel.add(this.btnB);
        bPanel.add(this.btnC);
        bPanel.add(this.btnR);
        
        return bPanel;
    }
        
    /**
     * <h1>createTablePanel()</h1>
     * <p>This function creates JPanel that contains the list table to be shown on the program.
     * This component includes a creation of a mutable variable 'table'.</p>
     * @param String[] columns - table headers.
     * @param List<T> list - Generic type of List(Determine by a current selected data type, or a view type).
     * @return JPanel tPanel
     */
    @SuppressWarnings("hiding")
    public <T> JPanel createTablePanel(String[] columns, List<T> list) {
        JPanel tPanel = new JPanel();
        tPanel.setLayout(new GridLayout(1,1,5,5));
        
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing cell.
            }          
        };
        
        // Assigns the data to the table.
        for(T t: list) {
            tableModel.addRow(t.toString().split(","));
        }
        
        this.table = new JTable(tableModel);
        
        // Configure width of each columns. It differs from each data type.
        TableColumnModel colModel = this.table.getColumnModel();
        switch(this.store.getCurDataType()) {
            case BOOK:
                colModel.getColumn(1).setPreferredWidth(250);
                colModel.getColumn(2).setPreferredWidth(150);
                colModel.getColumn(3).setPreferredWidth(150);
                colModel.getColumn(4).setPreferredWidth(50);
                colModel.getColumn(5).setPreferredWidth(100);
                colModel.getColumn(6).setPreferredWidth(50);
                break;
            case CUSTOMER:
                colModel.getColumn(1).setPreferredWidth(150);
                colModel.getColumn(2).setPreferredWidth(150);
                colModel.getColumn(3).setPreferredWidth(100);
                colModel.getColumn(4).setPreferredWidth(280);
                colModel.getColumn(5).setPreferredWidth(70);
                break;
            default:
                break;
        }
        this.table.removeColumn(colModel.getColumn(0)); // Make invisible of a number identifier column. 
        this.table.setColumnModel(colModel); // Apply the configuration the above to the table.
        
        // Apply the other configuration to the table.
        this.table.setFont(this.prop.getFont("Table"));
        this.table.setFillsViewportHeight(true);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Single selection only.
        this.table.setComponentPopupMenu(this.createPopupMenu()); // Add Pop-up Menu to the table.
        
        JScrollPane scrollPane = new JScrollPane(this.table); // Apply a scroll-able attribute to the table.
        
        tPanel.add(scrollPane); // load the table to the container panel.
        
        return tPanel;
    }
     
    /**
     * <h1>createPopupMenu()</h1>
     * <p>This function creates pop-up menu items that will be attached to the table.</p>
     * @return JPopupMenu popupMenu
     */
    public JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        
        // MenuItem that enables to display the detail information of the selected row. Opens up a JDialog Class 'DetailViewGUI'.
        this.menuD = new JMenuItem(this.prop.getLabel("DetailMTitle"));
        this.menuD.addActionListener(this);
        popupMenu.add(this.menuD);
        
        if(this.store.getCurDataType() == DataType.BOOK) { // Only shown in the book view.
            // MenuItem that enables to rent the selected book. Opens up a JDialog Class 'RentalViewGUI'.
            this.menuL = new JMenuItem(this.prop.getLabel("RentMTitle"));
            this.menuL.addActionListener(this);
            popupMenu.add(this.menuL);
        }
        else if(this.store.getCurDataType() == DataType.RENTAL) {// Only shown in the rental view.
            // MenuItem that enables to retrieve the selected book.
            this.menuR = new JMenuItem(this.prop.getLabel("ReturnMTitle"));
            this.menuR.addActionListener(this);
            popupMenu.add(this.menuR);
        }
        return popupMenu;
    }
    
    /**
     * <h1>createSearchBarPanel()</h1>
     * <p>This function creates a panel that contains a search bar used from the program.
     * By the keyword written within this search bar component, the list from current view will be
     * filtered as to display the search result only.</p>
     * @return JPanel sPanel
     */
    public JPanel createSearchBarPanel() {
        JPanel sPanel = new JPanel();
        sPanel.setLayout(new BorderLayout());
        sPanel.setBackground(this.prop.getColor("Theme"));
                
        // First panel layer includes guideline for search. 
        JPanel firstP = new JPanel();
        firstP.setLayout(new FlowLayout());
        firstP.setBackground(this.prop.getColor("Theme"));
        
        // Guideline text will be shown as differently depending of the current view type.
        String guideStr = String.format("[%s LIST SEARCH] ", this.store.getCurDataType())
                .concat(this.prop.getToolTip(this.store.getCurDataType()));
        JLabel guide = new JLabel(guideStr);
        guide.setFont(this.prop.getFont("Subtitle"));
        guide.setForeground(this.prop.getColor("GuideFont"));
        
        firstP.add(guide);
        
        // Second panel layer includes a search bar and its execution buttons.
        JPanel secondP = new JPanel();
        secondP.setLayout(new FlowLayout());
        secondP.setBackground(this.prop.getColor("Theme"));
        
        JLabel searchLabel = new JLabel(this.prop.getLabel("LabelS"));
        searchLabel.setFont(this.prop.getFont("Title"));
        searchLabel.setForeground(this.prop.getColor("GeneralFont"));
        
        this.sBar = new JTextField(this.prop.getSize("Search")[0]);
        
        this.sBar.setFont(this.prop.getFont("SearchBar"));
        this.sBar.setOpaque(true);
        this.sBar.setBackground(this.prop.getColor("TextArea"));
        this.sBar.setToolTipText(this.prop.getToolTip(this.store.getCurDataType()));
        this.sBar.setEditable(true);
        this.sBar.setHorizontalAlignment(SwingConstants.LEFT);
        this.sBar.addActionListener(this);
                        
        this.btnS = this.createBtn("BtnS"); // Button that executes searching.
        this.btnL = this.createBtn("BtnL"); // Button that displays non filtered, original list for current view.
        
        secondP.add(searchLabel, 0);
        secondP.add(this.sBar, 1);
        secondP.add(this.btnS, 2);
        secondP.add(this.btnL, 3);
        
        sPanel.add(firstP, BorderLayout.CENTER);
        sPanel.add(secondP, BorderLayout.PAGE_END);
        
        return sPanel;
    }

    /**
     * <h1>createBtn()</h1>
     * <p>This function creates a JPanel that includes its JButton component. ActionListener is thrown to the
     * class level since dynamic component changing event will be included.
     * </p>
     * @param String btnType
     * @return JButton btn
     */
    public JButton createBtn(String btnType) {
        JButton btn = new JButton(this.prop.getLabel(btnType));
        btn.setFont(this.prop.getFont("Button"));
        btn.setForeground(prop.getColor("GeneralFont"));
        btn.setBackground(this.prop.getColor("Theme"));
        btn.setOpaque(true);
        btn.addActionListener(this);
        return btn;
    }
    
    /**
     * <h1>updateTable()</h1>
     * <p>This function redraws a table component so to update any changes which are resulted from some event.
     * </p>
     * @param String[] columns
     * @param List<T> list - Declared as generic to be used regardless of the current view type.
     */
    public void updateTable(String[] columns, List<T> list) {

        this.panelM.remove(1); // remove old table.
        this.panelM.add(this.createTablePanel(columns, list), BorderLayout.CENTER, 1); // add updated table.
        this.panelM.remove(2); // remove old search bar.    
        this.panelM.add(this.createSearchBarPanel(), BorderLayout.PAGE_END, 2); // add updated search bar.   
        
        this.panelM.revalidate();
        this.panelM.repaint();
        
        this.frame.pack();
        
        this.store.requestRefresh(false); // Since the request is completed, this flag should be reset as false.
    }
    
    /**
     * <h1>actionPerformed()</h1>
     * <p>This function handles the triggered events from each component. In order to avoid circular errors
     * events are mainly dealt as a class level.
     * @param ActionEvent e
     * </p>
     */
    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        // Event Handling Case 1: Search Event.
        if(e.getSource() == this.sBar || e.getSource() == this.btnS) {// Pressed Enter within a text field component or clicked the search button.
            if (this.sBar.getText().equals("")) { // Excepts when nothing has typed within search bar.
                JOptionPane.showMessageDialog( // Showing up an alert window.
                        this.sBar, 
                        this.prop.getLabel("NoInputContent"), 
                        this.prop.getLabel("NoInputTitle"), 
                        JOptionPane.WARNING_MESSAGE);
            }
            else { // Request data retrieve to the back-bone class 'BookStoreSystem'.
                DataType dataType = this.store.getCurDataType();
                String[] columns = this.prop.getTableHeader(dataType);
                String searchKey = this.sBar.getText().trim();
                
                switch(dataType) { // Handling differs from view type.
                    case BOOK:
                        List<Book> bookList = this.store.findBookByTitle(searchKey);
                        this.updateTable(columns, (List<T>) bookList);
                        break;
                    case CUSTOMER:
                        List<Customer> customerlist = this.store.findCustomersByName(searchKey);
                        this.updateTable(columns, (List<T>) customerlist);
                        break;
                    case RENTAL:
                        List<Rental> rentalList = this.store.findBooksByLastName(searchKey);
                        this.updateTable(columns, (List<T>) rentalList);
                        break;
                    default:
                        break;   
                }
                this.sBar.setText(""); // Reset search bar.
            }
        }
        // Event Handling Case 2: Menu Item Event.
        else if(e.getSource() instanceof JMenuItem) {
            int row = this.table.getSelectedRow();
            if(row> -1) { // Only activate if any row has been selected.
                String no = (String) table.getModel().getValueAt(row, 0); // Book Number.
                if(e.getSource() == this.menuL) { // In case of request renting the book of a selected row.
                    if(this.popupR != null && this.popupR.isDialogOpen()) {// If a previous dialog has remained unclosed.
                        this.popupR.dispose();
                        this.popupR.setDialogOpen(false);
                    }
                    String title = (String) this.table.getModel().getValueAt(row, 1); // Book title.
                    int bookNo = Integer.parseInt(no);
                    
                    // Activate JDialog Class 'RentalViewGUI' from new window.
                    this.popupR = new RentalViewGUI(this.frame, title, bookNo);
                    this.popupR.setDefaultCloseOperation(DetailViewGUI.DISPOSE_ON_CLOSE);
                    this.popupR.setVisible(true);
                    this.popupR.addWindowListener(new java.awt.event.WindowAdapter() {

                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) { // After the dialog window has closed.
                            if(store.hasRequestRefresh()) {// Refresh table after rental completion.
                                DataType dataType = store.getCurDataType();
                                String[] colums = prop.getTableHeader(dataType);
                                List<T> list = store.getDataList(dataType);
                                updateTable(colums, list);
                                store.requestRefresh(false);
                            }
                        }
                    });
                }
                else if(e.getSource() == this.menuR) { // In case of request retrieving the book of a selected row.
                    int curRow = table.getSelectedRow();

                    if (curRow > -1) {
                        String rentalNo = (String) this.table.getModel().getValueAt(curRow, 0); // Rental Number.

                        // Shows the dialog to get confirmation of a book return from user.
                        int ans = JOptionPane.showConfirmDialog(
                                this.menuR, 
                                this.prop.getLabel("RetrieveContent"), 
                                this.prop.getLabel("RetrieveTitle"),
                                JOptionPane.YES_NO_OPTION);
                        if(ans == JOptionPane.YES_OPTION) {// Retrieve the book.
                            this.store.retrieveBook(rentalNo); // Request back-bone class to process the book return.
                            
                            String[] nums = rentalNo.split(":");
                            String lastName = (String) this.table.getModel().getValueAt(curRow, 1);
                            String title = (String) this.table.getModel().getValueAt(curRow, 2);
                            String returnMsg = String.format(
                                    "[Return Info]\n" + 
                                    "Book Number: %s\n"+ 
                                    "Book title: %s\n" +
                                    "Customer Number: %s\n"+
                                    "Last name of Customer: %s\n", 
                                    nums[0],
                                    title,
                                    nums[1],
                                    lastName);
                            
                            // Shows the dialog to display the information of the retrieved book.
                            JOptionPane.showMessageDialog(
                                    this.menuR, 
                                    returnMsg, 
                                    this.prop.getLabel("ReturnTitle"), 
                                    JOptionPane.PLAIN_MESSAGE);
                            
                            DataType dataType = this.store.getCurDataType();
                            String[] colums = this.prop.getTableHeader(dataType);
                            List<T> list = this.store.getDataList(dataType);
                            
                            this.updateTable(colums, list);
                        }
                    }
                }
                else { // e.getSource() == this.menuD. In case of request showing a detail information of the selected row.
                    if(this.popupD != null && this.popupD.isDialogOpen()) {// If a previous dialog has remained unclosed.
                        this.popupD.dispose();
                        this.popupD.setDialogOpen(false);
                    }
                    DataType dataType = this.store.getCurDataType();
                    T data = this.store.getData(no, dataType);
                    // Activate JDialog Class 'DetailViewGUI' from new window.
                    this.popupD = new DetailViewGUI(this.frame, dataType, data);
                    this.popupD.setDefaultCloseOperation(DetailViewGUI.DISPOSE_ON_CLOSE);
                    this.popupD.setVisible(true);              
                }
            }
        }
        // Event Handling Case 3: Group Button & All List Button Event.
        else if(e.getSource() instanceof JButton){
            if(e.getSource() != this.btnL) { // Unless it was All List button, view type will be changed.
                DataType newDataType = 
                        (e.getSource() == this.btnC) ? DataType.CUSTOMER : 
                            (e.getSource() == this.btnR) ? DataType.RENTAL : DataType.BOOK;
                this.store.setCurDataType(newDataType); // Change the current view type.
            }
            DataType dataType = this.store.getCurDataType();
            String[] colums = this.prop.getTableHeader(dataType);
            List<T> list = this.store.getDataList(dataType);
            
            this.updateTable(colums, list); // Refresh table component after view change.
        }
    }
}