package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import javax.print.attribute.standard.MediaSize.Other;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.InventoryItem;
import ca.mcgill.ecse.coolsupplies.model.Item;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.Student;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet8Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet8Controller() {}

  /**
   * Updates an order in CoolSupplies.
   * 
   * @param orderNumber the order number
   * @param purchaseLevel the purchase level of the order
   * @param studentName the name of the student
   * @return an error string if there is any
   * @author Jiaduo Xing
   */
  public static String updateOrder(String orderNumber, String purchaseLevel, String studentName) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (order == null){
      return "Order " + orderNumber + " does not exist";
    }
    Student student = Student.getWithName(studentName);
    if (student == null){
      return "Student" + studentName + "does not exist"; 
    }
    try {
      order.updateOrder(PurchaseLevel.valueOf(purchaseLevel), student);
      CoolsuppliesPersistence.save();
    } catch (Exception e) {
      return e.getMessage();
    }
    return "";
  }

  /**
   * Adds an item to an order
   * @param item the item name
   * @param quantity the quantity of the item
   * @param orderNumber the order number
   * @return an error message or "Order updated successfully."
   * @author Jiaduo Xing
   */
  public static String addOrderItem(String item, String quantity, String orderNumber) {
    InventoryItem anitem = InventoryItem.getWithName(item);
    if (anitem == null){
      return "Item " + item + " does not exist.";
    } 
    Order anOrder = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (anOrder == null){
      return "Order " + orderNumber + " does not exist";
    }
    try {
      anOrder.addItemToOrder(anitem, Integer.parseInt(quantity));
    } catch (Exception e) {
      if (e.getMessage().startsWith("Unable to create order item due to")){
        return "Item "+ item + " already exists in the order " + orderNumber + ".";
      }
      return e.getMessage();
    }
    return "";
  }
}
