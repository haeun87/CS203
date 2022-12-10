package hsuh_HW5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * <h1>Sub-GUI Class DetailViewGUI<T></h1>
 * <p>This class is a sub GUI Class that inherits JDialog class. 
 * This component class defines a view that displays a detail description of the given data type object. 
 * This Class is a child component of the 'BookStoreGUI' class.</p>
 * */
public class DetailViewGUI extends JDialog {
    
    private boolean isDialogOpen;
    
    /**
     * <h1>DetailViewGUI()</h1>
     * <P>Initially, this function brings up the Singleton Class instance and assigns them to its class variables.
     * Then it defines its components. After all configuration, the view will be activate within this constructor function process.</p>
     * @param JFrame frame - a parent component.
     * @param DataType dataType - a type of view for the object to be displayed.
     * @param T info - a generic type of object to be displayed.
     */
    public <T> DetailViewGUI(JFrame frame, DataType dataType, T info) {
        Property prop = Property.getInstance();
        
        int[] size = prop.getSize("DetailGUI");
        Dimension dim = new Dimension(size[0],size[1]);
        
        this.setSize(dim);
        this.setPreferredSize(dim);
        this.setResizable(false);
        this.setTitle(dataType.toString()+" "+prop.getLabel("PopupTitle"));
        
        this.setLocationRelativeTo(frame);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel titleP = new JPanel(); // Panel includes the title of the detail view.
        titleP.setLayout(new FlowLayout());
        titleP.setBackground(prop.getColor("Theme"));

        JLabel contentTitle = new JLabel(String.format("%s DESCRIPTION", dataType.toString())); // Defines the title of the detail view.
        contentTitle.setFont(prop.getFont("Subtitle"));
        contentTitle.setForeground(prop.getColor("GeneralFont"));

        titleP.add(contentTitle);
        
        JPanel contentP = new JPanel(); // Panel includes the contend of the detail view.
        contentP.setLayout(new GridLayout(1,1,10,10));
        contentP.setBackground(prop.getColor("Theme"));
                
        JTextArea description = createDescriptionField(dataType, info); // Defines the content of the detail view.
        description.setEditable(false);
        description.setFont(prop.getFont("TextArea"));
        description.setForeground(prop.getColor("GeneralFont"));
        description.setBackground(prop.getColor("TextArea"));
        description.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane jscrollPane = new JScrollPane(description); // Applies a scroll property.
        
        contentP.add(jscrollPane);;
        
        JPanel btnP = new JPanel(); // Add Close Button.
        btnP.setLayout(new GridLayout(1,1,5,5));
        
        JButton btnC = new JButton(prop.getLabel("BtnX")); // CLOSE button.
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDialogOpen = false;
                dispose(); // dispose the dialog when clicked a 'CLOSE' button.
            }
        });
        btnC.setFont(prop.getFont("Button"));
        
        panel.add(titleP, BorderLayout.PAGE_START);
        panel.add(contentP, BorderLayout.CENTER);
        panel.add(btnP, BorderLayout.PAGE_END);
        
        this.add(panel);
        
        this.isDialogOpen = true; // Flag on
        
        this.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                isDialogOpen = false;
                dispose(); // dispose the dialog when closing.
            }
        });
    }

    /**
     * <h1>createDescriptionField()</h1>
     * <p>This function creates a JTextArea that includes a description text for the given data type of object.
     * Internally, the function discriminates the type of object and assigns the suitable description text for the object.</p>
     * @param DataType dataType - a type of view for the object to be displayed.
     * @param T info - a generic type of object to be displayed.
     * @return JTextArea description
     */
    public <T> JTextArea createDescriptionField(DataType dataType, T info) {
        String description = "";
        switch(dataType) {
            case BOOK:
                Book book = (Book) info;
                description = String.format("""
                        Number: %d
                        Title: %s
                        Author: %s
                        Publisher: %s
                        Page: %d (pages)
                        Published Year: %d
                        Copy: %d(Available: %d)
                        Category: %s
                                """, 
                                book.getBookNo(),
                                book.getTitle(),
                                book.getAuthor(),
                                book.getPublisher(),
                                book.getNumOfPages(),
                                book.getYearPublished(),
                                book.getNumOfCopies(),
                                book.getInventory(),
                                book.getCategory());
                break;
            case CUSTOMER:
                Customer customer = (Customer) info;
                description = String.format("""
                        Number: %d
                        First Name: %s
                        Last Name: %s
                        Email: %s
                        Phone: %s
                        Address: %s
                        Current number of Rentals: %d (books)
                                """, 
                                customer.getCustomerNo(),
                                customer.getFirstName(),
                                customer.getLastName(),
                                customer.getEmail(),
                                customer.getPhoneNumber().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"),
                                customer.getAddress(),
                                customer.getNumOfRentals()
                                );
                break;
            case RENTAL:
                Rental rental = (Rental) info;
                description = String.format("""
                        Customer Number: %d
                        Customer Name: %s, %s
                        Book Number: %d
                        Book Title: %s
                        Book Author: %s
                                """, 
                                rental.getCustomer().getCustomerNo(),
                                rental.getCustomer().getFirstName(),
                                rental.getCustomer().getLastName(),
                                rental.getBook().getBookNo(),
                                rental.getBook().getTitle(),
                                rental.getBook().getAuthor());
                break;
            default:
                description = " - No content -";
                break;
        }
        return new JTextArea(description);
    }

    /**
     * <h1>isDialogOpen()</h1>
     * <p>This function returns a boolean attribute 'isDialogOpen'.
     * This attribute represents whether this dialog has opened already.</p>
     * @return boolean isDialogOpen
     */
    public boolean isDialogOpen() {
        return this.isDialogOpen;
    }
    
    /**
     * <h1>setDialogOpen()</h1>
     * <p>This function assigns a new value to the boolean attribute 'isDialogOpen'.</p>
     * @param boolean isDialogOpen
     */
    public void setDialogOpen(boolean isDialogOpen) {
        this.isDialogOpen = isDialogOpen;
    }
}