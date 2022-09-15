/**
 * This class defines a coffee object which is a child object of MenuItem.
 * coffee class defines coffee by size, addins and order quantity;
 * It has add, remove functions to add and remove addins.
 * it has function to calculte the total, tax, and total+tax.
 * It also has a standard compareTo function and to string function.
 * It has five private instance variables and a constructor.
 *  * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;
import java.util.ArrayList;


public class Coffee extends MenuItem implements Customizable{
    private String size; // Short, Tall, Grande, Venti
    private ArrayList<String> addins = new ArrayList<String>();
    private double price;
    private int quantity;
    private double tax;
    private double total;

    double shortCoffee_price = 1.69;
    double tallCoffee_price = shortCoffee_price + 0.40;
    double grandeCoffee_price = tallCoffee_price + 0.40;
    double ventiCoffee_price = grandeCoffee_price  + 0.40;
    double addin_charges = 0.3;
    double NJTaxRate = 0.06625;


    /**
     * This is a constructor for coffee
     * @param size
     * @param addins
     * @param quantity
     */
    public Coffee(String size,ArrayList<String> addins, int quantity) {
        this.quantity = quantity;
        this.size = size;
        this.addins = addins;
    }

    /**
     * This method will add an addin to the addin arraylist
     * @param obj
     * @return true if an addin is added
     */
    public boolean add(Object obj) {
        addins.add((String)obj);
        return true;
    };

    /**
     * This method will remove an addin from the addin arraylist
     * @param obj
     * @return true if removed
     */
    public boolean remove(Object obj) {
        addins.remove((String)obj);
        return true;
    };

    /**
     * this method calculates the coffee price
     * @return price in a double
     */

    @Override
    public double itemPrice(){
        price = 0;
        if (this.size.equals("Short")){
            price = shortCoffee_price;
        }
        if (this.size.equals("Tall")){
            price = tallCoffee_price;
        }
        if (this.size.equals("Grande")){
            price = grandeCoffee_price;
        }
        if (this.size.equals("Venti")){
            price = ventiCoffee_price;
        }
        price = price + addin_charges * addins.size();
        this.price = price * quantity;
        return price;
    }

    /**
     * This method calculates NJ sales tax for coffee
     * @return tax in a double
     */
    public double calculateTax() {
        tax = price * NJTaxRate;
        tax = Math.round(tax * 100.0) / 100.0;
        return tax;
    }

    /**
     * This method calculates the total + tax for coffee
     * @return total price for coffee
     */
    public double calculateTotal() {
        total = price + tax;
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }

    /**
     * Standard toString method that converts Coffee object to a string
     * @return
     */
    public String toString(){
        return "Coffee " + this.size + " " +addins + " Quantity: " + this.quantity;
    }

    /**
     * a compareTo method that determines if Coffee is the same the another Coffee object.
     * by the addins, size, and quantity
     * @param coffee
     * @return true if they are the same; false if they are different
     */
    public boolean compareTo( Coffee coffee) {
        if (this.addins.equals(coffee.addins) && this.size.equals(coffee.size) && this.quantity == coffee.quantity) {
            return true;
        }
        else{
            return false;
        }
    }

}
