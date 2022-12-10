package Lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GUI implements ActionListener {

	// add variables if needed
    private String tempInput = "0";
    private String identifier = "";
    JTextField display;

    // Patterns that are frequently used ( No getter/setter needs to be set)
    private String identifierPattern = "[+\\-*/]";
    private String endPatternWith = ".*";
        
	public GUI() {} // Constructor

    /**
     * <h1>getTempInput()</h1>
     * <p>This function returns the String property 'tempInput'.</p>
     * @return String tempInput
     */
    public String getTempInput() {
        return this.tempInput;
    }

    /**
     * <h1>setTempInput(String tempInput)</h1>
     * <p>This function sets new value to the String property 'tempInput' by given String value.</p>
     * @param String tempInput
     */
    public void setTempInput(String tempInput) {
        this.tempInput = tempInput;
    }
    
    /**
     * <h1>getIdentifier()</h1>
     * <p>This function returns the String property 'identifier'.</p>
     * @return String identifier
     */
	public String getIdentifier() {
        return this.identifier;
    }

    /**
     * <h1>setIdentifier(String identifier)</h1>
     * <p>This function sets new value to the String property 'identifier' by given String value.</p>
     * @param String identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * <h1>getDisplay()</h1>
     * <p>This function returns the JTextField property 'display'.</p>
     * @return JTextField display
     */
    public JTextField getDisplay() {
        return this.display;
    }

    /**
     * <h1>setDisplay(JTextField display)</h1>
     * <p>This function sets new value to the JTextField property 'display' by given JTextField value.</p>
     * @param JTextField display
     */
    public void setDisplay(JTextField display) {
        this.display = display;
    }

    /**
	 * <h1>start()</h1>
	 * <p>This method starts the GUI by creating the 
	 * frame and panels needed to show this Java App.</p>
	 */
	public void start() {
		JFrame frame = new JFrame();

		// configure the frame's size, title, and close operation
		frame.setSize(500, 600);
		frame.setTitle("CS 203 Lab 10 - Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// add the JPanel into your JFrame
		frame.add(createPanel(), BorderLayout.CENTER);

		// frame.pack();

		// for some reason, a JFrame defaults to false for visibility
		frame.setVisible(true);
	}

	/**
	 * <h1>createPanel()</h1>
	 * <p>This method creates all of the necessary components
	 * needed to be added into a main JPanel object.</p>
	 * @return a main JPanel object to be added into the JFrame
	 */
	public JPanel createPanel() {
	    // Generally used font type 
	    Font font = new Font("times",Font.PLAIN,15);
	    
		// this is our main panel to house other panels or components
		JPanel mainPanel = new JPanel();


		// this BoxLayout will line its components vertically on the y axis
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		// this is outPanel that contains display field.
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new GridLayout(2,1,5,10));
		outputPanel.setSize(new Dimension(450,40));

		JTextField display = new JTextField(14);
		display.setLayout(new GridLayout(1,1,5,5));
		display.setOpaque(true);
		display.setBackground(Color.white);
		display.setFont(new Font("Times",Font.PLAIN,36));
		display.setText(tempInput);
		display.setEditable(false);
		display.setHorizontalAlignment(SwingConstants.RIGHT);

		setDisplay(display);		
		outputPanel.add(display);
		
        // this is keyPanel 1 that contains Clear, Delete, and Root keypad.
        JPanel keyPanel1 = new JPanel();
        keyPanel1.setLayout(new GridLayout(1,3,5,5));
        keyPanel1.setFont(font);
        
        JButton clearB = new JButton("Clear"); // Clear Button
        clearB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setTempInput("0");
                setIdentifier("");
                getDisplay().setText("0");   
            }    
        });
        keyPanel1.add(clearB);
        
        JButton deleteB = new JButton("Delete"); // Delete Button
        deleteB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(tempInput.matches(endPatternWith+identifierPattern)) {// If the last letter was an identifier
                    setIdentifier("");
                }
                String newInput = getTempInput();
                newInput = (newInput.length() > 1) ? newInput.replaceAll(".$", "") : "0";
                setTempInput(newInput);
                getDisplay().setText(newInput);
            }    
        });
        keyPanel1.add(deleteB);
        
        JButton rootB = new JButton("√"); // √ Button
        rootB.addActionListener(this); // Delegate to the class level
        keyPanel1.add(rootB);
        
        outputPanel.add(keyPanel1);
        
        mainPanel.add(outputPanel);

        
        JPanel keyPanel2 = new JPanel();
        keyPanel2.setLayout(new GridLayout(4,4,5,5));
        keyPanel2.setFont(font);
        
        // Number + Identifier keypads
        String[] calS = {
                "7","8","9","/", // 2nd Row
                "4","5","6","*", // 3rd Row
                "1","2","3","-", // 4th Row
                ".","0","=","+" // 5th Row
                };
        JButton[] calB = new JButton[calS.length];
        
        for(int i = 0;i<calB.length;i++){ // Number Buttons assign
            calB[i] = new JButton(calS[i]);
            calB[i].addActionListener(this); // Delegate to the class level
        }
        for(int i = 0;i<calB.length;i++){ // Assigns keypads to the panel
            keyPanel2.add(calB[i]);
            }
        
        mainPanel.add(keyPanel2);
		// return the entire calculator panel as a JPanel object
		return mainPanel;
	}
	
	 /**
     * <h1>calculate(boolean isRoot)</h1>
     * <p>This method returns calculated result of given variables.</p>
     * @param double numVal1 - 1st Number
     * @param double numVal2 - 2nd Number
     * @param boolean isRoot - Whether square root requested
     * @return a main JPanel object to be added into the JFrame
     */
	public String calculate(double numVal1, double numVal2, boolean isRoot){
	    Double result = 0.0;
	    if(isRoot) {
	        result = Math.sqrt(numVal1);
	    }
	    else if(this.identifier.equals("+")) { // Add
	        result = numVal1 + numVal2;
	    }
	    else if(this.identifier.equals("-")) { // Subtract
	        result = numVal1 - numVal2;
	    }
	    else if(this.identifier.equals("*")) { // Multiply
	        result = numVal1 * numVal2;
	    }
	    else if(this.identifier.equals("/")) { // Divide, Error text return when divided by zero
	        if (numVal2 == 0) {
	            return "ERROR : DIVIDED BY ZERO";
	        }
	        else {
	            result = numVal1 / numVal2;
	        }
        }
	    NumberFormat nf = new DecimalFormat("#.########"); // Output to 8 decimal places
	    
	    return nf.format(result); 
    }
	
    /**
     * <h1>calculate(boolean isRoot)</h1>
     * <p>This method is the class level of action lister includes multiple components' events.</p>
     * @param ActionEvent e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pushB = e.getActionCommand();
        
        // Assign to the local variable
        String tempInput = getTempInput();
        String identifier = getIdentifier();
        JTextField display = getDisplay();
        
        if(pushB.matches("[√=.]")) { // Press "=" or "√" or "√"
            try {
                String result = "";
                
                if(!tempInput.equals("0")) {
                    String[] temp = tempInput.split(identifierPattern);
                    double numVal1 = Double.parseDouble(temp[0]);
                    double numVal2 = (temp.length < 2) ? 0 : Double.parseDouble(temp[1]);  // Check if no second value exist
                    
                    if(pushB.equals(".")) {
                        if(temp.length >= 2) { // The second value does exist
                            tempInput += (!temp[1].contains(".")) ? "." : "";
                        }
                        else { // The second value does not exist
                            tempInput += (!identifier.equals("")) ? "0." : // second value will be started as 0.xx
                                (!temp[0].contains(".")) ? "." : ""; // check first value
                        }
                    }
                    else {
                        result = calculate(numVal1, numVal2, pushB.equals("√"));
                        tempInput = "0";
                        identifier = "";
                    }
                }
                else { // The first time of pressing "."
                    if(pushB.equals(".")) {
                        tempInput += ".";
                    }
                }
                
                display.setText(result.equals("") ? tempInput : result);
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
            }

        }
        else if(pushB.matches(identifierPattern)){ // Pressed identifier
            if(!identifier.equals("")) {
                if(tempInput.matches(endPatternWith+identifierPattern)) {// If the last letter was an identifier => Modify the identifier
                    setIdentifier(pushB);
                    identifier = pushB;
                    tempInput = tempInput.replaceAll(".$", identifier);
                }
            }
            else {
                if(tempInput.matches(endPatternWith+"[.]")) {
                    tempInput = tempInput.replaceAll(".$", ".0");
                }
                identifier = pushB;
                tempInput += identifier;
            }
            
            display.setText(tempInput);
            
        }
        else if(pushB.matches("[0-9]")){ //Pressed number 0~9

            if(tempInput.equals("0")) {
                tempInput = pushB;
            }
            else {
                if(tempInput.matches(endPatternWith+identifierPattern+"0")) { // e.g. "1234 + 00" (X) "5678 / 05" (X)
                   tempInput = this.tempInput.replaceAll(".$", "");
                }
                tempInput += pushB;
            }
            display.setText(tempInput);
            
        }   

        // Update to the class variable
        setTempInput(tempInput);
        setIdentifier(identifier);
        setDisplay(display);
    }
}
