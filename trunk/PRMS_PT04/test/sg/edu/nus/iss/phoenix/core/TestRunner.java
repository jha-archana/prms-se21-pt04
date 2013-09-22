/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test Runner
 * @author Wang Jiqin <a0107596@nus.edu.sg>
 */
public class TestRunner {
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(AllTestSuite.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
}
