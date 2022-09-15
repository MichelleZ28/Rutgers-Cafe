/**
 * This is a controller for the coffee ordering fxml page
 * @author Haoyue Zhou Yiwen Hong
 */
package com.example.hw42;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class coffeesController implements Initializable {

    @FXML
    private ComboBox<String> size;
    @FXML
    private ComboBox<Integer> numberofcoffees;
    @FXML
    private CheckBox cream,syrup, milk, caramel, whipped_cream;
    @FXML
    private TextField subtotal;

    String sizing = "";
    int amount = 0;
    double cost = 0;
    double total = 0;
    String totalstring = "";
    Order cartOrder = new Order();

    /**
     * This initialize method sets the starting theme of the coffee ordering page
     * It sets a combobox for the size and presets to short
     * It set a combobox for amount and presets to 1
     * it sets a group of checkbox of addins
     * @param url
     * @param resourceBundle
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        size.setItems(FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti" ));
        numberofcoffees.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9 ));
        size.getSelectionModel().selectFirst();
        numberofcoffees.getSelectionModel().selectFirst();
    }

    /**
     * This method is triggered by the selection of addins by user
     * The subtotal will aslo change based on what coffee size, quantity addins that user selected
     * @param event
     */
    @FXML
    void toggle(ActionEvent event){
        ArrayList<String> addins = new ArrayList<String>();
        sizing = size.getValue();
        amount = numberofcoffees.getValue();
        if (this.cream.isSelected() && !addins.contains("cream")){
            addins.add("cream");
        }
        if (this.syrup.isSelected() && !addins.contains("syrup")){
            addins.add("syrup");
        }
        if (this.milk.isSelected() && !addins.contains("milk")){
            addins.add("milk");
        }
        if (this.caramel.isSelected() && !addins.contains("caramel")){
            addins.add("caramel");
        }
        if (this.whipped_cream.isSelected() && !addins.contains("whipped cream")){
            addins.add("whipped cream");
        }
        Coffee test = new Coffee(sizing, addins, amount);
        cost = test.itemPrice();
        total = cost;
        total = Math.round(total * 100.0) / 100.0;
        totalstring = String.format("%.2f", total);
        subtotal.setText(totalstring);
    }

    /**
     * this method is triggered by the buttoon "Add to Order"
     * It will generate a coffee object and add to the current order list
     * @param event
     * @throws IOException
     */
    @FXML
    void getcoffee(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController helloController= loader.getController();
        ArrayList<String> addins = new ArrayList<String>();
        sizing = size.getValue();
        amount = numberofcoffees.getValue();
        if (this.cream.isSelected()){
            addins.add("cream");
        }
        if (this.syrup.isSelected()){
            addins.add("syrup");
        }
        if (this.milk.isSelected()){
            addins.add("milk");
        }
        if (this.caramel.isSelected()){
            addins.add("caramel");
        }
        if (this.whipped_cream.isSelected()){
            addins.add("whipped cream");
        }
        Coffee test = new Coffee(sizing, addins, amount);
        subtotal.setText("1.69");
        helloController.addCoffee(test);
        cream.setSelected(false);
        syrup.setSelected(false);
        milk.setSelected(false);
        caramel.setSelected(false);
        whipped_cream.setSelected(false);
        size.getSelectionModel().selectFirst();
        numberofcoffees.getSelectionModel().selectFirst();
    }
}
