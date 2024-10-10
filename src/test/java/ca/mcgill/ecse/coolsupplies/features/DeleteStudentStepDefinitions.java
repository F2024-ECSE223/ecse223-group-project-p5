package ca.mcgill.ecse.coolsupplies.features;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;
import ca.mcgill.ecse.coolsupplies.model.Student;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteStudentStepDefinitions {

  private CoolSupplies coolSupplies;
  private String error;
  private int errorCntr;
  // private List<Grade> gradeList = new ArrayList<>();
  // private List<Student> studentList = new ArrayList<>();
  // @BeforeAll
  // public static void initializeCoolSupplies(){
  // // Before all tests, initialize coolSupplies
  // coolSupplies = CoolSuppliesApplication.getCoolSupplies();
  // }


  @Given("the following grade entities exists in the system \\(p5)")
  public void the_following_grade_entities_exists_in_the_system_p5(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> rows = dataTable.asMaps(); // Get datatable as a list of Strings
    for (var row : rows) {
      String gradeLevel = row.get("level");
      coolSupplies.addGrade(gradeLevel);
    }

    // throw new io.cucumber.java.PendingException();
  }


  @Given("the following student entities exists in the system \\(p5)")
  public void the_following_student_entities_exists_in_the_system_p5(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> studentTable = dataTable.asMaps();
    for (var student : studentTable) {
      String name = student.get("name");
      String gradeLevel = student.get("gradeLevel");
      for (Grade grade : coolSupplies.getGrades()) {
        if (grade.getLevel().equals(gradeLevel)) {
          coolSupplies.addStudent(name, grade);
          break;
        }
      }
    }
  }


  @When("the school admin attempts to delete from the system the student with name {string} \\(p5)")
  public void the_school_admin_attempts_to_delete_from_the_system_the_student_with_name_p5(
      String string) {
    // Write code here that turns the phrase above into concrete actions
    callController(CoolSuppliesFeatureSet2Controller.deleteStudent(string));
  }


  @Then("the number of student entities in the system shall be {string} \\(p5)")
  public void the_number_of_student_entities_in_the_system_shall_be_p5(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(string, "" + coolSupplies.getStudents().size());
  }

  @Then("the following student entities shall exist in the system \\(p5)")
  public void the_following_student_entities_shall_exist_in_the_system_p5(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String,String>> rows=dataTable.asMaps();
    for (var row :rows) {
       String name=row.get("name");
       String gradeLevel=row.get("gradeLevel");
       for (Student student : coolSupplies.getStudents()) {
         assertEquals(name, student.getName());
         assertEquals(gradeLevel,student.getGrade().getLevel());
       }
    }
  }

  @Then("the error {string} shall be raised \\(p5)")
  public void the_error_shall_be_raised_p5(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertTrue(error.contains(string));
  }

  /** Calls controller and sets error and error counter **/
  private void callController(String result) {
    if (!result.isEmpty()) {
      error += result;
      errorCntr += 1;
    }
  }
}
