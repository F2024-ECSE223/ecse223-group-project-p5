package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
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
    if (orderNumber == null || orderNumber.isEmpty()) {
      return "Error: Order number is required.";
    }
    if (purchaseLevel == null || purchaseLevel.isEmpty()) {
      return "Error: Purchase level is required.";
    }
    if (studentName == null || studentName.isEmpty()) {
      return "Error: Student name is required.";
    }
    if (!purchaseLevel.equals("Mandatory") && !purchaseLevel.equals("Recommended")
        && !purchaseLevel.equals("Optional")) {
      return "Purchase level " + purchaseLevel + " does not exist.";
    }

    if (!Student.hasWithName(studentName)) {
      return "Student " + studentName + " does not exist.";
    }

    if (!Order.hasWithNumber(Integer.parseInt(orderNumber))) {
      return "Order " + orderNumber + " does not exist";
    }

    try {
      Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
      if (order.getStatus() != Order.Status.Started || order.getStatus() != Order.Status.Final) {
        return "Cannot update a " + order.getStatus() + " order";
      }
      if (order.getParent().getEmail()
          .equals(Student.getWithName(studentName).getParent().getEmail())) {
        return "Student " + studentName + " is not a child of the parent "
            + order.getParent().getEmail() + ".";
      }
      order.setLevel(PurchaseLevel.valueOf(purchaseLevel));
      order.setStudent(Student.getWithName(studentName));
      CoolsuppliesPersistence.save();
      return "Order updated successfully.";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  public static String addOrderItem(String item, String quantity, String orderNumber) {
    if (item == null || item.isEmpty()) {
      return "Error: Item name is required.";
    }
    if (quantity == null || quantity.isEmpty()) {
      return "Error: Quantity is required.";
    }
    if (orderNumber == null || orderNumber.isEmpty()) {
      return "Error: Order number is required.";
    }

    if (!Order.hasWithNumber(Integer.parseInt(orderNumber))) {
      return "Order " + orderNumber + " does not exist";
    }
    if (!Item.hasWithName(item)) {
      return "Item " + item + " does not exist.";
    }
    try {
      if (Integer.parseInt(quantity) <= 0) {
        return "Quantity must be greater than 0.";
      }
      Item itemObj = (Item) Item.getWithName(item);
      Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
      if (order.getStatus() != Order.Status.Started || order.getStatus() != Order.Status.Final) {
        return "Cannot add items to a " + order.getStatus() + " order";
      }
      if (order.getOrderItems().contains(itemObj)) {
        return "Item " + item + " already existsin the order " + orderNumber + ".";
      }
      order.addItemToOrder(itemObj, Integer.parseInt(quantity));
      CoolsuppliesPersistence.save();
      return "Order updated successfully.";
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
