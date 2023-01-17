package SalesProgram.controller;

import SalesProgram.model.InvoiceHeader;
import SalesProgram.model.InvoiceLines;
import SalesProgram.view.InvoiceAction;
import SalesProgram.model.FileOperations;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static SalesProgram.Main.runSalesInvoiceApplication;

public class InvoiceController {
    public static void AddNewInvoice(long invoiceNumber, String invoiceDate, String customerName) {
        ArrayList<InvoiceHeader> invoiceHeaders = FileOperations.readFiles();
        InvoiceHeader invoiceHeader;
        if (isInvoiceNumberExist(invoiceNumber)) {
            System.out.println("The invoice number already exists.");
            return;
        }
        invoiceHeader = addNewInvoiceHeader(invoiceNumber, invoiceDate, customerName);
        invoiceHeaders.add(invoiceHeader);
        FileOperations.writeFiles(invoiceHeaders);
    }

    public static void addItemToInvoice(long invoiceNumber, String itemName, String itemPrice, String count) {
        ArrayList<InvoiceHeader> invoiceHeaders = FileOperations.readFiles();
        ArrayList<InvoiceLines> invoiceLines = new ArrayList<InvoiceLines>();
        ArrayList<InvoiceHeader> completedInvoiceHeaders = new ArrayList<InvoiceHeader>();
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        if (!isInvoiceNumberExist(invoiceNumber)) {
            System.out.println("No invoice with the number: " + invoiceNumber);
            return;
        }
        for (InvoiceHeader invoiceHeaderRow : invoiceHeaders) {
            if (invoiceNumber == invoiceHeaderRow.getInvoiceNum()) {
                invoiceLines = invoiceHeaderRow.getInvoiceLines();
                for (InvoiceLines invoiceLineRow : invoiceLines) {
                    if (itemName.equalsIgnoreCase(invoiceLineRow.getItemName())) {
                        System.out.println("The inserted item (" + itemName + ") already exists in the invoice with the number: " + invoiceNumber);
                        return;
                    }
                }
                invoiceLines.add(addNewItem(invoiceNumber, itemName, itemPrice, count));
                invoiceHeaderRow.setInvoiceLines(invoiceLines);
            }
            completedInvoiceHeaders.add(invoiceHeaderRow);
            invoiceHeaderRow = new InvoiceHeader();
            invoiceLines = new ArrayList<InvoiceLines>();
        }
        FileOperations.writeFiles(completedInvoiceHeaders);
    }

    public static boolean isInvoiceNumberExist(long invoiceNumber) {
        ArrayList<InvoiceHeader> freshInvoiceHeaders = FileOperations.readFiles();
        for (InvoiceHeader invoiceHeaderRow : freshInvoiceHeaders) {
            if (invoiceNumber == invoiceHeaderRow.getInvoiceNum()) {
                return true;
            }
        }
        return false;
    }

    public static InvoiceHeader addNewInvoiceHeader(long invoiceNumber, String invoiceDate, String customerName) {
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        invoiceHeader.setInvoiceNum(invoiceNumber);
        invoiceHeader.setInvoiceDate(invoiceDate);
        invoiceHeader.setCustomerName(customerName);
        return invoiceHeader;
    }

    public static InvoiceLines addNewItem(long invoiceNumber, String itemName, String itemPrice, String count) {
        InvoiceLines invoiceLine = new InvoiceLines();
        invoiceLine.setInvoiceNum(invoiceNumber);
        invoiceLine.setItemName(itemName);
        invoiceLine.setItemPrice(Double.parseDouble(itemPrice));
        invoiceLine.setCount(Integer.parseInt(count));
        return invoiceLine;
    }

    public static void ShowAllInvoices(boolean restartApplication) {
        ArrayList<InvoiceHeader> invoiceHeaders = FileOperations.readFiles();
        System.out.println("Please Check All stored invoices:");
        for (InvoiceHeader invoiceHeaderRow : invoiceHeaders) {
            printInvoiceHeaderRow(invoiceHeaderRow);
        }
        System.out.println("-------------------------------------------------------");
        if (restartApplication) {
            runSalesInvoiceApplication();
        }
    }

