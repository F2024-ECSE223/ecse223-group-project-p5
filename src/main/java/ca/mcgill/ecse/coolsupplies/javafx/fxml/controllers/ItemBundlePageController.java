package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
//import java.sql.Date;
//import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet13Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet12Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet10Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet9Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet8Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet7Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet6Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet5Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet4Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet3Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet2Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet1Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOParent;
import ca.mcgill.ecse.coolsupplies.controller.TOStudent;
import ca.mcgill.ecse.coolsupplies.controller.TOBundleItem;
import ca.mcgill.ecse.coolsupplies.controller.TOGradeBundle;
import ca.mcgill.ecse.coolsupplies.controller.TOItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ItemBundlePageController {

    @FXML
    private ChoiceBox<TOItem> addBundleItemChoiceBox;

    @FXML
    private ChoiceBox<TOGradeBundle> addGradeBundleChoiceBox;

    @FXML
    private Button addItemButton;

    @FXML
    private TextField addItemQuantityTextField;

    @FXML
    private ChoiceBox<String> addItemTypeChoiceBox;

    @FXML
    private ChoiceBox<TOBundleItem> deleteBundleItemChoiceBox;

    @FXML
    private ChoiceBox<TOGradeBundle> deleteGradeBundleChoiceBox;

    @FXML
    private Button deleteItemButton;

    @FXML
    private ChoiceBox<TOBundleItem> updateBundleItemChoiceBox;

    @FXML
    private ChoiceBox<TOGradeBundle> updateGradeBundleChoiceBox;

    @FXML
    private Button updateItemClicked;

    @FXML
    private TextField updateItemQuantityTextField;

    @FXML
    private ChoiceBox<String> updateItemTypeChoiceBox;

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
  public void initialize(){
    addBundleItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      addBundleItemChoiceBox.setItems(ViewUtils.getItems());
      addBundleItemChoiceBox.setValue(null);
    } );

    updateBundleItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      updateBundleItemChoiceBox.setItems(ViewUtils.getBundleItems(null));
      updateBundleItemChoiceBox.setValue(null);
    } );

    deleteBundleItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      deleteBundleItemChoiceBox.setItems(ViewUtils.getBundleItems(null));
      deleteBundleItemChoiceBox.setValue(null);
    } );


    addGradeBundleChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      addGradeBundleChoiceBox.setItems(ViewUtils.getBundles());
      addGradeBundleChoiceBox.setValue(null);
    });

    updateGradeBundleChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      updateGradeBundleChoiceBox.setItems(ViewUtils.getBundles());
      updateGradeBundleChoiceBox.setValue(null);
    });

    deleteGradeBundleChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      deleteGradeBundleChoiceBox.setItems(ViewUtils.getBundles());
      deleteGradeBundleChoiceBox.setValue(null);
    });


    ObservableList<String> purchaseLevels = FXCollections.observableArrayList("Mandatory","Recommended","Optional");
    addItemTypeChoiceBox.setItems(purchaseLevels);
    addItemTypeChoiceBox.setValue(purchaseLevels.get(0));

    ObservableList<String> purchaseLevelss = FXCollections.observableArrayList("Mandatory","Recommended","Optional");
    updateItemTypeChoiceBox.setItems(purchaseLevelss);
    updateItemTypeChoiceBox.setValue(purchaseLevelss.get(0));

    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(addBundleItemChoiceBox, addGradeBundleChoiceBox, deleteGradeBundleChoiceBox, deleteBundleItemChoiceBox, updateBundleItemChoiceBox, updateGradeBundleChoiceBox);
   }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void addItemClicked(ActionEvent event) {
      try {
        TOGradeBundle gradeBundle = addGradeBundleChoiceBox.getValue();
        TOItem item = addBundleItemChoiceBox.getValue();
        int quantity = Integer.parseInt(addItemQuantityTextField.getText());
        String itemType = addItemTypeChoiceBox.getValue();
        var error = "";
        if (gradeBundle == null){
          error += "Invalid gradeBundle";
        }
        if (item == null){
          error += "Invalid item";
        }
        if (itemType == null){
          error += "Invalid item type";
        }
        if (!error.isEmpty()){
          ViewUtils.showError(error);
        }else{
          if (successful(CoolSuppliesFeatureSet5Controller.addBundleItem(quantity, itemType, item.getName(), gradeBundle.getName()))){
            addItemQuantityTextField.setText("");
          }
        }
      }catch(NumberFormatException e){
        ViewUtils.showError("Please input a valid quantity number");
      }
    }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void deleteItemClicked(ActionEvent event) {
      TOGradeBundle gradeBundle = deleteGradeBundleChoiceBox.getValue();
      TOBundleItem bundleItem = deleteBundleItemChoiceBox.getValue();
      if (gradeBundle == null) {
        ViewUtils.showError("Please select a valid grade bundle");
      }
      else if (bundleItem == null) {
        ViewUtils.showError("Please select a valid bundle item");
      } else {
        callController(CoolSuppliesFeatureSet5Controller.deleteBundleItem(bundleItem.getItemName(), gradeBundle.getName()));
      }

      }
    

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void updateItemClicked(ActionEvent event) {
      try {
        TOGradeBundle gradeBundle = updateGradeBundleChoiceBox.getValue();
        TOBundleItem item = updateBundleItemChoiceBox.getValue();
        int newQuantity = Integer.parseInt(addItemQuantityTextField.getText());
        String newItemType = addItemTypeChoiceBox.getValue();
        var error = "";
        if (gradeBundle == null){
          error += "Invalid grade";
        }
        if (item == null){
          error += "Invalid item";
        }
        if (newItemType == null){
          error += "Invalid item type";
        }
        if (!error.isEmpty()){
          ViewUtils.showError(error);
        }else{
          if (successful(CoolSuppliesFeatureSet5Controller.updateBundleItem(item.getItemName(), gradeBundle.getName(), newQuantity, newItemType))){
            addItemQuantityTextField.setText("");
          }
        }
      }catch(NumberFormatException e){
        ViewUtils.showError("Please input a valid quantity number");
      }
    }

}