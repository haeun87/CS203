package Lab11;

/*
 * Concrete class 2
 * This class is a child class of concrete class 'InkPrinter' and interface 'Scanner'
 * */
public class ColorInkPrinter extends InkPrinter implements Scanner {

    private int colorInkLoaded;
    private boolean blackMode; // Ink consumption will be doubles as 10% -> 20% when using a blackMode
    private final int colorInkPerDoc = 10;
    
    /**
     * This function is a constructor and set up the given value to the class properties.
     * @param int numOfPaperLoaded
     * @param int blackInkLoaded
     * @param int colorInkLoaded
     * @param boolean blackMode
     */
    public ColorInkPrinter(int numOfPaperLoaded, int blackInkLoaded, int colorInkLoaded, boolean blackMode) {
        super(numOfPaperLoaded, blackInkLoaded);
        this.colorInkLoaded = colorInkLoaded;
        this.blackMode = blackMode;
    }

    /**
     * This function returns the Integer property 'colorInkLoaded'.
     * @return int colorInkLoaded
     */
    public int getColorInkLoaded() {
        return this.colorInkLoaded;
    }

    /**
     * This function sets new value to the Integer property 'colorInkLoaded'.(Min: 0, Max : 100)
     * @param int colorInkLoaded
     */
    public void setColorInkLoaded(int colorInkLoaded) {
        if (colorInkLoaded > 0) {// Load
            if(this.colorInkLoaded < this.getMaxInkLoaded()) {
                this.colorInkLoaded = Math.min(this.getMaxInkLoaded(), this.colorInkLoaded + colorInkLoaded);
            } 
        }
        else if(colorInkLoaded < 0) {// Consume
            if(this.colorInkLoaded > this.getMinInkLoaded()) {
                this.colorInkLoaded = Math.max(this.getMinInkLoaded(), this.colorInkLoaded + colorInkLoaded);
            } 
        }
    }
    
    /**
     * This function returns the Boolean property 'blackMode'.
     * @return boolean blackMode
     */
    public boolean isBlackMode() {
        return this.blackMode;
    }

    /**
     * This function sets new value to the Boolean property 'blackMode'.
     * @param boolean blackMode
     */
    public void setBlackMode(boolean blackMode) {
        this.blackMode = blackMode;
    }

    /**
     * This function returns the Integer property 'colorInkPerDoc'.
     * @return int colorInkPerDoc
     */
    public int getColorInkPerDoc() {
        return this.colorInkPerDoc;
    }

    /**
     * This function is inherited from its grandparent(class 'Printer')'s concrete method.
     * This function shows the description of current class and its properties.
     */
    @Override
    public void description() {
        System.out.println("============[Printer Description]===========");
        System.out.println("This is an Color Ink Printer");
        System.out.printf("This Printer's power is %s\n",(this.isPowerOn()) ? "On.": "Off");
        System.out.printf("Current paper loaded: %d\n",this.getNumOfPaperLoaded());
        System.out.printf("Current print mode: %s\n",(this.blackMode)? "Black-Only": "Color");
        System.out.printf("Current Ink left: Black - %d (percent) Color - %d (percent) \n", this.getBlackInkLoaded(), this.colorInkLoaded);
        System.out.println("============================================");
        System.out.println();
    }
    
    /**
     * This function is implemented of its grandparent(class 'Printer')'s abstract method.
     * This function executes printing of the given number of requests(documents).
     * @param int numberOfDocuments
     */
    @Override
    public void printRequest(int numberOfDocuments) {

        if(this.isPowerOn()) {
            int currentNumOfPapers = this.getNumOfPaperLoaded();
            if(currentNumOfPapers < numberOfDocuments) {
                System.out.println("[Fail] Cannot complete print. Not enough paper!!");
            }
            else if(!checkBlackInk(numberOfDocuments)) {
                System.out.println("[Fail] Cannot complete print. Not enough Black Ink!!");
            }
            else if(!checkColorInk(numberOfDocuments)) {
                System.out.println("[Fail] Cannot complete print. Not enough Color Ink!!");
            }
            else {
                String mode = (this.blackMode) ? "black only" : "colorfully";
                System.out.printf("[Success] Printed %d of documents as %s.\n", numberOfDocuments, mode); 
                this.setNumOfPaperLoaded((-1)*numberOfDocuments);
                int blackInkConsumed = ((this.blackMode) ? this.getBlackInkPerDoc(): this.getBlackInkPerDoc()/2)*numberOfDocuments;
                this.setBlackInkLoaded((-1)*blackInkConsumed);
                if(!this.blackMode) this.setColorInkLoaded((-1)*this.colorInkPerDoc*numberOfDocuments);
            }
        }
        else {
            System.out.println("[Fail] Cannot print. The printer's power is off.");
        }
        System.out.println();
    }
    
    /**
     * This function is inherited from its parent(class 'InkPrinter')'s concrete method.
     * This function checks whether the printer has enough black ink.
     * @param int numberOfDocuments
     * @return boolean isEnoughBlackInk
     */
    @Override
    public boolean checkBlackInk(int numberOfDocuments) {
        return this.getBlackInkLoaded() >= ((this.blackMode) ? this.getBlackInkPerDoc(): this.getBlackInkPerDoc()/2)*numberOfDocuments;
    }
    
    /**
     * This function is a concrete method of this class.
     * This function checks whether the printer has enough color ink
     * @param int numberOfRequests
     * @return boolean isEnoughColorInk
     */
    public boolean checkColorInk(int numberOfDocuments) {
        return this.colorInkLoaded >= this.colorInkPerDoc*numberOfDocuments;
    }
    
    /**
     * This function is implemented from interface 'Scanner'.
     * This function executes printing of the given number of requests(documents).
     * @param String docType
     * @param int numberOfDocuments
     * @param boolean isPrinting - whether to print the scanned documents
     */
    @Override
    public void scan(String docType, int numberOfDocuments, boolean isPrinting) {
        if(this.isPowerOn()) {
            System.out.printf("[Success] Scanned %d of %S documents successfully.\n", numberOfDocuments, docType);
            
            if(isPrinting) {
                System.out.println("[Loading] Now request printing...");
                this.printRequest(numberOfDocuments);
            }
            
        }
        else {
            System.out.printf("[Fail] Cannot scan the %s file. The printer's power is off.\n", docType);
        }
        System.out.println();
    }
}
