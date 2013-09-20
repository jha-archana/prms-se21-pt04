package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.PSSearchObject;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author tmswj
 */
public class ScheduleDAOImplTest {
    
    private ScheduleDAOImpl sDao;
    
    @Mock
    private DataSource ds;
    @Mock
     private Connection conn;
    @Mock
    private ResultSet rs;
    @Mock
    private PreparedStatement stmt;
    
    public ScheduleDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         MockitoAnnotations.initMocks( this );
        sDao = new ScheduleDAOImpl(ds);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createValueObject method, of class ScheduleDAOImpl.
     */
    @Test
    public void testCreateValueObject() {
        System.out.println("createValueObject");
   
        ProgramSlot result = sDao.createValueObject();
        assertEquals(0, result.getId());
        assertNull(result.getDateOfProgram());
        assertNull(result.getStartTime());
        assertNull(result.getDuration());
        assertNull(result.getPresenter());
        assertNull(result.getProducer());
        assertNull(result.getRadioProgram());

    }

    /**
     * Test of getListByDate method, of class ScheduleDAOImpl.
     */
    @Test
    public void testGetListByDate() throws Exception {
        System.out.println("getListByDate");
        //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of findObject method, of class ScheduleDAOImpl.
     */
    @Test
    public void testFindObject() throws Exception {
        System.out.println("findObject");
        //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadById method, of class ScheduleDAOImpl.
     */
    @Test
    public void testLoadById() throws Exception {
        System.out.println("loadById");
       //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class ScheduleDAOImpl.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
       //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadAll method, of class ScheduleDAOImpl.
     */
    @Test
    public void testLoadAll() throws Exception {
        System.out.println("loadAll");
       //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ScheduleDAOImpl.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class ScheduleDAOImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
       //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ScheduleDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
       //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAll method, of class ScheduleDAOImpl.
     */
    @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
       //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of countAll method, of class ScheduleDAOImpl.
     */
    @Test
    public void testCountAll() throws Exception {
        System.out.println("countAll");
        //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMatching method, of class ScheduleDAOImpl.
     */
    @Test
    public void testSearchMatching() throws Exception {
        System.out.println("searchMatching");
      //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of databaseUpdate method, of class ScheduleDAOImpl.
     */
    @Test
    public void testDatabaseUpdate() throws Exception {
        System.out.println("databaseUpdate");
       //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of singleQuery method, of class ScheduleDAOImpl.
     */
    @Test
    public void testSingleQuery() throws Exception {
        System.out.println("singleQuery");
      //TODO
        fail("The test case is a prototype.");
    }

    /**
     * Test of listQuery method, of class ScheduleDAOImpl.
     */
    @Test
    public void testListQuery() throws Exception {
        System.out.println("listQuery");
      //TODO
        fail("The test case is a prototype.");
    }
}
