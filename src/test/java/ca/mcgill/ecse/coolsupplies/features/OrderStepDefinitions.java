package ca.mcgill.ecse.coolsupplies.features;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.model.*;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import io.cucumber.datatable.internal.difflib.myers.Equalizer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStepDefinitions {
  static String error = "";
  private List<TOOrder> toOrdersList = new ArrayList<>();
  private CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();
  @Given("the following parent entities exist in the system")
  public void the_following_parent_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following grade entities exist in the system")
  public void the_following_grade_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following student entities exist in the system")
  public void the_following_student_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following student entities exist for a parent in the system")
  public void the_following_student_entities_exist_for_a_parent_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following item entities exist in the system")
  public void the_following_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following grade bundle entities exist in the system")
  public void the_following_grade_bundle_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  /*
   * @author Sanad Abu Baker
   */
  @Given("the following bundle item entities exist in the system")
  public void the_following_bundle_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        for (Map<String, String> bundleItems : rows) {
        List<GradeBundle> gradeBundleList = coolSupplies.getBundles();
        List<Item> items = coolSupplies.getItems();

        GradeBundle bundle = null;
        for(GradeBundle gb : gradeBundleList){
          if(gb.getName().equals(bundleItems.get("gradeBundleName"))){
            bundle = gb;
            break;
          }
        }

        Item item = null;
        for(Item i : items){
          if(i.getName().equals(bundleItems.get("itemName"))){
            item = i;
            break; 
          }
        }

          coolSupplies.addBundleItem(Integer.parseInt(bundleItems.get("quantity")), 
          BundleItem.PurchaseLevel.valueOf(bundleItems.get("level")),
          bundle, item);
        }
  }
  /**
   * @author Doddy Yang Qiu
   */
  @Given("the following order entities exist in the system")
  public void the_following_order_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> order : rows)
    {
      
      new Order(Integer.parseInt(order.get("number")),
              Date.valueOf(order.get("date")),
              BundleItem.PurchaseLevel.valueOf(order.get("level")),
              (Parent) Parent.getWithEmail(order.get("parentEmail")),
              Student.getWithName(order.get("studentName")),
              coolSupplies);
    }
  }

  @Given("the following order item entities exist in the system")
  public void the_following_order_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  /*
   * @author Sanad Abu Baker
   */
  @Given("the order {string} is marked as {string}")
  public void the_order_is_marked_as(String orderNumber, String statusName) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (statusName == "Paid"){
      callController(CoolSuppliesFeatureSet10Controller.payOrder(orderNumber, "45"));
    }
    else if (statusName == "Penalized"){
      callController(CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber));
    }
    else if (statusName == "Prepared"){
      callController(CoolSuppliesFeatureSet10Controller.payOrder(orderNumber, "45"));
      callController(CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber));
    }
    if (statusName == "PickedUp"){
      callController(CoolSuppliesFeatureSet10Controller.payOrder(orderNumber, "45"));
      callController(CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber));
      callController(CoolSuppliesFeatureSet12Controller.pickUpOrder(orderNumber));
    }
  }

  /**
   * @author Doddy Yang Qiu
   */
  @When("the parent attempts to update an order with number {string} to purchase level {string} and student with name {string}")
  public void the_parent_attempts_to_update_an_order_with_number_to_purchase_level_and_student_with_name(
      String orderNumber, String purchaseLevel, String studentName) {
    error = CoolSuppliesFeatureSet8Controller.updateOrder(orderNumber, purchaseLevel, studentName);
  }

  @When("the parent attempts to add an item {string} with quantity {string} to the order {string}")
  public void the_parent_attempts_to_add_an_item_with_quantity_to_the_order(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the parent attempts to update an item {string} with quantity {string} in the order {string}")
  public void the_parent_attempts_to_update_an_item_with_quantity_in_the_order(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the parent attempts to delete an item {string} from the order {string}")
  public void the_parent_attempts_to_delete_an_item_from_the_order(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Doddy Yang Qiu
   */
  @When("the parent attempts to get from the system the order with number {string}")
  public void the_parent_attempts_to_get_from_the_system_the_order_with_number(String orderNumber) {
    TOOrder toOrder = CoolSuppliesFeatureSet11Controller.viewOrder(orderNumber);
    if(toOrder != null) toOrdersList.add(toOrder);
  }



  @When("the parent attempts to cancel the order {string}")
  public void the_parent_attempts_to_cancel_the_order(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the parent attempts to pay for the order {string} with authorization code {string}")
  public void the_parent_attempts_to_pay_for_the_order_with_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the admin attempts to start a school year for the order {string}")
  public void the_admin_attempts_to_start_a_school_year_for_the_order(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the parent attempts to pay penalty for the order {string} with penalty authorization code {string} and authorization code {string}")
  public void the_parent_attempts_to_pay_penalty_for_the_order_with_penalty_authorization_code_and_authorization_code(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /*
   * @author Sanad Abu Baker
   */
  @When("the student attempts to pickup the order {string}")
  public void the_student_attempts_to_pickup_the_order(String orderNumber) {
    callController(CoolSuppliesFeatureSet12Controller.pickUpOrder(orderNumber));
  }

  /*
   * @author Sanad Abu Baker
   */
  @When("the school admin attempts to get from the system all orders")
  public void the_school_admin_attempts_to_get_from_the_system_all_orders() {
    callController(CoolSuppliesFeatureSet11Controller.viewAllOrders());
  }

  @Then("the order {string} shall contain penalty authorization code {string}")
  public void the_order_shall_contain_penalty_authorization_code(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall not contain penalty authorization code {string}")
  public void the_order_shall_not_contain_penalty_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  @Then("the order {string} shall not contain authorization code {string}")
  public void the_order_shall_not_contain_authorization_code(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    // throw new io.cucumbser.java.PendingException();
  }

  /*
   * @author Sanad Abu Baker
   */
  @Then("the order {string} shall not exist in the system")
  public void the_order_shall_not_exist_in_the_system(String orderNumber) {
    assertNull(Order.getWithNumber(Integer.parseInt(orderNumber)));
  }

  @Then("the order {string} shall contain authorization code {string}")
  public void the_order_shall_contain_authorization_code(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall contain {string} item")
  public void the_order_shall_contain_item(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall not contain {string}")
  public void the_order_shall_not_contain(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }



  @Then("the number of order items in the system shall be {string}")
  public void the_number_of_order_items_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall contain {string} items")
  public void the_order_shall_contain_items(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall not contain {string} with quantity {string}")
  public void the_order_shall_not_contain_with_quantity(String string, String string2,
      String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  /*
   * @author Sanad Abu Baker
   */
  @Then("the order {string} shall contain {string} with quantity {string}")
  public void the_order_shall_contain_with_quantity(String orderNumber, String itemName, String quantity) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    assertNotNull(order, "Could not find order " + orderNumber + " using Order.getWithNumber");

    Item item = (Item) InventoryItem.getWithName(itemName);
    for (OrderItem orderItem : order.getOrderItems()){
      if(orderItem.getItem().equals(item)){
        assertEquals(orderItem.getQuantity(), Integer.parseInt(quantity));
      }
    }
  }


  @Then("the order {string} shall be marked as {string}")
  public void the_order_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  @Then("the number of orders in the system shall be {string}")
  public void the_number_of_orders_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Then("the order {string} shall contain level {string} and student {string}")
  public void the_order_shall_contain_level_and_student(String orderNumber, String level,
      String student) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    assertNotNull(order, "Could not find order " + orderNumber + " using Order.getWithNumber");
    assertEquals(order.getLevel().name(), level,
            "Expected Purchase Level: " + level + "\nActual Purchase Level: " + order.getLevel().name());
    assertEquals(order.getStudent().getName(), student,
            "Expected Student: " + student + "\nActual Student: " + order.getStudent().getName());
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Given("order {string} is marked as {string}")
  public void order_is_marked_as(String orderNumber, String level) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    if(order != null) order.setLevel(BundleItem.PurchaseLevel.valueOf(level));
  }

  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Then("the following order entities shall be presented")
  public void the_following_order_entities_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    assertEquals(rows.size(), toOrdersList.size(),
            "Expected toOrdersList.size(): " + (rows.size()) + "\nActual size: " + toOrdersList.size());
    for (Map<String, String> toOrder : rows)
    {
      TOOrder myTOOrder = null;
      for(TOOrder order : toOrdersList) {
        if(Objects.equals(toOrder.get("parentEmail"), order.getParentEmail()) &&
                Objects.equals(toOrder.get("studentName"), order.getStudentName()) &&
                Objects.equals(toOrder.get("status"), order.getStatus()) &&
                Objects.equals(toOrder.get("number"), order.getNumber()) &&
                Objects.equals(Date.valueOf(toOrder.get("date")), order.getDate()) &&
                Objects.equals(toOrder.get("level"), order.getLevel()) &&
                Objects.equals(toOrder.get("authorizationCode"), order.getAuthorizationCode()) &&
                Objects.equals(toOrder.get("penaltyAuthorizationCode"), order.getPenaltyAuthorizationCode()) &&
                Objects.equals(Double.parseDouble(toOrder.get("totalPrice")), order.getTotalPrice()))
        {
          myTOOrder = order;
        }
      }
      assertNotNull(myTOOrder,
              "Could not find TOOrder in toOrdersList with" +
                      "\nparentEmail: " + toOrder.get("parentEmail") +
                      "\nstudentName: " + toOrder.get("studentName") +
                      "\nstatus: " + toOrder.get("status") +
                      "\nnumber: " + toOrder.get("number") +
                      "\ndate: " + toOrder.get("date") +
                      "\nlevel: " + toOrder.get("level") +
                      "\nauthorizationCode: " + toOrder.get("authorizationCode") +
                      "\npenaltyAuthorizationCode: " + toOrder.get("penaltyAuthorizationCode") +
                      "\ntotalPrice: " + toOrder.get("totalPrice"));
    }

  }

  /**
   * @author Doddy Yang Qiu
   */
  @Then("the following order items shall be presented for the order with number {string}")
  public void the_following_order_items_shall_be_presented_for_the_order_with_number(String orderNumber,
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    // Find the specified TOOrder
    TOOrder myOrder = null;
    for(TOOrder order : toOrdersList)if(order.getNumber().equals(orderNumber)) myOrder = order;
    assertNotNull(myOrder, "Could not find order with number " + orderNumber + " in toOrdersList");

    // Compare the number of OrderItems
    assertEquals(rows.size(), myOrder.numberOfOrderItems(),
            "Expected number of order Items: " + (rows.size()) + "\nActual number: " + myOrder.numberOfOrderItems());
    for (Map<String, String> orderItem : rows)
    {
      TOOrderItem myItem = null;
      for (TOOrderItem item : myOrder.getOrderItems())
      {
        if(item.getQuantity() == Integer.parseInt(orderItem.get("quantity")) &&
                item.getItemName().equals(orderItem.get("itemName")) &&
                item.getGradeBundleName().equals(orderItem.get("gradeBundleName")) &&
                item.getPrice() == Integer.parseInt(orderItem.get("price")) &&
                ((orderItem.get("discount").isEmpty() && item.getDiscount() == 0) || item.getDiscount() == Double.parseDouble(orderItem.get("discount"))))
        {
          myItem = item;
        }
      }
      assertNotNull(myItem, "Could not find TOOrderItem with" +
              "\nquantity: " + orderItem.get("quantity") +
              "\nitemName: " + orderItem.get("itemName") +
              "\ngradeBundleName: " + orderItem.get("gradeBundleName") +
              "\nprice: " + orderItem.get("price") +
              "\ndiscount: " + orderItem.get("discount"));
    }
  }

  /*
   * @author  Sanad Abu baker
   */
  @Then("no order entities shall be presented")
  public void no_order_entities_shall_be_presented() {
    assertTrue(toOrdersList.isEmpty(), "Expected no order entities, but the list is not empty)");
  }

}
