package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import java.util.List;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
// import ca.mcgill.ecse.btms.controller.BtmsController;
// import ca.mcgill.ecse.btms.controller.TODriver;
// import ca.mcgill.ecse.btms.controller.TORouteAssignment;
// import ca.mcgill.ecse.btms.javafx.fxml.BtmsFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtils {

  /** Calls the controller and shows an error, if applicable. */
  public static boolean callController(String result) {
    if (result.isEmpty()) {
      CoolSuppliesFxmlView.getInstance().refresh();
      return true;
    }
    showError(result);
    return false;
  }

  /** Calls the controller and returns true on success. This method is included for readability. */
  public static boolean successful(String controllerResult) {
    return callController(controllerResult);
  }

  /**
   * Creates a popup window.
   *
   * @param title: title of the popup window
   * @param message: message to display
   */
  public static void makePopupWindow(String title, String message) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogPane = new VBox();

    // create UI elements
    Text text = new Text(message);
    Button okButton = new Button("OK");
    okButton.setOnAction(a -> dialog.close());

    // display the popup window
    int innerPadding = 10; // inner padding/spacing
    int outerPadding = 100; // outer padding
    dialogPane.setSpacing(innerPadding);
    dialogPane.setAlignment(Pos.CENTER);
    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
    dialogPane.getChildren().addAll(text, okButton);
    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
    dialog.setScene(dialogScene);
    dialog.setTitle(title);
    dialog.show();
  }

  public static void showError(String message) {
    makePopupWindow("Error", message);
  }

  // public static ObservableList<TODriver> getDrivers() {
  //   List<TODriver> drivers = BtmsController.getDrivers();
  //   // as javafx works with observable list, we need to convert the java.util.List to
  //   // javafx.collections.observableList
  //   return FXCollections.observableList(drivers);
  // }

  // public static ObservableList<String> getBuses() {
  //   return FXCollections.observableList(BtmsController.getBuses());
  // }

  // public static ObservableList<Integer> getRoutes() {
  //   return FXCollections.observableList(BtmsController.getRoutes());
  // }

  // public static ObservableList<TORouteAssignment> getAssignments() {
  //   return FXCollections.observableList(BtmsController.getAssignments());
  // }
}