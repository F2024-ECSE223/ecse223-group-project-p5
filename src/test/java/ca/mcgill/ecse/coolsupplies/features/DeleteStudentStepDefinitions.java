package ca.mcgill.ecse.coolsupplies.features;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
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

public class DeleteStudentStepDefinitions {

  private static CoolSupplies coolSupplies;
  private List<Grade> gradeList = new ArrayList<>();
  private List<Student> studentList = new ArrayList<>();
  @BeforeAll
  public static void initializeCoolSupplies(){
    // Before all tests, initialize coolSupplies
    coolSupplies = CoolSuppliesApplication.getCoolSupplies();
  }
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
    gradeList = new ArrayList<>(); // Reinitialize grades list every scenario
    List<String> gradeTable = dataTable.asList(String.class); // Get datatable as a list of Strings
    for (String gradeLevel: gradeTable){
      gradeList.add(new Grade(gradeLevel, coolSupplies));
    }

    //throw new io.cucumber.java.PendingException();
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
    studentList = new ArrayList<>(); // Reinitialize students list every scenario
    List<Map<String, String>> studentTable = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> student : studentTable){
      for (Grade grade : gradeList){
        if (grade.getLevel().equals(student.get("gradeLevel"))){
          studentList.add(new Student(student.get("name"), coolSupplies, grade));
          break;
        }
      }
    }
    //throw new io.cucumber.java.PendingException();
  }

  @When("the school admin attempts to delete from the system the student with name {string} \\(p5)")
  public void the_school_admin_attempts_to_delete_from_the_system_the_student_with_name_p5(
      String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of student entities in the system shall be {string} \\(p5)")
  public void the_number_of_student_entities_in_the_system_shall_be_p5(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
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
    throw new io.cucumber.java.PendingException();
  }

  @Then("the error {string} shall be raised \\(p5)")
  public void the_error_shall_be_raised_p5(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
