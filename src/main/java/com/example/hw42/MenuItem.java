/**
 * MenuItem is the parent class for coffee and donut objects
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;
public class MenuItem {
    private double price;

    /**
     * This method gets overrided in coffee and donut class
     * @return
     */
    public double itemPrice() {
        return this.price;
    }

    /**
     * This method determines if a menuItem is the same as another menuItem
     * @param item
     * @return true if they are the same; false if they are not
     */

    public boolean equals (MenuItem item) {
        if (item == this) {
            return true;
        }
        if (item instanceof Donut) {
            if (((Donut) item).compareTo((Donut) (this))) {
                return true;
            }
        }
        if (item instanceof Coffee) {
            if (((Coffee)item).compareTo((Coffee) (this))) {
                return true;
            }
        }
        return false;
    }
}