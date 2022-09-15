/**
 * This class defines a donut object which is a child object of Menuitem.
 * It has 6 private instance variables and a constructor
 * It has functions to calculate the price, tax and total price.
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;
public class Donut extends MenuItem{
    private String type; // yeast donut, cake donut, donut hole
    private String flavor;
    private double price;
    private int quantity;
    private double tax;
    private double total;

    double yeast_price = 1.59;
    double cake_price = 1.79;
    double hole_price = 0.39;
    double NJTaxRate = 0.06625;

    /**
     * donut constructor
     * @param type
     * @param flavor
     * @param quantity
     */
    public Donut (String type, String flavor,int quantity){
        this.quantity = quantity;
        this.type = type;
        this.flavor = flavor;
    }

    /**
     * This method calculate the price of a donut
     * @return item price in a double
     */
    @Override
    public double itemPrice(){
        price = 0;
        if (this.type.equals("yeast donut")){
            price = yeast_price;
        }
        if (this.type.equals("cake donut")){
            price = cake_price;
        }
        if (this.type.equals("donut hole")){
            price = hole_price;
        }
        this.price = price * this.quantity;
        price = Math.round(price * 100.0) / 100.0;
        return price;
    }

    /**
     * calculate the sales tax of a donut
     * @return tax in a double
     */
    public double calculateTax() {
        tax = price * NJTaxRate;
        tax = Math.round(tax * 100.0) / 100.0;
        return tax;
    }

    /**
     * calculates the total price of a donut
     * @return total price in a double
     */
    public double calculateTotal() {
        total = price + tax;
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }

    /**
     * toString method that converts a donut object to a string
     * @return
     */
    @Override
    public String toString() {
        return this.flavor + " "+ this.type + " Quantity: " + this.quantity;
    }

    /**
     * compareTo method that determines if a donut is the same as another donut based on donut type, flavor, and quantity;
     * @param donut
     * @return true if they are the same donut; else, fasle.
     */
    public boolean compareTo (Donut donut) {
        if (this.type.equals(donut.type) && this.flavor.equals(donut.flavor) && this.quantity == donut.quantity){
            return true;
        }
        else{
            return false;
        }
    }
}
