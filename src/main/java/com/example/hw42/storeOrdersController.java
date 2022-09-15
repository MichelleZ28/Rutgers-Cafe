/**
 * This is a controller for the Store order fxml page
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

public class storeOrdersController {

    @FXML
    private ListView<String> orderdisplay;

    @FXML
    private TextField total;

    @FXML
    private ComboBox<Integer> orderNumber;

    @FXML
    public Button closeButton;

    int currentOrder = 0;

    ArrayList<MenuItem> list =  new ArrayList<MenuItem>();
    ArrayList<String> stringlist =  new ArrayList<String>();
    public static StoreOrders so = new StoreOrders();

    /**
     * this method will transfer storeorder from hellocontroller to this controller
     * @param storeOrders
     */
    public void transferso(StoreOrders storeOrders){
        so = storeOrders;
        list = so.getOrders().get(0).getCurrentOrder();
        for (int i = 0; i < so.getOrders().get(0).getSize(); i++){
            stringlist.add(list.get(i).toString());
        }
    }

    /**
     * this display method will a combobox for existing orders and preselects 1
     * it will also display the content of order number 1
     * it will also display subtotal, sales tax and total of the order number 1
     */
    public void display(){
        orderdisplay.setItems(FXCollections.observableArrayList(stringlist));
        so.getOrders().get(0).calculateSubtotal();
        so.getOrders().get(0).calculateTax();
        String itemtotalstring = String.format("%.2f", so.getOrders().get(0).calculateTotal());

        total.setText(itemtotalstring);
        ArrayList<Integer> orderNumberList =  new ArrayList<Integer>();
        for (int i = 1; i< so.getSize() + 1; i++){
            orderNumberList.add(i);
        }
        orderNumber.setItems(FXCollections.observableArrayList(orderNumberList));
        orderNumber.getSelectionModel().selectFirst();
    }

    /**
     * this method captures order number selected by the user and display the content
     * of that order number
     * @param event
     */
    @FXML
    void orderNumberSelection(ActionEvent event){
        ArrayList<MenuItem> orderlist =  new ArrayList<MenuItem>();
        ArrayList<String> stringorderlist =  new ArrayList<String>();
        try {
            currentOrder = orderNumber.getValue() - 1;
        }catch (Exception e){
            currentOrder = 0;
        }
        orderlist = so.getOrders().get(currentOrder).getCurrentOrder();
        for (int i = 0; i < so.getOrders().get(currentOrder).getSize(); i++){
            stringorderlist.add(orderlist.get(i).toString());
        }
        orderdisplay.setItems(FXCollections.observableArrayList(stringorderlist));
        so.getOrders().get(currentOrder).calculateSubtotal();
        so.getOrders().get(currentOrder).calculateTax();
        String itemtotalstring = String.format("%.2f", so.getOrders().get(currentOrder).calculateTotal());
        total.setText(itemtotalstring);
    }

    /**
     * this method will cancel the selected order
     * remove the order from displaying
     * @param event
     * @throws IOException
     */
    @FXML
    void orderCancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController helloController = loader.getController();
            try {
                so.getOrders().remove(currentOrder);
                list = so.getOrders().get(0).getCurrentOrder();
                for (int i = 0; i < so.getOrders().get(0).getSize(); i++) {
                    stringlist.add(list.get(i).toString());
                }
                orderdisplay.setItems(FXCollections.observableArrayList(stringlist));
                String itemtotalstring = String.format("%.2f", so.getOrders().get(0).calculateTotal());
                total.setText(itemtotalstring);
                ArrayList<Integer> orderNumberList = new ArrayList<Integer>();
                for (int i = 1; i < so.getSize() + 1; i++) {
                    orderNumberList.add(i);
                }
                orderNumber.setItems(FXCollections.observableArrayList(orderNumberList));
                orderNumber.getSelectionModel().selectFirst();
            }
            catch (Exception e){
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
                var alert = new Alert(Alert.AlertType.NONE);
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.setTitle("Error!");
                alert.setHeaderText("Store does not contain any orders!");
                alert.setContentText("Please add orders to your store.");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }

    /**
     * this method is triggered by the "Export Orders" button and will export a txt file
     * with the content of the storeorder.
     * @param event
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
        @FXML
        void printtotxt(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
            PrintWriter writer = new PrintWriter("StoreOrdersPrinted.txt", "UTF-8");
            writer.println("List of Store Orders:");
            double total = 0.0;
            for (int i = 0; i < so.getSize(); i++) {
                int ordernumber = i+1;
                writer.println("Order number " + ordernumber + ": " + so.getOrders().get(i) +  " Order total: " + so.getOrders().get(i).calculateTotal());
                total = total +  so.getOrders().get(i).calculateTotal();
            }
            writer.println("Store orders total: " + total);
            writer.close();
        }
    }


