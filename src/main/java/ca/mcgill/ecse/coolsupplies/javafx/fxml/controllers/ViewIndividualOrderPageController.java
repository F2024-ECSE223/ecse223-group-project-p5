package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import java.util.List;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOOrderItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * This class handles the View Controller for the viewing of individual orders.
 * @author Baptiste Didier
 */
public class ViewIndividualOrderPageController {

    @FXML
    private TableView<TOOrder> orderInformationsTable;

    @FXML
    private ChoiceBox<TOOrder> orderInput;

    @FXML
    private TableView<TOOrderItem> orderSummaryTable;

    @FXML
    private Button submitInput;

    /**
     * Show the detail of a selected order
     */
    @FXML
    void showOrder(ActionEvent event) {
        String orderNumber = orderInput.getText();
        TOOrder order = CoolSuppliesFeatureSet11Controller.viewOrder(orderNumber);

        orderInformationsTable.setItems(FXCollections.observableArrayList(order));
        List<TOOrderItem> orderItems = order.getOrderItems();

        if (orderItems == null || orderItems.isEmpty()) {
            orderSummaryTable.setItems(FXCollections.observableArrayList());
        }
        else {
            orderSummaryTable.setItems(FXCollections.observableArrayList(orderItems));
        }
    }

    /**
     * Initializes the user interface for viewing orders
     */
    @FXML
    private void initialize() {
        selectOrderChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
            selectOrderChoiceBox.setItems(ViewUtils.getOrders());
            selectOrderChoiceBox.setValue(null);
          });
        
        orderInformationsTable.getColumns().add(createOrderTableColumn("Parent", "parentEmail"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Student", "studentName"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Status", "status"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Number", "number"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Date", "date"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Purchase Level","level" ));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Authorization Code", "authorizationCode"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Penalty Authorization Code", "penaltyAuthorizationCode"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Total Price", "totalPrice"));

        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Name", "itemName"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Quantity", "quantity"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Price", "price"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Discount", "discount"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Bundle Name", "gradeBundleName"));
    }

    /**
     * Creates a table column in the order informations
     */
    public static TableColumn<TOOrder, String> createOrderTableColumn(String header, String propertyName) {
      TableColumn<TOOrder, String> column = new TableColumn<>(header);
      column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
      return column;
    }

    /**
     * Creates a table column in the order summary
     */
    public static TableColumn<TOOrderItem, String> createOrderItemTableColumn(String header, String propertyName) {
        TableColumn<TOOrder, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
      }

}
