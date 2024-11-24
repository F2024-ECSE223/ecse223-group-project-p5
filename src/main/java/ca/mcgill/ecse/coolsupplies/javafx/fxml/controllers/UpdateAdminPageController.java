package main.java.ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet1Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UpdateAdminPageController {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    public void initialize() {
      newPasswordField.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
        newPasswordField.clear();
    });

    adminEmailField.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
        adminEmailField.setText("admin@cool.ca");
        adminEmailField.setDisable(true);
    });

    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(newPasswordField, adminEmailField);
    }

    @FXML
    void updateAdminPasswordClicked(ActionEvent event) {
      String newPassword = newPasswordField.getText();
      if (newPassword == null || newPassword.trim().isEmpty()){
        ViewUtils.showError("Please input a valid password.");
      }
      else {
        if (successful(CoolSuppliesFeatureSet1Controller.updateAdmin(newPassword))){
          updateAdminPasswordClicked.setText("");
          CoolSuppliesFxmlView.getInstance().refresh();
        }
      }
    }

}