    //Display specific invoice method
    public static void displaySpecificInvoice(long invoiceNumber) {
        int optionNumber = 0;
        boolean correctInput, isPrinted = false;
        ArrayList<InvoiceHeader> invoiceHeaders = FileOperations.readFiles();
        System.out.println("-------------------------------------------");
        for (InvoiceHeader invoiceHeaderRow : invoiceHeaders) {
            if (invoiceNumber == invoiceHeaderRow.getInvoiceNum()) {
                printInvoiceHeaderRow(invoiceHeaderRow);
                isPrinted = true;
            }
        }
        if (isPrinted == false){
            System.out.println("We don't have any invoice with this number.");
        }
        System.out.println("----------------------------------------");
        System.out.println("1- Display another invoice.");
        System.out.println("2- Back to the main menu.");
        System.out.println("Enter Your option:");
        do {
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                optionNumber = Integer.parseInt(scanner.next("^[[1-2]]+$"));
                correctInput = true;
            } catch (InputMismatchException ex) { correctInput = false;
                System.out.println("Wrong Number, Please enter 1 to (Display another Invoice) or 2 to (Main Menu):");
            }
        } while (!correctInput);
        if (optionNumber == 1) {
            InvoiceAction actionInvoiceView = new InvoiceAction();
            actionInvoiceView.openSpecificInvoice();
        } else if (optionNumber == 2) {
            runSalesInvoiceApplication();
        }
    }

    //Delete specific invoice method
    public static void deleteSpecificInvoiceRecord(long invoiceNumber) {
        int optionNumber = 0;
        boolean correctInput, isDeleted = false;;
        ArrayList<InvoiceHeader> invoiceHeaders = FileOperations.readFiles();
        ArrayList<InvoiceHeader> newInvoiceHeaders = new ArrayList<>();
        for (InvoiceHeader invoiceHeaderRow : invoiceHeaders) {
            if (invoiceNumber == invoiceHeaderRow.getInvoiceNum()) {
                isDeleted = true;
                continue;
            } else {
                newInvoiceHeaders.add(invoiceHeaderRow);
            }
        }
        if (isDeleted == false){
            System.out.println("No invoices with this number.");
        } else {
            FileOperations.writeFiles(newInvoiceHeaders);
            System.out.println("Invoice number " + invoiceNumber + " has been deleted.");
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("1- Delete another invoice.");
        System.out.println("2- Return to the main menu.");
        System.out.println("Enter option number:");
        do {
            try {
                Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                optionNumber = Integer.parseInt(scanner.next("^[[1-2]]+$"));
                correctInput = true;
            } catch (InputMismatchException e) {
                correctInput = false;
                System.out.println("Wrong Number, Please enter 1 to (Display Invoice) or 2 to (Main Menu):");
            }
        } while (!correctInput);
        if (optionNumber == 1) {
            InvoiceAction actionInvoiceView = new InvoiceAction();
            actionInvoiceView.deleteInvoice();
        } else if (optionNumber == 2) {
            runSalesInvoiceApplication();
        }
    }

    private static void printInvoiceHeaderRow(InvoiceHeader invoiceHeaderRow) {
        System.out.println(invoiceHeaderRow.getInvoiceNum());
        System.out.println("{");
        System.out.println(invoiceHeaderRow.getInvoiceDateAsString() + ", " + invoiceHeaderRow.getCustomerName());
        for (InvoiceLines invoiceLinesRow : invoiceHeaderRow.getInvoiceLines()) {
            System.out.println(invoiceLinesRow.getItemName() + ", "
                    + invoiceLinesRow.getItemPrice() + ", "
                    + invoiceLinesRow.getCount());
        }
        System.out.println("}");
    }
}