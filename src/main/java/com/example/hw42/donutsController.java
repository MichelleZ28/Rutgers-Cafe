/**
 * This is a controller for the donut ordering fxml page
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class donutsController implements Initializable {
    @FXML
    private ComboBox<String> donuttypes;
    @FXML
    private ComboBox<Integer> amount;
    @FXML
    private ListView<String> flavors;
    @FXML
    private ListView<String> cart;
    @FXML
    private TextField subtotal;

    String type = "";
    String flavor = "";
    int numberofdonuts = 0;
    double cost = 0;
    double total = 0;
    String totalstring = "";
    Order cartOrder = new Order();
    ArrayList<String> clearlist =  new ArrayList<String>();
    private HelloController helloController;

    /**
     * This initialize method sets the starting page of donut ordering
     * It sets a combobox for donut types wtih three donut types
     * It sets a combobox for amount with 1 - 9
     * It sets a list view for three donut flavors
     * It preselects a donut type, amount and flavors
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        donuttypes.setItems(FXCollections.observableArrayList("yeast donut", "cake donut", "donut hole" ));
        amount.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9 ));
        donuttypes.getSelectionModel().selectFirst();
        amount.getSelectionModel().selectFirst();
        flavors.setItems(FXCollections.observableArrayList("chocolate", "vanilla", "strawberry"));
        flavors.getSelectionModel().selectFirst();
    }

    /**
     * This method is triggered by the click of "add to cart" button.
     * It gets the type flavor and amount of donut
     * It sets the price of donut on display
     * It sets the donut added to the listview on the right side
     * @param event
     */
    @FXML
    void getdonuttypes(ActionEvent event){
        type = donuttypes.getValue();
        numberofdonuts = amount.getValue();
        flavor = flavors.getSelectionModel().getSelectedItem();
        Donut test = new Donut(type, flavor, numberofdonuts);
        cost = test.itemPrice();
        total = total + cost;
        total = Math.round(total * 100.0) / 100.0;
        totalstring = Double.toString(total);
        cart.getItems().add(test.toString());
        subtotal.setText(totalstring);
    }

    /**
     * This method is triggered by the "Remove from cart" button
     * It removes the selected donut from the listview and reduce the subtotal to reflect the new subtotal
     * @param event
     */
    @FXML
    void removefromcart(ActionEvent event){
        try{
            String donutRemoved = cart.getSelectionModel().getSelectedItem();
            cart.getItems().remove(cart.getSelectionModel().getSelectedItem());
            String [] donutString = donutRemoved.split(" ");
            flavor = donutString[0];
            type = donutString[1] + " " + donutString[2];
            numberofdonuts = Integer.parseInt(donutString[4]);
            Donut test = new Donut(type, flavor, numberofdonuts);
            cost = test.itemPrice();
            total = total - cost;
            total = Math.round(total * 100.0) / 100.0;
            totalstring = Double.toString(total);
            subtotal.setText(totalstring);
        }
        catch (Exception e){
            var alert = new Alert(Alert.AlertType.NONE);
            alert.getButtonTypes().addAll(ButtonType.OK);
            alert.setTitle("Error!");
            alert.setHeaderText("No donuts selected");
            alert.setContentText("Please select donut to remove.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    /**
     * This is triggered by the "add to order" button
     * It will add all the donuts in cart to a list of currentorder
     * It will sent a pop-up alert box if the cart is empty
     * @param event
     * @throws IOException
     */

    @FXML
    void toOrder(ActionEvent event) throws IOException{
        ObservableList<String> list = cart.getItems();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController helloController= loader.getController();
        if( !(list.isEmpty())) {
            for (String item : list) {
                String donutRemoved = item;
                String[] donutString = donutRemoved.split(" ");
                flavor = donutString[0];
                type = donutString[1] + " " + donutString[2];
                numberofdonuts = Integer.parseInt(donutString[4]);
                Donut test = new Donut(type, flavor, numberofdonuts);
                helloController.addDonuts(test);
                cartOrder.add((MenuItem) test);
            }
            cart.setItems(FXCollections.observableArrayList(clearlist));
            subtotal.setText("0.00");
        }
        else{
            var alert = new Alert(Alert.AlertType.NONE);
            alert.getButtonTypes().addAll(ButtonType.OK);
            alert.setTitle("Error!");
            alert.setHeaderText("Cart does not contain any donuts!");
            alert.setContentText("Please add donuts to your cart.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }


}
