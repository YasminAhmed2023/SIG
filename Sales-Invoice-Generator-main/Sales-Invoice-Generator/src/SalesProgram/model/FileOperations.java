package SalesProgram.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileOperations {
    final static String INVOICE_HEADER_CSV = "Sales-Invoice-Generator-main/Sales-Invoice-Generator/resources/InvoiceHeader.csv";
    final static String INVOICE_LINES_CSV = "Sales-Invoice-Generator-main/Sales-Invoice-Generator/resources/InvoiceLines.csv";

    public static ArrayList<InvoiceHeader> readFiles() {
        String rowOFData;
        String[] data;
        String[] allData = new String[0];
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
        ArrayList<InvoiceHeader> completedInvoiceHeaders = new ArrayList<>();
        ArrayList<InvoiceLines> invoiceLines = new ArrayList<InvoiceLines>();
        ArrayList<InvoiceLines> invoiceLinesSorted = new ArrayList<InvoiceLines>();
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        InvoiceLines invoiceLine = new InvoiceLines();
        BufferedReader csvReader;
        try {
            csvReader = new BufferedReader(new FileReader(INVOICE_HEADER_CSV));
            while ((rowOFData = csvReader.readLine()) != null) {
                data = rowOFData.split(",");
                int firstLength = allData.length;
                allData = Arrays.copyOf(allData, firstLength + data.length);
                 for (int i = 0; i < data.length; i++) {
                    allData[firstLength + i] = data[i];
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't read this file.");
            System.out.println("Please make sure the InvoiceHeader.csv file exists.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't read this file.");
            System.out.println("Please ensure that the InvoiceHeader.csv file is not in use and can be opened.");
            e.printStackTrace();
        }
        //Check invoice Header
        try {
            for (int i = 0; i < allData.length; i += 3) {
                invoiceHeader = new InvoiceHeader();
                invoiceHeader.setInvoiceNum(Integer.parseInt(allData[i]));
                invoiceHeader.setInvoiceDate(allData[i + 1]);
                invoiceHeader.setCustomerName(allData[i + 2]);
                invoiceHeaders.add(invoiceHeader);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid data in the file.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data");
            e.printStackTrace();
        }

        try {
            csvReader = new BufferedReader(new FileReader(INVOICE_LINES_CSV));
            allData = new String[0];
            while ((rowOFData = csvReader.readLine()) != null) {
                data = rowOFData.split(",");
                int firstLength = allData.length;
                allData = Arrays.copyOf(allData, firstLength + data.length);
                for (int i = 0; i < data.length; i++) {
                    allData[firstLength + i] = data[i];
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The read operation failed.");
            System.out.println("Please make sure the InvoiceLines.csv file exists and has (.csv) extension.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("The read operation failed.");
            System.out.println("Please ensure that the InvoiceLines.csv file is not in use and is not corrupted.");
            e.printStackTrace();
        }
        // Check Invoice lines
        try {
            for (int i = 0; i < allData.length; i += 4) {
                invoiceLine = new InvoiceLines();
                invoiceLine.setInvoiceNum(Integer.parseInt(allData[i]));
                invoiceLine.setItemName(allData[i + 1]);
                invoiceLine.setItemPrice(Double.parseDouble(allData[i + 2]));
                invoiceLine.setCount(Integer.parseInt(allData[i + 3]));
                invoiceLines.add(invoiceLine);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid data in the file.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data");
            e.printStackTrace();
        }
        for (InvoiceHeader invoiceHeaderRow : invoiceHeaders) {
            invoiceLinesSorted = new ArrayList<InvoiceLines>();
            for (InvoiceLines invoiceLinesRow : invoiceLines) {
                if (invoiceHeaderRow.getInvoiceNum() == invoiceLinesRow.getInvoiceNum()) {
                    invoiceLinesSorted.add(invoiceLinesRow);
                }
            }
            invoiceHeaderRow.setInvoiceLines(invoiceLinesSorted);
            completedInvoiceHeaders.add(invoiceHeaderRow);
        }
        return completedInvoiceHeaders;
    }

    //Write on csv File
    public static void writeFiles(ArrayList<InvoiceHeader> invoiceHeaders) {
        int i = 0, j = 0;
        for (InvoiceHeader invoiceHeader : invoiceHeaders) {
            saveHeaderToFile(invoiceHeader, (i == 0) ? false : true);
            i++;
            for (InvoiceLines invoiceLines : invoiceHeader.getInvoiceLines()) {
                saveLineToFile(invoiceLines, (j == 0) ? false : true);
                j++;
                invoiceLines = new InvoiceLines();
            }
            invoiceHeader = new InvoiceHeader();
        }
    }

    public static void saveHeaderToFile(InvoiceHeader invoiceHeader, boolean isNewFile) {
        try {
            FileWriter csvWriter = new FileWriter(INVOICE_HEADER_CSV, isNewFile);
            csvWriter.append(String.valueOf(invoiceHeader.getInvoiceNum()));
            csvWriter.append(",");
            csvWriter.append(invoiceHeader.getInvoiceDateAsString());
            csvWriter.append(",");
            csvWriter.append(invoiceHeader.getCustomerName());
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't Write in this file.");
            System.out.println("Please make sure the InvoiceHeader.csv file exists.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't Write in this file.");
            System.out.println("Please ensure that the InvoiceHeader.csv file is not in use and can be opened");
            e.printStackTrace();
        }

    }

    public static void saveLineToFile(InvoiceLines invoiceLinesRow, boolean isNewFile) {
        try {
            FileWriter csvWriter = new FileWriter(INVOICE_LINES_CSV, isNewFile);
            csvWriter.append(String.valueOf(invoiceLinesRow.getInvoiceNum()));
            csvWriter.append(",");
            csvWriter.append(invoiceLinesRow.getItemName());
            csvWriter.append(",");
            csvWriter.append(String.valueOf(invoiceLinesRow.getItemPrice()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(invoiceLinesRow.getCount()));
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't Write in this file.");
            System.out.println("Please make sure the InvoiceHeader.csv file exists.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't Write in this file.");
            System.out.println("Please ensure that the InvoiceHeader.csv file is not in use and can be opened.");
            e.printStackTrace();
        }
    }
}