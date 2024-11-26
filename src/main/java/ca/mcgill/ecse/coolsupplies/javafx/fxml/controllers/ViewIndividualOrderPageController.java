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

public class ViewIndividualOrderPageController {

    @FXML
    private TableColumn<TOOrder, String> authCodeColumn;

    @FXML
    private TableColumn<TOOrderItem, String> bundleNameColumn;

    @FXML
    private TableColumn<TOOrder, String> dateColumn;

    @FXML
    private TableColumn<TOOrderItem, String> itemDiscountColumn;

    @FXML
    private TableColumn<TOOrderItem, String> itemNameColumn;

    @FXML
    private TableColumn<TOOrderItem, String> itemPriceColumn;

    @FXML
    private TableColumn<TOOrderItem, String> itemQuantityColumn;

    @FXML
    private TableColumn<TOOrder, String> levelColumn;

    @FXML
    private TableView<TOOrder> orderInformationsTable;

    @FXML
    private TableColumn<TOOrder, String> orderNumberColumn;

    @FXML
    private TableView<TOOrderItem> orderSummaryTable;

    @FXML
    private TableColumn<TOOrder, String> parentColumn;

    @FXML
    private TableColumn<TOOrder, String> penAuthCodeColumn;

    @FXML
    private TableColumn<TOOrder, String> statusColumn;

    @FXML
    private TableColumn<TOOrder, String> studentColumn;

    @FXML
    private Button submitInput;

    @FXML
    private TextField textInput;

    @FXML
    private TableColumn<TOOrder, String> totalPriceColumn;

    @FXML
    private void initialize() {
    orderNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getNumber())));
    dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
    statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
    authCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthorizationCode()));
    penAuthCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPenaltyAuthorizationCode()));
    levelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLevel()));
    parentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParentEmail()));
    studentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
    totalPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalPrice())));

    itemNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItemName()));
    itemQuantityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity())));
    itemPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));
    itemDiscountColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDiscount())));
    bundleNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGradeBundleName()));
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

}
