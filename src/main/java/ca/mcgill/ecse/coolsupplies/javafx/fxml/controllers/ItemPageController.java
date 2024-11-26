package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet3Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;

public class ItemPageController {

    @FXML
    private Button updateItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button addItemButton;

    @FXML
    private ChoiceBox<TOItem> deleteItemChoiceBox;

    @FXML
    private ChoiceBox<TOItem> updateItemChoiceBox;

    @FXML
    private TextField addItemNameTextField;

    @FXML
    private TextField updateItemPriceTextField;


    @FXML
    private TextField addItemPriceTextField;

    @FXML
    private TextField updateItemNameTextField;
    


    
    
/**
 * Initializes the ItemPageController by setting up refresh event handlers 
 * for the updateItemChoiceBox and deleteItemChoiceBox. These handlers 
 * update the items in the choice boxes upon receiving a refresh event. 
 * Additionally, registers the choice boxes to listen for refresh events 
 * from the CoolSuppliesFxmlView.
 * @author Jiaduo Xing
 */
     @FXML
     public void initialize(){
      updateItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
        updateItemChoiceBox.setItems(ViewUtils.getItems());
        updateItemChoiceBox.setValue(null);
       } );
    	 
    	 deleteItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
        deleteItemChoiceBox.setItems(ViewUtils.getItems());
        deleteItemChoiceBox.setValue(null);
       } );

       CoolSuppliesFxmlView.getInstance().registerRefreshEvent(updateItemChoiceBox, deleteItemChoiceBox);
     }

   

    /**
     * Handles the add item button click event. Retrieves the item name and price from the UI, and calls the addItem method of the CoolSuppliesFeatureSet3Controller. If the item is successfully added, refreshes the UI.
     * @author Jiaduo Xing
     */
    @FXML
    void addItemClicked(ActionEvent event) {
    	String itemName = addItemNameTextField.getText();
    	String itemPrice = addItemPriceTextField.getText();
    	if (itemName == null || itemName.trim().isEmpty()){
    	      ViewUtils.showError("Please input a valid item name.");
    	}
      if (itemPrice == null || itemPrice.trim().isEmpty() || !itemPrice.matches("[0-9]+(\\.[0-9]+)?")){
        ViewUtils.showError("Please input a valid item Price.");
  }
    	else{
    	      if (successful(CoolSuppliesFeatureSet3Controller.addItem(itemName, Integer.parseInt(itemPrice)))){
    	        CoolSuppliesFxmlView.getInstance().refresh();
    	      }
    	}
    }

   

    /**
     * Handles the delete item button click event. Retrieves the selected item name from the UI, and calls the deleteItem method of the CoolSuppliesFeatureSet3Controller. If the item is successfully deleted, refreshes the UI.
     * @author Jiaduo Xing
     */
    @FXML
    void deleteItemClicked(ActionEvent event) {
    	TOItem item = deleteItemChoiceBox.getValue();
        if (item == null){
          ViewUtils.showError("Please select a valid item name.");
        }
    /**
     * Handles the update item button click event. Retrieves the selected item name and price from the UI, and calls the updateItem method of the CoolSuppliesFeatureSet3Controller. If the item is successfully updated, refreshes the UI.
     * @author Jiaduo Xing
     */
        else{
          callController(CoolSuppliesFeatureSet3Controller.deleteItem(item.getName()));
        }
    }

   
    /**
     * Handles the update item button click event. Retrieves the selected item name and new name and price from the UI, and calls the updateItem method of the CoolSuppliesFeatureSet3Controller. If the item is successfully updated, refreshes the UI.
     * @author Jiaduo Xing
     */
    @FXML
    void updateItemClicked(ActionEvent event) {
    	TOItem item = deleteItemChoiceBox.getValue();
    	String newNameString = updateItemNameTextField.getText();
    	String newPriceString = updateItemPriceTextField.getText();
    	
    	if (item == null) {
    		ViewUtils.showError("Please select a valid item name.");
    	}
    	else if (newNameString == null || newNameString.trim().isEmpty()){
    		ViewUtils.showError("Please input a valid item name.");
    	}
      else if (newPriceString == null || newPriceString.trim().isEmpty()|| !newPriceString.matches("[0-9]+(\\.[0-9]+)?")){
    		ViewUtils.showError("Please input a valid item price.");
    	}
    	else{
    	      if (successful(CoolSuppliesFeatureSet3Controller.updateItem(item.getName(), newNameString, Integer.parseInt(newPriceString)))){
    	        CoolSuppliesFxmlView.getInstance().refresh();
    	      }
    	    }
    }

}