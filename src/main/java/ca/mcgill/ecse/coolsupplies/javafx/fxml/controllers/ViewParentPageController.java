package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.getParents;
import ca.mcgill.ecse.coolsupplies.controller.TOParent;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewParentPageController {

    @FXML
    private TableView<TOParent> parentInformations;

    @FXML
    private void initialize() {
        parentInformations.getColumns().add(createTableColumn("Email", "email"));
        parentInformations.getColumns().add(createTableColumn("Password", "password"));
        parentInformations.getColumns().add(createTableColumn("Name", "name"));
        parentInformations.getColumns().add(createTableColumn("Phone Number", "phoneNumber"));

        parentInformations.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> parentInformations.setItems(FXCollections.observableList(getParents())));
    
        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(parentInformations);
    }

    public static TableColumn<TOParent, String> createTableColumn(String header, String propertyName) {
      TableColumn<TOParent, String> column = new TableColumn<>(header);
      column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
      return column;
    }
}
