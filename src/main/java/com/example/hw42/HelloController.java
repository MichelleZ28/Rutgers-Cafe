/**
 * this is the controller for the main RU Cafe fxml page
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController {
    private Parent root;
    private Stage stage;
    public static Order o = new Order();
    public static StoreOrders so = new StoreOrders();
    Order clear = new Order();

    @FXML
    private Label welcomeText;

    /**
     * this method is triggered by clicking on the donut pic
     * It will load the ordering donuts fxml page
     * @param event
     */
    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donuts.fxml"));
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Order Donuts");
            stage.setScene(new Scene (root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method is triggered by the yourorder picture
     * It will open the currentOrder fxml page
     * @param event
     */
    @FXML
    protected void yourOrderClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("currentOrder.fxml"));
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Your Current Order");
            stage.setScene(new Scene (root));
            stage.show();
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.transfero(o);
            currentOrderController.display();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * this method is triggered by clicking on the coffee pic
     * It will load the ordering coffee fxml page
     * @param event
     */
    @FXML
    protected void coffeeClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffees.fxml"));
            root = loader.load();
            stage = new Stage();
            stage.setTitle("Order Coffee");
            stage.setScene(new Scene (root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * this method is triggered by the store order picture
     * It will open the storeOrder fxml page
     * @param event
     */
    @FXML
    protected void storeOrderClick(ActionEvent event) {
        if( !(so.getOrders().isEmpty())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("storeOrders.fxml"));
                root = loader.load();
                stage = new Stage();
                stage.setTitle("Store Orders");
                stage.setScene(new Scene(root));
                stage.show();
                storeOrdersController storeOrdersController = loader.getController();
                storeOrdersController.transferso(so);
                storeOrdersController.display();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            var alert = new Alert(Alert.AlertType.NONE);
            alert.getButtonTypes().addAll(ButtonType.OK);
            alert.setTitle("Error!");
            alert.setHeaderText("Store does not contain any orders!");
            alert.setContentText("Please add orders to your store.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    /**
     * this method returns storeorder arraylist
     * @return storeorder
     */
    public StoreOrders getSO(){
        return so;
    }

    /**
     * this method returns curretorder arraylist
     * @return current order
     */
    public Order getO(){
        return o;
    }

    /**
     * this method adds donuts to current order
     * @param donut
     */
    public void addDonuts (Donut donut) {
        o.add(donut);
    }

    /**
     * this method adds coffee to current order
     * @param coffee
     */
    public void addCoffee(Coffee coffee) {
        o.add(coffee);
    }

    /**
     * this method removes donuts from the current order
     * @param donut
     */
    public void removeDonuts(Donut donut){
        o.remove(donut);
    }

    /**
     * this method removes coffee from the current order
     * @param coffee
     */
    public void removeCoffee(Coffee coffee){
        o.remove(coffee);
    }

    /**
     *this method adds order to store order
     * @param order
     */
    public void addtostoreorder(Order order){
        so.add(order);
        o = clear;
        o.getCurrentOrder().clear();
    }

}