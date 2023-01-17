package SalesProgram;

import SalesProgram.view.InvoiceAction;
import SalesProgram.view.MainMenuOfOptions;
import static SalesProgram.controller.InvoiceController.ShowAllInvoices;

public class Main {
    public static void main(String[] args) {
        runSalesInvoiceApplication();
    }

    public static void runSalesInvoiceApplication() {
        MainMenuOfOptions mainMenuOfOptions = new MainMenuOfOptions();
        InvoiceAction invoiceAction = new InvoiceAction();
        mainMenuOfOptions.menuOfOptions();
        switch (mainMenuOfOptions.getSelectedOption()) {
            case 1 : ShowAllInvoices(true);
            break;
            case 2 : invoiceAction.addNewInvoice();
                break;
            case 3 : invoiceAction.addItemToStoredInvoice();
                break;
            case 4 : invoiceAction.openSpecificInvoice();
                break;
            case 5 : invoiceAction.deleteInvoice();
                break;
            default: System.exit(0);
        }
    }
}