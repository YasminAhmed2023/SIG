package SalesProgram.model;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private long invoiceNum;
    private Date invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLines> invoiceLines = new ArrayList<InvoiceLines>();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("d/MM/yyyy");
    public long getInvoiceNum() {return invoiceNum;}
    public void setInvoiceNum(long invoiceNum) {
        this.invoiceNum = invoiceNum;
    }
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getInvoiceDateAsString() {return SIMPLE_DATE_FORMAT.format(invoiceDate);}
    public void setInvoiceDate(String invoiceDate) {this.invoiceDate = SIMPLE_DATE_FORMAT.parse(invoiceDate, new ParsePosition(0));}
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public ArrayList<InvoiceLines> getInvoiceLines() {
        return invoiceLines;
    }
    public void setInvoiceLines(ArrayList<InvoiceLines> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
}