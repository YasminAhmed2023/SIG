package SalesProgram.view;

import java.util.InputMismatchException;
import java.util.Scanner;
public class MainMenuOfOptions {
    private int mainMenuOfOptions = 0;

    public MainMenuOfOptions() {
        this.mainMenuOfOptions = 0;
    }

    public int getSelectedOption() {
        return mainMenuOfOptions;
    }

    public void menuOfOptions() {
        System.out.println("Please select from options :");
        System.out.println("---------------------------");
        System.out.println("1-Write 1 to Show All Invoices");
        System.out.println("2-Write 2 to Add New Invoice user");
        System.out.println("3-Write 3 to Add Item to Specific User");
        System.out.println("4-Write 4 to Search for Specific Invoice by Invoice Number");
        System.out.println("5-Write 5 to Delete Invoice");
        System.out.println("6-Write 6 to Close App");
        System.out.println("Please Enter your option:");
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                this.mainMenuOfOptions = Integer.valueOf(scanner.next("[1-6]"));
            } catch (InputMismatchException ex) {
                System.out.println("Wrong Number, Please write option from 1 to 6:");
            }
        } while (!(this.mainMenuOfOptions >= 1 && this.mainMenuOfOptions <= 6));
    }
}