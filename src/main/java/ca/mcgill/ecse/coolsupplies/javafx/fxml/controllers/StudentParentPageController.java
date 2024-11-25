package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
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

public class StudentParentPageController {

    @FXML
    private ChoiceBox<TOParent> addSelectParentChoiceBox;

    @FXML
    private ChoiceBox<TOStudent> addSelectStudentChoiceBox;

    @FXML
    private Button addStudentParentButton;

    @FXML
    private ChoiceBox<TOParent> removeSelectParentChoiceBox;

    @FXML
    private ChoiceBox<TOStudent> removeSelectStudentChoiceBox;

    @FXML
    private Button removeStudentParentButton;

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
  public void initialize(){

    addSelectParentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      addSelectParentChoiceBox.setItems(ViewUtils.getParents());
      addSelectParentChoiceBox.setValue(null);
    } );

    addSelectStudentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      addSelectStudentChoiceBox.setItems(ViewUtils.getStudents());
      addSelectStudentChoiceBox.setValue(null);
    } );

    removeSelectParentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      removeSelectParentChoiceBox.setItems(ViewUtils.getParents());
      removeSelectParentChoiceBox.setValue(null);
    } );

    removeSelectStudentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      removeSelectStudentChoiceBox.setItems(ViewUtils.getStudents());
      removeSelectStudentChoiceBox.setValue(null);
    } );

    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(addSelectParentChoiceBox, addSelectStudentChoiceBox, removeSelectParentChoiceBox, removeSelectStudentChoiceBox);
   }

   /*
     * @author Suthiesan Subramaniam
     */
   @FXML
    void addStudentParentClicked(ActionEvent event) {
      TOParent parent = addSelectParentChoiceBox.getValue();
      TOStudent student = addSelectStudentChoiceBox.getValue();
      if (parent == null) {
        ViewUtils.showError("Please select a valid parent");
      }
      else if (student == null) {
        ViewUtils.showError("Please select a valid student");
      } else {
        callController(CoolSuppliesFeatureSet6Controller.addStudentToParent(student.getName(), parent.getEmail()));
      }
    }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void removeStudentParentClicked(ActionEvent event) {
      TOParent parent = removeSelectParentChoiceBox.getValue();
      TOStudent student = removeSelectStudentChoiceBox.getValue();
      if (parent == null) {
        ViewUtils.showError("Please select a valid parent");
      }
      else if (student == null) {
        ViewUtils.showError("Please select a valid student");
      } else {
        callController(CoolSuppliesFeatureSet6Controller.deleteStudentFromParent(student.getName(), parent.getEmail()));
      }
    }
}