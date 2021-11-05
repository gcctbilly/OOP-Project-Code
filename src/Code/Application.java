package Code;

import Code.model.Clevis;

import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        Clevis clevis = new Clevis();
        Scanner input = new Scanner(System.in);
        String command;
        // Initialize and utilize the system
        System.out.println("Thanks for using Clevis");
        while(true) {
            System.out.print("Please enter the command: ");
            command = input.nextLine();
            clevis.process(command);
        }

    }

}
