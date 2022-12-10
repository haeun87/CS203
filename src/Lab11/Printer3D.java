package Lab11;

/*
 * Concrete Class 3
 * This class is a child class of abstract class 'Printer'.
 * */
public class Printer3D extends Printer {

    private int filamentLoaded;
    private final int maxFilaments = 100; 
    private final int filamentNeededPerDoc = 30; // Suppose 30% of filament is needed for 1 model;
    
    /**
     * This function is a constructor and set up the given value to the class properties.
     * @param int filamentLoaded
     */
    public Printer3D(int filamentLoaded) {
        this.filamentLoaded = filamentLoaded;
    }
    
    /**
     * This function returns the Integer property 'filamentLoaded'.
     * @return int filamentLoaded
     */
    public int getFilamentLoaded() {
        return this.filamentLoaded;
    }

    /**
     * This function sets new value to the Integer property 'filamentLoaded'. (Max : 100)
     * @param int filamentLoaded
     */
    public void setFilamentLoaded(int filamentLoaded) {
        this.filamentLoaded = (this.filamentLoaded < this.maxFilaments && filamentLoaded < this.maxFilaments) ? filamentLoaded : this.maxFilaments;
    }

    /**
     * This function returns the Integer property 'maxFilaments'.
     * @return int maxFilaments
     */
    public int getMaxFilaments() {
        return maxFilaments;
    }
    
    /**
     * This function returns the Integer property 'filamentNeededPerDoc'.
     * @return int filamentNeededPerDoc
     */
    public int getFilamentNeededPerDoc() {
        return this.filamentNeededPerDoc;
    }

    /**
     * This function is inherited from its parent's concrete method.
     * This function shows the description of current class and its properties.
     */
    @Override
    public void description() {
        System.out.println("============[Printer Description]===========");
        System.out.println("This is a 3D Printer");
        System.out.printf("This Printer's power is %s\n",(this.isPowerOn()) ? "On.": "Off.");
        System.out.printf("Current Filament left: %d (percent)\n",this.filamentLoaded);
        System.out.println("============================================");
        System.out.println();
    }
    
    /**
     * This function is implemented of its parent's abstract method.
     * This function executes printing of the given number of requests(objects)
     * @param int numberOfRequest
     */
    @Override
    public void printRequest(int numberOfRequest) {
        if(this.isPowerOn()) {
            if(this.filamentLoaded < this.filamentNeededPerDoc*numberOfRequest) {
                System.out.println("[Fail] Cannot complete print. Not enough filament!!");
            }
            else {
                printObject(numberOfRequest);
                this.filamentLoaded -= this.filamentNeededPerDoc*numberOfRequest;
            }
        }
        else {
            System.out.println("[Fail] Cannot print. The printer's power is off.");
        }
        System.out.println();
    }
    
    /**
     * This function is a concrete method of this class.
     * This function executes a printing activity of the given number of requests(objects)
     * @param int numberOfRequests
     */
    public void printObject(int numberOfRequests) {
        System.out.printf("[Success] Printed %d of objects by the 3D printer.\n", numberOfRequests); 
    }

}
