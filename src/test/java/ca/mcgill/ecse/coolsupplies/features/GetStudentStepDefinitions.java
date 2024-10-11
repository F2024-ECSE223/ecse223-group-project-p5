package ca.mcgill.ecse.coolsupplies.features;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;
import ca.mcgill.ecse.coolsupplies.model.Student;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GetStudentStepDefinitions {
  private CoolSupplies coolSupplies=CoolSuppliesApplication.getCoolSupplies();
  private TOStudent student;
  private List<TOStudent> students;

  /**
   * @author Brian Yang
   */
  @When("the school admin attempts to get from the system the student with name {string} \\(p5)")
  public void the_school_admin_attempts_to_get_from_the_system_the_student_with_name_p5(
      String string) {
    // Write code here that turns the phrase above into concrete actions
    student = CoolSuppliesFeatureSet2Controller.getStudent(string);
  }

  /**
   * @author Brian Yang
   */
  @When("the school admin attempts to get from the system all the students \\(p5)")
  public void the_school_admin_attempts_to_get_from_the_system_all_the_students_p5() {
    // Write code here that turns the phrase above into concrete actions
    CoolSuppliesFeatureSet2Controller.getStudents();
  }

  /**
   * @author Brian Yang
   */
  @Then("the following student entities shall be presented \\(p5)")
  public void the_following_student_entities_shall_be_presented_p5(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String name = row.get("name");
      String gradeLevel = row.get("gradeLevel");
      for (TOStudent student : students) {
        if (student.getName().equals(name)) {
          assertEquals(name, student.getName());
          assertEquals(gradeLevel, student.getGradeLevel());
        }
      }
    }

  }

  /**
   * @author Brian Yang
   */
  @Then("no student entities shall be presented \\(p5)")
  public void no_student_entities_shall_be_presented_p5() {
    // Write code here that turns the phrase above into concrete actions
    assertNull(student);
  }



}
