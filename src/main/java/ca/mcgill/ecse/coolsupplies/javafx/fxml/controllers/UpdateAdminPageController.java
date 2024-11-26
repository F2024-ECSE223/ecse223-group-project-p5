package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;

import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class UpdateAdminPageController {

    @FXML
    private TextField adminEmailField;

    @FXML
    private TextField newPasswordField;

    // @FXML
    // public void initialize() {
    //   newPasswordField.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
    //     newPasswordField.clear();
    // });

    // adminEmailField.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
    //     adminEmailField.setText("admin@cool.ca");
    //     adminEmailField.setDisable(true);
    // });

    // CoolSuppliesFxmlView.getInstance().registerRefreshEvent(newPasswordField, adminEmailField);
    // }

    @FXML
    void updateAdminPasswordClicked(ActionEvent event) {
      String newPassword = newPasswordField.getText();
      if (newPassword == null || newPassword.trim().isEmpty()){
        ViewUtils.showError("Please input a valid password.");
      }
      else {
        if (successful(CoolSuppliesFeatureSet1Controller.updateAdmin(newPassword))) {
                newPasswordField.clear();
                CoolSuppliesFxmlView.getInstance().refresh();
      }
    }

}

}