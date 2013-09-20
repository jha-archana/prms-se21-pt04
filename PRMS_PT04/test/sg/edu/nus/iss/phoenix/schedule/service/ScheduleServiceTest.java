/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.schedule.entity.PSSearchObject;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author tmswj
 */
public class ScheduleServiceTest {
    
    public ScheduleServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of searchProgramSlot method, of class ScheduleService.
     */
    @Test
    public void testSearchProgramSlot() {
        System.out.println("searchProgramSlot");
        PSSearchObject ps = null;
        ScheduleService instance = new ScheduleService();
        ArrayList expResult = null;
        ArrayList result = instance.searchProgramSlot(ps);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProgramSlotByDate method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotByDate() {
        System.out.println("findProgramSlotByDate");
        Date dateOfProgram = null;
        ScheduleService instance = new ScheduleService();
        List expResult = null;
        List result = instance.findProgramSlotByDate(dateOfProgram);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProgramSlotById method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotById() {
        System.out.println("findProgramSlotById");
        int id = 0;
        ScheduleService instance = new ScheduleService();
        ProgramSlot expResult = null;
        ProgramSlot result = instance.findProgramSlotById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllProgramSlot method, of class ScheduleService.
     */
    @Test
    public void testFindAllProgramSlot() {
        System.out.println("findAllProgramSlot");
        ScheduleService instance = new ScheduleService();
        ArrayList expResult = null;
        ArrayList result = instance.findAllProgramSlot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertProgramSlot method, of class ScheduleService.
     */
    @Test
    public void testInsertProgramSlot() {
        System.out.println("insertProgramSlot");
        ProgramSlot ps = null;
        ScheduleService instance = new ScheduleService();
        instance.insertProgramSlot(ps);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProgramSlot method, of class ScheduleService.
     */
    @Test
    public void testUpdateProgramSlot() {
        System.out.println("updateProgramSlot");
        ProgramSlot ps = null;
        ScheduleService instance = new ScheduleService();
        instance.updateProgramSlot(ps);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProgramSlot method, of class ScheduleService.
     */
    @Test
    public void testDeleteProgramSlot() {
        System.out.println("deleteProgramSlot");
        ProgramSlot ps = null;
        ScheduleService instance = new ScheduleService();
        instance.deleteProgramSlot(ps);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProgramSlotByUserId method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotByUserId() {
        System.out.println("findProgramSlotByUserId");
        String userId = "";
        ScheduleService instance = new ScheduleService();
        ArrayList expResult = null;
        ArrayList result = instance.findProgramSlotByUserId(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateValueOfString method, of class ScheduleService.
     */
    @Test
    public void testGetDateValueOfString() {
        System.out.println("getDateValueOfString");
        String time = "";
        ScheduleService instance = new ScheduleService();
        Date expResult = null;
        Date result = instance.getDateValueOfString(time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkConflict method, of class ScheduleService.
     */
    @Test
    public void testCheckConflict() {
        System.out.println("checkConflict");
        ProgramSlot psInput = null;
        String id = "";
        ScheduleService instance = new ScheduleService();
        boolean expResult = false;
        boolean result = instance.checkConflict(psInput, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addHourAndMinute method, of class ScheduleService.
     */
    @Test
    public void testAddHourAndMinute() {
        System.out.println("addHourAndMinute");
        Date date = null;
        Date DateToAdd = null;
        ScheduleService instance = new ScheduleService();
        Date expResult = null;
        Date result = instance.addHourAndMinute(date, DateToAdd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}