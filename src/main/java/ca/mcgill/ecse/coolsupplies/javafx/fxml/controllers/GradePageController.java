package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet7Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOGrade;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

public class GradePageController {
  @FXML
  private TextField addGradeNameTextField;
  @FXML
  private TextField updateGradeNameTextField;
  @FXML
  private ChoiceBox<TOGrade> updateGradeChoiceBox;
  @FXML
  private ChoiceBox<TOGrade> deleteGradeChoiceBox;
  @FXML
  private Button addGradeButton;
  @FXML
  private Button updateGradeButton;
  @FXML
  private Button deleteGradeButton;

  // Event Listener on Button[#addDriverButton].onAction
  @FXML
  public void addGradeClicked(ActionEvent event) {
    // omitted
  }
//Event Listener on Button[#addRouteButton].onAction
 @FXML
 public void updateGradeClicked(ActionEvent event) {
   // omitted
 }

 // Event Listener on Button[#addBusButton].onAction
 @FXML
 public void deleteGradeClicked(ActionEvent event) {
   // omitted
 }
}