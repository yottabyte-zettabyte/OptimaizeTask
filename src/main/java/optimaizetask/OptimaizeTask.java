package optimaizetask;

import java.util.Scanner;

public class OptimaizeTask {

    public static void main(String[] args) {
        
        System.out.println("For commands description please use '-help'.");
        
        System.out.println();
        System.out.println("Please enter a string or command: ");
        
        String inputStr = "";
        Scanner scanner = new Scanner(System.in);
        
        while(!inputStr.trim().equals("-quit")) {
            
            inputStr = scanner.nextLine();
            
            switch (inputStr.trim()) {
                case "-help" : {
                    Service.getInstance().printHelpDescription();
                    break;
                }
                case "-stats" : {
                    Service.getInstance().printStatistics();
                    break;
                }
                default: {
                    
                    if(inputStr.trim().isEmpty()) {
                        continue;
                    }
                    
                    Service.getInstance().saveInputString(inputStr);
                    break;
                }
            }
        }
    }
}
