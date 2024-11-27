package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.getStudents;
import ca.mcgill.ecse.coolsupplies.controller.TOStudent;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewStudentPageController {

    @FXML
    private TableView<TOStudent> studentInformations;

    @FXML
    private void initialize() {
      studentInformations.getColumns().add(createTableColumn("Name", "name"));
      studentInformations.getColumns().add(createTableColumn("Grade Level", "gradeLevel"));

      studentInformations.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> studentInformations.setItems(FXCollections.observableList(getStudents())));
    
        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(studentInformations);
    }

    public static TableColumn<TOStudent, String> createTableColumn(String header, String propertyName) {
      TableColumn<TOStudent, String> column = new TableColumn<>(header);
      column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
      return column;
    }

}
