/**
 * This order object is an arraylist that holds all the menuitems.
 * It implements customizable which has add and remove functions.
 * @author Haoyue ZHou Yiwen Hong
 */
package com.example.hw42;
import java.util.ArrayList;

public class Order implements Customizable {
    private ArrayList<MenuItem> currentOrder = new ArrayList<MenuItem>();
    private double subtotal;
    private double tax;
    private double total;

    public int getSize(){
        return currentOrder.size();
    }

    /**
     * a getter method to have access to the arraylist
     * @return
     */
    public ArrayList<MenuItem> getCurrentOrder() {
        return currentOrder;
    }

    /**
     * this method adds object into order
     * @param obj
     * @return true if added
     */
    public boolean add(Object obj) {
        currentOrder.add((MenuItem) obj);
        return true;
    };
    /**
     * this method removes object from order
     * @param obj
     * @return return true if removed
     */
    public boolean remove(Object obj) {
        if (obj instanceof Donut) {
            for( int i = 0; i < currentOrder.size();i++) {
                if (currentOrder.get(i) instanceof Donut) {
                    if (currentOrder.get(i).equals((Donut) obj)) {
                        currentOrder.remove(i);
                        return true;
                    }
                }
            }
        }
        if (obj instanceof Coffee) {
            for( int i = 0; i < currentOrder.size();i++) {
                if (currentOrder.get(i) instanceof Coffee) {
                    if (currentOrder.get(i).equals((Coffee) obj)) {
                        currentOrder.remove(i);
                        return true;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    };

    /**
     * This method calculates the subtotal sums of the order
     * @return
     */
    public double calculateSubtotal(){
        double price = 0;
        for (int i = 0; i < currentOrder.size(); i ++) {
            price += currentOrder.get(i).itemPrice();
        }
        subtotal = price;
        return subtotal;
    }
    /**
     * This method calculates the sum of subtotal for the order
     * @return subtotal
     */

    /**
     * This method calculates the sum of tax for the order
     * @return tax
     */
    public double calculateTax() {
        tax = 0;
        tax = subtotal * 0.06625;
        tax = Math.round(tax * 100.0) / 100.0;
        return tax;
    }

    /**
     * This method calculates the sum of total for the order
     * @return total
     */
    public double calculateTotal() {
        total = 0;
        total = subtotal + tax;
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }

    /**
     * toString method that returns order object to a string
     * @return
     */
    @Override
    public String toString() {
        return "" +
                currentOrder;
    }

}
