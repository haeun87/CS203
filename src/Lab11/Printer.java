package Lab11;

/*
 * Abstract class
 * This class a parent class of class 'InkPrinter' and class 'Print3D'.
 * */
public abstract class Printer {
    
    private boolean powerOn;
    
    /**
     * This function returns the Boolean property 'powerOn'.
     * @return boolean powerOn
     */
    public boolean isPowerOn() {
        return this.powerOn;
    }

    /**
     * This function sets new value to the Boolean property 'powerOn'.
     * @param boolean powerOn
     */
    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }

    /**
     * This function prints out the description of current class and its properties.
     */
    public void description() { // Concrete function
        System.out.println("============[Printer Description]===========");
        System.out.println("This is a Printer.");
        System.out.printf("This Printer's power is %s\n",(this.powerOn) ? "On.": "Off.");
        System.out.println("============================================");
        System.out.println();
    }
    
    public abstract void printRequest(int numberOfDocument); // Abstract function
    
    
}
