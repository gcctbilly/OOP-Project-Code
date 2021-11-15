package Code;

import Code.model.Clevis;

import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        Clevis clevis = new Clevis();
        Scanner input = new Scanner(System.in);
        String command;
        // Initialize and utilize the system
        System.out.println("Welcome for using Clevis");
        while (true) {
            System.out.println("Please enter the name of the log files like 'java Clevis-html log.html -txt log.txt' : ");
            command = input.nextLine();
            int number = clevis.process(command);
            if(number == 1) break;
        }

        while(true) {
            System.out.print("Please enter the command: ");
            command = input.nextLine();
            int number = clevis.process(command);
            if(number == 2) break;
        }
        System.out.println("Thanks for using!");

    }

}
