package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.InventoryItem;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.Order.Status;
import ca.mcgill.ecse.coolsupplies.model.OrderItem;

public class CoolSuppliesFeatureSet9Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet9Controller() {}

  public static String updateOrderItem(String itemName, int newQuantity, int orderNumber) {

    Order order = Order.getWithNumber(orderNumber);
    if (order == null) {
      return "Order " + orderNumber + " does not exist";
    }

    InventoryItem inventoryItem = InventoryItem.getWithName(itemName);
    if (inventoryItem == null) {
      return "Item " + itemName + " does not exist.";
    }

    OrderItem orderItem = null;
    for (OrderItem oi : order.getOrderItems()) {
      if (oi.getItem().getName().equals(itemName)) {
        orderItem = oi;
        break;
      }
    }

    if (orderItem == null) {
      return "Item " + itemName + " does not exist in the order " + orderNumber + ".";
    }

    try {
      orderItem.setQuantity(newQuantity);
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return "";
  }

  public static String deleteOrderItem(String itemName, int orderNumber) {

    Order order = Order.getWithNumber(orderNumber);
    if (order == null) {
      return "Order " + orderNumber + " does not exist";
    }

    InventoryItem inventoryItem = InventoryItem.getWithName(itemName);
    if (inventoryItem == null) {
      return "Item " + itemName + " does not exist.";
    }

    OrderItem orderItem = null;
    for (OrderItem oi : order.getOrderItems()) {
      if (oi.getItem().getName().equals(itemName)) {
        orderItem = oi;
        break;
      }
    }

    if (orderItem == null) {
      return "Item " + itemName + " does not exist in the order " + orderNumber + ".";
    }


    try {
      orderItem.delete();
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return "";
  }
}