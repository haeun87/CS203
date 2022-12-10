package Lab11;

/*
 * Concrete class 1
 * This class is a parent class of class 'ColorInkPrint' and a child class of abstract class 'Printer'
 * */
public class InkPrinter extends Printer {

    private int numOfPaperLoaded;
    private int blackInkLoaded;
    private final int minPaperLoaded = 0;
    private final int maxPaperLoaded = 200;
    private final int minInkLoaded = 0;
    private final int maxInkLoaded = 100;
    private final int blackInkPerDoc = 20; // Suppose 1 document consumes 20% of black ink
    
    /**
     * This function is a constructor and set up the given value to the class properties.
     * @param int numOfPaperLoaded
     * @param int blackInkLoaded
     */
    public InkPrinter(int numOfPaperLoaded, int blackInkLoaded) {
        this.numOfPaperLoaded = numOfPaperLoaded;
        this.blackInkLoaded = blackInkLoaded;
    }

    /**
     * This function returns the Integer property 'numOfPaperLoaded'.
     * @return int numOfPaperLoaded
     */
    public int getNumOfPaperLoaded() {
        return this.numOfPaperLoaded;
    }

    /**
     * This function sets new value to the Integer property 'numOfPaperLoaded'. (Max : 200)
     * @param int numOfPaperLoaded
     */
    public void setNumOfPaperLoaded(int numOfPaperLoaded) {
        if (numOfPaperLoaded > 0) {// Load
            if(this.numOfPaperLoaded < this.maxPaperLoaded) {
                this.numOfPaperLoaded = Math.min(this.maxPaperLoaded, this.numOfPaperLoaded + numOfPaperLoaded);
            } 
        }
        else if(numOfPaperLoaded < 0) {// Consume
            if(this.numOfPaperLoaded > this.minPaperLoaded) {
                this.numOfPaperLoaded = Math.max(this.minPaperLoaded, this.numOfPaperLoaded + numOfPaperLoaded);
            } 
        }
    }
    
    /**
     * This function returns the Integer property 'blackInkLoaded'.
     * @return int blackInkLoaded
     */
    public int getBlackInkLoaded() {
        return this.blackInkLoaded;
    }

    /**
     * This function sets new value to the Integer property 'blackInkLoaded'. (Min: 0 Max : 100)
     * @param int blackInkLoaded
     */
    public void setBlackInkLoaded(int blackInkLoaded) {
        if (blackInkLoaded > 0) {// Load
            if(this.blackInkLoaded < this.maxInkLoaded) {
                this.blackInkLoaded = Math.min(this.maxInkLoaded, this.blackInkLoaded + blackInkLoaded);
            } 
        }
        else if(blackInkLoaded < 0) {// Consume
            if(this.blackInkLoaded > this.minInkLoaded) {
                this.blackInkLoaded = Math.max(this.minInkLoaded, this.blackInkLoaded + blackInkLoaded);
            } 
        }
    }

    /**
     * This function returns the Integer property 'minPaperLoaded'.
     * @return int minPaperLoaded
     */
    public int getMinPaperLoaded() {
        return minPaperLoaded;
    }

    /**
     * This function returns the Integer property 'maxPaperLoaded'.
     * @return int maxPaperLoaded
     */
    public int getMaxPaperLoaded() {
        return this.maxPaperLoaded;
    }

    /**
     * This function returns the Integer property 'minInkLoaded'.
     * @return int minInkLoaded
     */
    public int getMinInkLoaded() {
        return minInkLoaded;
    }

    /**
     * This function returns the Integer property 'maxInkLoaded'.
     * @return int maxInkLoaded
     */
    public int getMaxInkLoaded() {
        return this.maxInkLoaded;
    }

    /**
     * This function returns the Integer property 'blackInkPerDoc'.
     * @return int blackInkPerDoc
     */
    public int getBlackInkPerDoc() {
        return this.blackInkPerDoc;
    }

    /**
     * This function is inherited from its parent's concrete method.
     * This function shows the description of current class and its properties.
     */
    @Override
    public void description() {
        System.out.println("============[Printer Description]===========");
        System.out.println("This is an Ink Printer.");
        System.out.printf("This Printer's power is %s\n",(this.isPowerOn()) ? "On.": "Off.");
        System.out.printf("Current paper loaded: %d\n",this.getNumOfPaperLoaded());
        System.out.printf("Current Ink left: %d (percent)\n",this.blackInkLoaded);
        System.out.println("============================================");
        System.out.println();
    }
    
    /**
     * This function is implemented from its parent's abstract method.
     * This function executes a printing activity of the given number of requests(documents)
     * @param int numberOfRequests
     */
    @Override
    public void printRequest(int numberOfRequests) {

        if(this.isPowerOn()) {
            int currentNumOfPapers = this.getNumOfPaperLoaded();
            if(currentNumOfPapers < numberOfRequests) {
                System.out.println("[Fail] Cannot complete print. Not enough paper!!");
            }
            else if(!checkBlackInk(numberOfRequests)) { 
                System.out.println("[Fail] Cannot complete print. Not enough Black Ink!!");
            }
            else {
                System.out.printf("[Success] Printed %d of documents successfully.\n", numberOfRequests);
                this.setNumOfPaperLoaded((-1)*numberOfRequests);
                this.setBlackInkLoaded((-1)*this.blackInkPerDoc*numberOfRequests);
            }
        }
        else {
            System.out.println("[Fail] Cannot print. The printer's power is off.");
        }
        System.out.println();
    }
    
    /**
     * This function is a concrete method of this class.
     * This function checks whether the printer has enough black ink
     * @param int numberOfRequests
     * @return boolean isEnoughInk
     */
    public boolean checkBlackInk(int numberOfRequests) {
        return this.blackInkLoaded >= this.blackInkPerDoc*numberOfRequests;
    }

}
