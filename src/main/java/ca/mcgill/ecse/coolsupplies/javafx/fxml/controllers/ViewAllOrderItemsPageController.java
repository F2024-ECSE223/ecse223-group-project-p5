package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet13Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOOrderItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewAllOrderItemsPageController {

  @FXML
  private TableView<TOOrderItem> orderItemsTable;

  /*
   * @author Brian Yang
   */

  @FXML
  public void initialize(){
    orderItemsTable.getColumns().add(createTableColumn("Quantity", "quantity"));
    orderItemsTable.getColumns().add(createTableColumn("Item", "itemName"));
    orderItemsTable.getColumns().add(createTableColumn("Grade Bundle", "gradeBundleName"));
    orderItemsTable.getColumns().add(createTableColumn("Price", "price"));
    orderItemsTable.getColumns().add(createTableColumn("Discount", "discount"));
    //
    orderItemsTable.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> orderItemsTable.setItems(FXCollections.observableList(CoolSuppliesFeatureSet13Controller.viewItemsAllOrderItems())));
    //
    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(orderItemsTable);
  }

  /* 
  * @author Brian Yang 
  */
  public static TableColumn<TOOrderItem, String> createTableColumn(String header,
  String propertyName) 
  {
    TableColumn<TOOrderItem, String> column = new TableColumn<>(header);
    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    return column;
  }



}
