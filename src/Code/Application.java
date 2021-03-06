package Code;

import Code.model.Clevis;

import java.util.Scanner;

/**
 * There are no functions and methods in the class. This class is used to present the     user interface and run the software
 */
public class Application {
    /**
     * @param args 1
     */
    public static void main(String[] args){
        Clevis clevis = new Clevis();
        Scanner input = new Scanner(System.in);
        String command;
        String filePathHtml;
        String filePathTxt;

        // Initialize and utilize the system
        System.out.println("Welcome for using Clevis");
        //create tht file
        if(args.length != 0) {
            filePathHtml = args[1];
            filePathTxt = args[3];
            int fileNumber = clevis.createFile(args[1],args[3]);
            if(fileNumber == 0) {
                System.out.println("SoftWare quit, please run it again with the write name");
                return;
            }
        }

        while(true) {
            System.out.print("Please enter the command: ");
            command = input.nextLine();
            System.out.println(command);
            int number = clevis.process(command);
            if(number == 2) break;
        }
        System.out.println("Thanks for using!");

    }

}
