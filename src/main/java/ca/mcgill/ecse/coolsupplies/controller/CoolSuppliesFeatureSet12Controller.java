package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.Order.Status;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet12Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet12Controller() {}
  
  /**
   * This method starts the school year for the specified order
   * 
   * @param orderNumber the exact order to have its school year started
   * @return an error message if the operation failed
   * 				 Returns an empty string if it was successfully added
   * @since 1.0
   */
  public static String startSchoolYearForOrder(String orderNumber) {

	  //Check if the order number exists
	  int orderNumberInt = Integer.parseInt(orderNumber);
	  Order order = Order.getWithNumber(orderNumberInt);
	  if (order == null) {
		  return String.format("The order %d does not exist.", orderNumberInt);
	  }
	  
	  try {
		  
		  Status orderStatus = order.getStatus();
		  
		  //Check if current State permits the startSchoolYear method
		  if ((orderStatus != Status.Paid) && (orderStatus != Status.Started)) {
			  return "The school year has already been started";
		  }
		  
		  //Start the school year
		  order.startSchoolYear();
		  CoolsuppliesPersistence.save();
		   
	  } catch (RuntimeException e) {
			return e.getMessage(); //Error during run time
		}
	  return "";
  }
  
  /**
   * This method picks up the specified order
   * 
   * @param orderNumber the exact order to be picked up by a student
   * @return an error message if the operation failed
   * 				 Returns an empty string if it was successfully added
   * @since 1.0
   */
  public static String pickUpOrder(String orderNumber) {
	  
	//Check if the order number exists
	  int orderNumberInt = Integer.parseInt(orderNumber);
	  Order order = Order.getWithNumber(orderNumberInt);
	  if (order == null) {
		  return String.format("The order %d does not exist.", orderNumberInt);
	  }
	  
	  try {
		  
		  Status orderStatus = order.getStatus();
		  
		  //Check if current State permits the pickUpOrder method
		  if (orderStatus == Status.PickedUp) {
			  return "The order is already picked up";
		  }
		  else if (orderStatus == Status.Started) {
			  return "Cannot pickup a started order";
		  }
		  else if (orderStatus == Status.Paid) {
			  return "Cannot pickup a paid order";
		  }
		  else if (orderStatus == Status.Penalized) {
			  return "Cannot pickup a penalized order";
		  }
		  
		  //Student picks up order
		  order.pickUpOrder();
		  CoolsuppliesPersistence.save();
		  
	  } catch (RuntimeException e) {
			return e.getMessage(); //Error during run time
		}
	  return "";
  }
  

}