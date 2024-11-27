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
    private ChoiceBox<TOItem> ItemChoiceBox;

    @FXML
    private TextField ItemPriceTextField;

    @FXML
    private TextField ItemNameTextField;
    


    
    

/**
 * Initializes the item page controller by setting up the ItemChoiceBox.
 * It adds an event handler to update the item list and clears the selection
 * when a refresh event is triggered. It also registers the ItemChoiceBox to
 * receive refresh events from the CoolSuppliesFxmlView.
 * @author Jiaduo Xing
 */
     @FXML
     public void initialize(){
      ItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
        ItemChoiceBox.setItems(ViewUtils.getItems());
        ItemChoiceBox.setValue(null);
       } );
    	 
      
       CoolSuppliesFxmlView.getInstance().registerRefreshEvent(ItemChoiceBox);
     }

   

   
/**
 * Handles the add item button click event. Retrieves the item name and price from the UI, and calls the addItem method of the CoolSuppliesFeatureSet3Controller. If the item is successfully added, refreshes the UI.
 * @author Jiaduo Xing
 */
    @FXML
    void addItemClicked(ActionEvent event) {
    	String itemName = ItemNameTextField.getText();
    	String itemPrice = ItemPriceTextField.getText();
    	if (itemName == null || itemName.trim().isEmpty()){
    	      ViewUtils.showError("Please input a valid item name.");
    	}
      if (itemPrice == null || itemPrice.trim().isEmpty() || !itemPrice.matches("[0-9]+(\\.[0-9]+)?")){
        ViewUtils.showError("Please input a valid item Price.");
  }
    	else{
    	      if (successful(CoolSuppliesFeatureSet3Controller.addItem(itemName, Integer.parseInt(itemPrice)))){
            ItemPriceTextField.setText("");
            ItemNameTextField.setText("");
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
    	TOItem item = ItemChoiceBox.getValue();
        if (item == null){
          ViewUtils.showError("Please select an item.");
        }
  
        else{
          callController(CoolSuppliesFeatureSet3Controller.deleteItem(item.getName())){
            ItemPriceTextField.setText("");
            ItemNameTextField.setText("");
            CoolSuppliesFxmlView.getInstance().refresh();
          }
    }

   
  
    /**
     * Handles the update item button click event. Retrieves the selected item name from the UI, and retrieves the new item name and price from the UI. If the item is successfully updated, refreshes the UI.
     * @author Jiaduo Xing
     */
    @FXML
    void updateItemClicked(ActionEvent event) {
    	TOItem item = ItemChoiceBox.getValue();
    	String newNameString = ItemNameTextField.getText();
    	String newPriceString = ItemPriceTextField.getText();
    	
    	if (item == null) {
    		ViewUtils.showError("Please select an item.");
    	}
    	else if (newNameString == null || newNameString.trim().isEmpty()){
    		ViewUtils.showError("Please input a valid item name.");
    	}
      else if (newPriceString == null || newPriceString.trim().isEmpty()|| !newPriceString.matches("[0-9]+(\\.[0-9]+)?")){
    		ViewUtils.showError("Please input a valid item price.");
    	}
    	else{
    	      if (successful(CoolSuppliesFeatureSet3Controller.updateItem(item.getName(), newNameString, Integer.parseInt(newPriceString)))){
              ItemPriceTextField.setText("");
              ItemNameTextField.setText("");
    	        CoolSuppliesFxmlView.getInstance().refresh();
    	      }
    	    }
    }

}