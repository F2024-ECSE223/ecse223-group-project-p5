package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import java.util.List;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOOrderItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewIndividualOrderPageController {

    @FXML
    private TableView<TOOrder> orderInformationsTable;

    @FXML
    private TableView<TOOrderItem> orderSummaryTable;

    @FXML
    private Button submitInput;

    @FXML
    private TextField textInput;

    @FXML
    private void initialize() {
        orderInformationsTable.getColumns().add(createOrderTableColumn("Order Number", "orderNumber"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Date", "date"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Status", "status"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Level", "level"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Authorization Code", "authCode"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Penality Authorization Code", "penAuthCode"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Parent", "parent"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Student", "student"));

        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Name", "itemName"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Quantity", "quantity"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Price", "price"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Discount", "discount"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Bundle Name", "bundleName"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Total Price", "totalPrice"));
    
        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(orderInformationsTable);
        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(orderSummaryTable);
    }

    @FXML
    void showOrder(ActionEvent event) {
        String orderNumber = textInput.getText().trim();
        TOOrder order = CoolSuppliesFeatureSet11Controller.viewOrder(orderNumber);

        if (order == null) {
            ViewUtils.showError("Please input a valid order number.");
            return;
        }

        orderInformationsTable.setItems(FXCollections.observableArrayList(order));
        List<TOOrderItem> orderItems = order.getOrderItems();

        if (orderItems == null || orderItems.isEmpty()) {
            orderSummaryTable.setItems(FXCollections.observableArrayList());
        }
        else {
            orderSummaryTable.setItems(FXCollections.observableArrayList(orderItems));
        }
    }

    public static TableColumn<TOOrder, String> createOrderTableColumn(String header, String propertyName) {
    TableColumn<TOOrder, String> column = new TableColumn<>(header);
    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    return column;
  }

    public static TableColumn<TOOrderItem, String> createOrderItemTableColumn(String header, String propertyName) {
    TableColumn<TOOrderItem, String> column = new TableColumn<>(header);
    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    return column;
  }

}
