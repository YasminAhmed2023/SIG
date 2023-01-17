package SalesProgram.view;

import SalesProgram.model.InvoiceHeader;
import SalesProgram.model.InvoiceLines;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import static SalesProgram.Main.runSalesInvoiceApplication;
import static SalesProgram.controller.DataController.*;
import static SalesProgram.controller.InvoiceController.*;
import static SalesProgram.controller.InvoiceController.AddNewInvoice;

public class InvoiceAction {


    InvoiceHeader invoiceHeader = new InvoiceHeader();
  

    //Add New Invoice
    public void addNewInvoice() {
        boolean correctInput;
        int optionNumber = 0;
        Date currentDate = new Date();
        System.out.println("Please enter invoice number you want to add:");
        int invoiceNumber = getInvoiceNumber();

        if (isInvoiceNumberExist(invoiceNumber)) {
            System.out.println("This invoice number is already exists.");
        } else {
            invoiceHeader.setInvoiceNum(invoiceNumber);
            System.out.println("Please Enter Customer Name:");
            invoiceHeader.setCustomerName(getCustomerNameInput());
            invoiceHeader.setInvoiceDate(currentDate);
            AddNewInvoice(invoiceHeader.getInvoiceNum(),
                    invoiceHeader.getInvoiceDateAsString(), invoiceHeader.getCustomerName());
            System.out.println("Invoice has been saved without linked items.");
            System.out.println("Please Note to link item Write 1");
        }
        System.out.println("-------------------------------------");
        System.out.println("1- Add an item to the invoice number: " + invoiceNumber);
        System.out.println("2- Create another invoice.");
        System.out.println("3- Return to the main menu.");
        System.out.println("Enter option number:");
        do {
            correctInput = false;
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                optionNumber = Integer.parseInt(scanner.next("[1-3]"));
                correctInput = true;
            } catch (InputMismatchException e) {
                correctInput = false;
                System.out.println(
                        "Wrong Input Number, Please enter 1 to (Add Item to current invoice) or 2 to (Add New Invoice) or 3 to (Main Menu):");
            }
        } while (!correctInput);
        if (optionNumber == 1) {
            InvoiceAction invoiceAction = new InvoiceAction();
            invoiceAction.addItemToStoredInvoice(invoiceHeader.getInvoiceNum());
        } else if (optionNumber == 2) {
            InvoiceAction invoiceAction = new InvoiceAction();
            invoiceAction.addNewInvoice();
        } else if (optionNumber == 3) {
            runSalesInvoiceApplication();
        }
    }

    public void addItemToStoredInvoice() {
        System.out.println("Please enter the invoice number to add the item:");
        addItemToStoredInvoice(getInvoiceNumber());
    }

    public void addItemToStoredInvoice(long invoiceNumberInput) {
        boolean correctInput;
        int optionNumber = 0;
        InvoiceLines invoiceLine = new InvoiceLines();
        if (!isInvoiceNumberExist(invoiceNumberInput)) {
            System.out.println("No invoice with this number: invoiceNumberInput");
            System.out.println("-------------------------------------------------");
            System.out.println("1- Try again with another user invoice number.");
            System.out.println("2- Return to the main menu.");
            System.out.println("Please Enter your option number:");
            do {
                try {
                    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                    optionNumber = Integer.parseInt(scanner.next("^[[1-2]]+$"));
                    correctInput = true;
                } catch (InputMismatchException ex) {
                    correctInput = false;
                    System.out.println("Wrong Number, Please enter 1 to (Try Again) or 2 to (Main Menu):");
                }
            } while (!correctInput);
            if (optionNumber == 1) {
                addItemToStoredInvoice();
            } else if (optionNumber == 2) {
                runSalesInvoiceApplication();
            }
        } else {
            invoiceLine.setInvoiceNum(invoiceNumberInput);
            System.out.println("Please Enter Item Name:");
            invoiceLine.setItemName(getItemName());
            System.out.println("Please Enter Item Price:");
            invoiceLine.setItemPrice(getItemPrice());
            System.out.println("Enter Item Count:");
            invoiceLine.setCount(getCount());
            addItemToInvoice(invoiceLine.getInvoiceNum(), invoiceLine.getItemName(),
                    String.valueOf(invoiceLine.getItemPrice()), String.valueOf(invoiceLine.getCount()));
            System.out.println("Item has been added to invoice number:" + invoiceLine.getInvoiceNum());
            System.out.println("------------------------------------");
            System.out.println("1- Add another item to the invoice.");
            System.out.println("2- Return to the main menu.");
            System.out.println("Enter option number:");
            do {
                try {
                    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                    optionNumber = Integer.parseInt(scanner.next("^[[1-2]]+$"));
                    correctInput = true;
                } catch (InputMismatchException e) {
                    correctInput = false;
                    System.out.println("Wrong input, Please enter 1 to (Add Item) or 2 to (Main Menu):");
                }
            } while (!correctInput);
            if (optionNumber == 1) {
                InvoiceAction invoiceAction = new InvoiceAction();
                invoiceAction.addItemToStoredInvoice(invoiceLine.getInvoiceNum());
            } else if (optionNumber == 2) {
                runSalesInvoiceApplication();
            }
        }
    }

    //Open Specific Invoice
    public void openSpecificInvoice() {
        System.out.println();
        System.out.println("Please enter the invoice number to display it:");
        displaySpecificInvoice(getInvoiceNumber());
    }

    // Delete Specific Invoice
    public void deleteInvoice() {
        System.out.println();
        System.out.println("Please enter the invoice number to delete:");
        deleteSpecificInvoiceRecord(getInvoiceNumber());
        }
    }