/**
 * This StoreOrder object is an arraylist that holds all the orders.
 * It implements customizable which has add and remove functions.
 * It has four private instances
 * @author Haoyue ZHou Yiwen Hong
 */
package com.example.hw42;
import java.util.ArrayList;

public class StoreOrders implements Customizable{
    private ArrayList<Order> storeOrders = new ArrayList<Order>();

    /**
     * This getter method returns the arraylist
     * @return arraylist
     */
    public ArrayList<Order> getOrders(){
        return storeOrders;
    }

    /**
     * This method returns the size of the arraylist
     * @return size
     */
    public int getSize(){
        return storeOrders.size();
    }

    /**
     * This method add an order to the StoreOrder arraylist
     * @param obj
     * @return
     */
    public boolean add(Object obj) {
        storeOrders.add((Order) obj);
        return true;
    };

    /**
     * This method removes an order from the StoreOrder arraylist
     * @param obj
     * @return
     */
    public boolean remove(Object obj) {
        storeOrders.remove((Order)obj);
        return true;
    };

    /**
     * This method converts the StoreOrder object into a string
     * @return
     */
    @Override
    public String toString() {
        return "" +
                storeOrders;
    }
}
