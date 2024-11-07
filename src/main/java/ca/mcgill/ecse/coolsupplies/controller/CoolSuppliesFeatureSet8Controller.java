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
 * Updates an existing order in the system with a new purchase level and a new student.
 * 
 * @param orderNumber the unique identifier of the order to be updated
 * @param purchaseLevel the new purchase level to be set for the order
 * @param studentName the name of the student to be associated with the order
 * @return a string message indicating the result of the update operation:
 *         - "Order {orderNumber} does not exist" if the order is not found
 *         - "Student {studentName} does not exist" if the student is not found
 *         - "Purchase level {purchaseLevel} does not exist" if the purchase level is invalid
 *         - an error message if an exception occurs
 *         - an empty string if the update is successful
 * @author Jiaduo Xing
 */
  public static String updateOrder(String orderNumber, String purchaseLevel, String studentName) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (order == null){
      return "Order " + orderNumber + " does not exist";
    }
    Student student = Student.getWithName(studentName);
    if (student == null){
      return "Student " + studentName + " does not exist."; 
    }
    if ((!purchaseLevel.equals("Mandatory")) && (!purchaseLevel.equals("Optional")) && (!purchaseLevel.equals("Recommended"))){
      return "Purchase level " +purchaseLevel +" does not exist.";
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
   * Adds an item to an order in CoolSupplies.
   * 
   * @param item the name of the item
   * @param quantity the quantity of the item
   * @param orderNumber the order number
   * @return an error string if there is any
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
      CoolsuppliesPersistence.save();
    } catch (Exception e) {
      if (e.getMessage().startsWith("Unable to create order item due to")){
        return "Item "+ item + " already exists in the order " + orderNumber + ".";
      }
      return e.getMessage();
    }
    return "";
  }
}
