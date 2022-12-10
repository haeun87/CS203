package hsuh_HW5;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * <h1>Sub-GUI Class RentalViewGUI<T></h1>
 * <p>This class is a main GUI Class that inherits JDialog class. 
 * This component class defines a view that shows options to confirm a rental of the given data type object 
 * and handles the subsidiary events regarding the selection. 
 * This Class is a child component of the 'BookStoreGUI' class.</p>
 * */
public class RentalViewGUI extends JDialog {
    
    private Property prop;
    private BookStore store;
    private JComboBox<String> selection;
    private Map<Integer, int[]> rSels;
    
    private boolean isDialogOpen;
    
    /**
     * <h1>RentalViewGUI()</h1>
     * <P>This function is a constructor and sets up the class attributes.
     * Initially, this function brings up the Singleton Class instances and assigns them to its class variables.
     * Then it defines its components and the corresponding events of them. After all configuration, the view will be activate
     * within this constructor function process.</p>
     * @param JFrame frame - a parent component.
     * @param String bTitle - a title of the book to be rent.
     * @param int bNo - an identifier number of the book to be rent.
     */
    public <T> RentalViewGUI(JFrame frame, String bTitle, int bNo) {
        this.prop = Property.getInstance();
        this.store = BookStoreSystem.getInstance();
        
        int[] size = this.prop.getSize("RentalGUI");
        Dimension dim = new Dimension(size[0],size[1]);
        this.setSize(dim);
        this.setLayout(new GridLayout(3,1,10,5));
        this.setPreferredSize(dim);
        this.setResizable(false);
        this.setTitle(this.prop.getLabel("RentalTitle")+" "+bTitle);
        this.setLocationRelativeTo(frame);
        
        JLabel label = new JLabel(this.prop.getLabel("LabelR"));
        label.setFont(prop.getFont("Subtitle"));
        label.setForeground(this.prop.getColor("GuideFont"));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);
        
        JPanel body = new JPanel();
        
        List<Customer> customerList = this.store.getCustomerList();
        this.rSels = new HashMap<Integer, int[]>();
        
        // Creates the Combo box property that displays a selection for which target customer to rent the book.
        this.selection = new JComboBox<String>();
        this.selection.setFont(prop.getFont("Combobox"));
        
        int idx = 0;
        for(Customer customer: customerList) {// Assigns the customer list to the combo box.
            this.rSels.put(idx, new int[]{bNo, customer.getCustomerNo()});
            String row = String.format("[%d] %s, %s", customer.getCustomerNo(), customer.getFirstName(), customer.getLastName());
            this.selection.addItem(row);
            idx++;
        }
        
        body.add(this.selection);

        this.add(body);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1,2,0,5));
        
        JButton btnY = new JButton(this.prop.getLabel("BtnY")); // RENT Button.
        btnY.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {// Rental event occurs.
                int idx = selection.getSelectedIndex();
                if(idx > -1) {// Only processes when selected.
                    int[] rental = rSels.get(idx);
                    int result = store.rentBook(rental[0], rental[1]);
                    
                    if(result < 0) {// In case of not enough inventory.
                        JOptionPane.showMessageDialog(
                                frame, 
                                prop.getLabel("NoInventoryContent"), 
                                prop.getLabel("NoInventoryTitle"), 
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else if(result == 0) {// In case of request duplicated.
                        JOptionPane.showMessageDialog(//
                                frame, 
                                prop.getLabel("NoDuplicateContent"), 
                                prop.getLabel("NoDuplicateTitle"), 
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(// In case of rent approved.
                                frame, 
                                prop.getLabel("RentSuccessContent"), 
                                prop.getLabel("RentSuccessTitle"), 
                                JOptionPane.INFORMATION_MESSAGE);
                        store.requestRefresh(true);
                        isDialogOpen = false;
                        dispose();
                    }
                }
            }
            
        });
        btnY.setFont(prop.getFont("Button"));
        
        JButton btnN = new JButton(this.prop.getLabel("BtnN")); // CANCEL button.
        btnN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDialogOpen = false;
                dispose(); // dispose the dialog when clicked a 'CANCEL' button.
            }
        });
        btnN.setFont(prop.getFont("Button"));
        
        btnPanel.add(btnY);
        btnPanel.add(btnN);
        
        this.add(btnPanel);
        
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