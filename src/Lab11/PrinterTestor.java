package Lab11;

/*
 * Driver class
 * This class check the methods of each classes.
 * */
public class PrinterTestor {
    
    /**
     * This function check the methods within class 'InkPrinter'
     * @param int numOfReqs
     */
    public static void checkBlackPrinter(int numOfReqs) {

        InkPrinter printer = new InkPrinter(0, 0);
        
        System.out.println("[System] Show the description of printer.");
        printer.description(); // Initial description
        
        System.out.printf("[System] Request printing (# of Request: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: no power on

        System.out.println("[System] Power the printer on.\n");
        printer.setPowerOn(true);
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: not enough paper
        
        int paperLoaded = 5;
        System.out.printf("[System] Loaded papers as: %d (sheets)\n\n", paperLoaded);
        printer.setNumOfPaperLoaded(paperLoaded);
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: not enough black ink
        
        int blackInkLoaded = 75;
        System.out.printf("[System] Charged black ink as: %d (percent) \n\n", blackInkLoaded);
        printer.setBlackInkLoaded(blackInkLoaded);
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Success - Current black ink left: 40 - 10*2 = 20
        
        System.out.println("[System] Re-show the description of printer.");
        printer.description(); // Re show the description
    }

    /**
     * This function check the methods within class 'ColorInkPrinter'
     * @param int numOfReqs
     */
    public static void checkColorPrinter(int numOfReqs) {

        ColorInkPrinter printer = new ColorInkPrinter(0, 0, 0, false);
        
        System.out.println("[System] Show the description of printer.");
        printer.description(); // Initial description
        
        System.out.printf("[System] Request printing (# of Request: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: no power on

        System.out.println("[System] Power the printer on.\n");
        printer.setPowerOn(true);
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: not enough paper
        
        int paperLoaded = 5;
        System.out.printf("[System] Loaded papers as: %d (sheets)\n\n", paperLoaded);
        printer.setNumOfPaperLoaded(paperLoaded);
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: not enough black ink
        
        int blackInkLoaded = 40;
        System.out.printf("[System] Charged black ink as: %d (percent) \n\n", blackInkLoaded);
        printer.setBlackInkLoaded(blackInkLoaded); // Current black ink left: 0 + 40 = 40

        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: not enough black ink
        
        int colorInkLoaded = 100;
        System.out.printf("[System] Charged color ink as: %d (percent) \n\n", colorInkLoaded);
        printer.setColorInkLoaded(colorInkLoaded);// Current color ink left: 0 + 100 = 100

        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Success - Current black ink left: 40 - 10*2 = 20, Current color ink left: 100 - 10*2 = 80

        System.out.println("[System] Change to black-only mode\n");
        printer.setBlackMode(true);
        
        System.out.printf("[System] Newly request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: not enough black ink
        
        blackInkLoaded = 45;
        System.out.printf("[System] Charged black ink as: %d (percent) \n\n", blackInkLoaded);
        printer.setBlackInkLoaded(blackInkLoaded); // Current black ink left: 20 + 45 = 65
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Success - Current black ink left: 65 - 20*2 = 25, Current color ink left: 80 - 0 = 80
        
        String docType = "PDF";
        numOfReqs = 1;
        System.out.printf("[System] Request scanning '%s' document (# of Requests: %d)\n", docType, numOfReqs);
        printer.scan(docType, numOfReqs, false);
        
        System.out.printf("[System] Request scanning and printing '%s' document (# of Requests: %d)\n", docType, numOfReqs);
        printer.scan(docType, numOfReqs, true);// Success - Current black ink left: 25 - 20*1 = 5, Current color ink left: 80 - 0 = 80
        
        System.out.println("[System] Re-show the description of printer.");
        printer.description(); // Re show the description
    }
    
    /**
     * This function check the methods within class 'Printer3D'
     * @param int numOfReqs
     */
    public static void checkPrinter3D(int numOfReqs) {
        Printer3D printer = new Printer3D(0);
        
        System.out.println("[System] Show the description of printer.");
        printer.description(); // Initial description
        
        System.out.printf("[System] Request printing (# of Request: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: no power on

        System.out.println("[System] Power the printer on.\n");
        printer.setPowerOn(true);
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Fail to print: not enough filament
        
        int filamentLoaded = 75;
        System.out.printf("[System] Charged filaments as: %d (percent) \n\n", filamentLoaded);
        printer.setFilamentLoaded(filamentLoaded); // Current filament left: 0 + 75 = 75
        
        System.out.printf("[System] Re-request printing (# of Requests: %d)\n", numOfReqs);
        printer.printRequest(numOfReqs); // Success - Current filament left: 75 - 30*2 = 15
        
        System.out.println("[System] Re-show the description of printer.");
        printer.description(); // Re show the description
    }
    
    public static void main(String[] args) {
        
        int numOfReqs = 2; // general use variable set
        
        System.out.println("<<1>> Check Class 'InkPrinter'");
        System.out.println();
        
        checkBlackPrinter(numOfReqs);
        
        System.out.println("**********************************************************");
        System.out.println();
        
        System.out.println("<<2>> Check Class 'ColorInkPrinter'");
        System.out.println();

        checkColorPrinter(numOfReqs);
      
        System.out.println("**********************************************************");
        System.out.println();
        
        System.out.println("<<3>> Check Class 'PrinterTestor'");
        System.out.println();
        
        checkPrinter3D(numOfReqs);

        System.out.println("**********************************************************");
    }

}
