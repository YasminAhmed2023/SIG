package SalesProgram.model;

public class InvoiceLines extends  InvoiceHeader{
    private String itemName;
    private double itemPrice;
    private int count;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(double itemPrice) {this.itemPrice = itemPrice;}
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}