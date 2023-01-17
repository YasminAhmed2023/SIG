package SalesProgram.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataController {
    public static int getInvoiceNumber() {
        String invoiceNumberInputString;
        int invoiceNumber = 0;
        boolean correctInput;
        do{
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                invoiceNumberInputString = scanner.next("^[0-9]+$");
                invoiceNumber = Integer.parseInt(invoiceNumberInputString);
                if (invoiceNumber == 0){
                    correctInput = false;
                    System.out.println("Invoice number can't be Zero, Please enter correct invoice number:");
                }else {
                    correctInput = true;
                }
            } catch (InputMismatchException ex) {
                correctInput = false;
                System.out.println("Wrong Number, please enter invoice number by numbers only:");
            }
        }while(!correctInput);
        return invoiceNumber;
    }

    public static String getCustomerNameInput(){
        String customerNameInput = null;
        boolean correctInput;
        do{
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                customerNameInput = scanner.next("^[[\\t\\s][a-zA-Z]]+$");
                if (customerNameInput.length()<3) {
                    correctInput = false;
                    System.out.println("Please enter a Customer Name with 3 characters or more:");
                } else {
                    correctInput = true;
                }
            } catch (InputMismatchException e) {
                correctInput = false;
                System.out.println("Wrong Customer Name, please enter a Customer Name by characters only:");
            }
        } while(!correctInput);
        return customerNameInput;
    }

    public static String getItemName(){
        String itemNameInput = null;
        boolean correctInput = false;
        do{
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                itemNameInput = scanner.next("^[[\\t\\s][a-zA-Z]]+$");
                if (itemNameInput.length()<3) {
                    correctInput = false;
                    System.out.println("Please enter a Customer Name with 3 characters or more:");
                } else {
                    correctInput = true;
                }
            } catch (InputMismatchException e) {
                correctInput = false;
                System.out.println("Wrong Item Name, please enter Item Name by characters only:");
            }
        } while(!correctInput);
        return itemNameInput;
    }

    public static Double getItemPrice(){
        String itemPriceInputString = null;
        Double itemPriceInput = 0.0;
        boolean correctInput = false;
        do{
            correctInput = false;
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                itemPriceInputString = scanner.next("^[[.][0-9]]+$");
                correctInput = true;
                itemPriceInput = Double.valueOf(itemPriceInputString);
                if (itemPriceInput == 0.0){
                    correctInput = true;
                    System.out.println("Adding Item.");
                }
            } catch (InputMismatchException e) {
                correctInput = false;
                System.out.println("Wrong Number, please enter item price by numbers only:");
            }
        }while(!correctInput);
        return itemPriceInput;
    }

    public static int getCount(){
        String countInputString = null;
        int countInput = 0;
        boolean correctInput = false;
        do{
            correctInput = false;
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                countInputString = scanner.next("^[.0-9]+$");
                if (Integer.valueOf(countInputString) == 0){
                    correctInput = false;
                    System.out.println("Count can't be Zero, Please enter right number:");
                }else {
                    correctInput = true;
                    countInput = Integer.valueOf(countInputString);
                }
            } catch (InputMismatchException e) {
                correctInput = false;
                System.out.println("Wrong Number, please enter invoice number by numbers only:");
            }
        }while(!correctInput);
        return countInput;
    }
}