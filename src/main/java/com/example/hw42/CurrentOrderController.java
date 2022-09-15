/**
 * This is a controller for the current order fxml page
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentOrderController{

    Order o = new Order();
    Order clear = new Order();

    @FXML
    private ListView<String> orderlist;

    @FXML
    private TextField Subtotal,salesTax, total;


    ArrayList<MenuItem> list =  new ArrayList<MenuItem>();
    ArrayList<String> stringlist =  new ArrayList<String>();
    ArrayList<String> clearlist =  new ArrayList<String>();

    /**
     * This method will transfer the currentorder list from hellocontroller to this controller
     * @param order
     */
    public void transfero(Order order){
        o = order;
        list = o.getCurrentOrder();
        for (int i = 0; i < o.getSize(); i++){
            stringlist.add(list.get(i).toString());
        }
    }

    /**
     * This method displays all the menuitems from the current order arraylist
     * It will also display subtotal, salestax and total in textfield
     */
    public void display (){
        String subtotalstring = String.format("%.2f", o.calculateSubtotal());
        String salestaxstring = String.format("%.2f", o.calculateTax());
        String itemtotalstring = String.format("%.2f", o.calculateTotal());
        Subtotal.setText(subtotalstring);
        salesTax.setText(salestaxstring);
        total.setText(itemtotalstring);
        orderlist.setItems(FXCollections.observableArrayList(stringlist));
    }


    /**
     *check if the currentoder is
     * @throws IOException
     */
    public void check() throws IOException{
        if(!(o.getCurrentOrder().isEmpty())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            HelloController helloController = loader.getController();
            helloController.addtostoreorder(o);
            o = clear;
            Subtotal.setText(String.valueOf(0.00));
            salesTax.setText(String.valueOf(0.00));
            total.setText(String.valueOf(0.00));
            orderlist.setItems(FXCollections.observableArrayList(clearlist));
        }
        else{
            var alert = new Alert(Alert.AlertType.NONE);
            alert.getButtonTypes().addAll(ButtonType.OK);
            alert.setTitle("Error!");
            alert.setHeaderText("Order does not contain any items!");
            alert.setContentText("Please add items to your order.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    /**
     * This method will remove the selected MenueItem for the listview and remove it from the currentorder
     * arraylist. If no itme is selected or the current order is empty, it will send a pop-up alert.
     * @throws IOException
     */
    public void remove() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController helloController = loader.getController();
        String flavor = "";
        String type = "";
        int numberofdonuts = 0;
        double cost = 0.0;
        String subtotalstring = "";
        String coffeeSize = "";
        int numberofcoffees = 0;
        if (orderlist.getSelectionModel().isEmpty()){
            var alert = new Alert(Alert.AlertType.NONE);
            alert.getButtonTypes().addAll(ButtonType.OK);
            alert.setTitle("Error!");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select item to remove.");
            Optional<ButtonType> result = alert.showAndWait();
        }
         else {
            String itemRemoved = orderlist.getSelectionModel().getSelectedItem();// original string of either coffee or donut;
            orderlist.getItems().remove(orderlist.getSelectionModel().getSelectedItem());
            String[] donutString = itemRemoved.split(" ");
            ArrayList<String> alladdins = new ArrayList<String>();
            if (donutString[0].equals("Coffee")) {
                coffeeSize = donutString[1];
                numberofcoffees = Integer.parseInt(donutString[donutString.length - 1]);
                String addinsstring = "";
                Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(itemRemoved);
                while (m.find()) {
                    addinsstring = m.group(1);
                }
                alladdins = new ArrayList<>(Arrays.asList(addinsstring.split(", ")));
                ArrayList<String> stringlist =  new ArrayList<String>();
                Coffee cRemove = new Coffee(coffeeSize, alladdins, numberofcoffees);
                if (addinsstring == ""){
                    cRemove = new Coffee(coffeeSize, stringlist, numberofcoffees);
                }
                else {
                    cRemove = new Coffee(coffeeSize, alladdins, numberofcoffees);
                }
                cost = cRemove.itemPrice();
                double subtotal = Double.parseDouble(Subtotal.getText());
                double salestax = Double.parseDouble(salesTax.getText());
                double itemtotal = Double.parseDouble(total.getText());
                subtotal = subtotal - cost;
                subtotal = Math.round(subtotal * 100.0) / 100.0;
                subtotalstring = Double.toString(subtotal);
                salestax = salestax - cRemove.calculateTax();
                salestax = Math.round(salestax * 100.0) / 100.0;
                String salestaxstring = Double.toString(salestax);
                itemtotal = itemtotal - cRemove.calculateTotal();
                itemtotal = Math.round(itemtotal * 100.0) / 100.0;
                String itemtotalstring = Double.toString(itemtotal);
                subtotalstring = String.format("%.2f", subtotal);
                salestaxstring = String.format("%.2f", salestax);
                itemtotalstring = String.format("%.2f", itemtotal);
                if (subtotal < 0) {
                    Subtotal.setText("0.00");
                } else {
                    Subtotal.setText(subtotalstring);
                }
                if (salestax < 0) {
                    salesTax.setText("0.00");
                } else {
                    salesTax.setText(salestaxstring);
                }
                if (itemtotal < 0) {
                    total.setText("0.00");
                } else {
                    total.setText(itemtotalstring);
                }
                helloController.removeCoffee(cRemove);
            } else {
                flavor = donutString[0];
                type = donutString[1] + " " + donutString[2];
                numberofdonuts = Integer.parseInt(donutString[4]);
                Donut test = new Donut(type, flavor, numberofdonuts);

                cost = test.itemPrice();
                double subtotal = Double.parseDouble(Subtotal.getText());
                double salestax = Double.parseDouble(salesTax.getText());
                double itemtotal = Double.parseDouble(total.getText());
                subtotal = subtotal - cost;
                subtotal = Math.round(subtotal * 100.0) / 100.0;
                subtotalstring = Double.toString(subtotal);
                salestax = salestax - test.calculateTax();
                salestax = Math.round(salestax * 100.0) / 100.0;
                String salestaxstring = Double.toString(salestax);
                itemtotal = itemtotal - test.calculateTotal();
                itemtotal = Math.round(itemtotal * 100.0) / 100.0;
                String itemtotalstring = Double.toString(itemtotal);
                subtotalstring = String.format("%.2f", subtotal);
                salestaxstring = String.format("%.2f", salestax);
                itemtotalstring = String.format("%.2f", itemtotal);
                if (subtotal < 0) {
                    Subtotal.setText("0.00");
                } else {
                    Subtotal.setText(subtotalstring);
                }
                if (salestax < 0) {
                    salesTax.setText("0.00");
                } else {
                    salesTax.setText(salestaxstring);
                }
                if (itemtotal < 0) {
                    total.setText("0.00");
                } else {
                    total.setText(itemtotalstring);
                }
                helloController.removeDonuts(test);
            }
        }
    }

}
