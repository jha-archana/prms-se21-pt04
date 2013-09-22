package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
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
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * Unit Test Class for ScheduleDAOImpl
 * @author Wang Jiqin <a0107596@nus.edu.sg>
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
        String sql = "SELECT * FROM APP.\"program-slot\" WHERE (\"dateOfProgram\" = ? ) ";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date dateOfProgram = cal.getTime();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR, 1);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND,0);
        Date startTime = time.getTime();
        Date duration = time.getTime();
        ProgramSlot  p = new ProgramSlot();
        p.setId(1);
        p.setDateOfProgram(dateOfProgram);
        p.setDuration(duration);
        p.setStartTime(startTime);
        p.setPresenter(new Presenter("pre1"));
        p.setProducer(new Producer("prod1"));
        p.setRadioProgram(new RadioProgram("testProgram"));
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("duration")).thenReturn("01:00:00");
        when(rs.getString("startTime")).thenReturn("01:00:00");
        when(rs.getString("dateOfProgram")).thenReturn("2012-01-01");
        when(rs.getString("program-name")).thenReturn("testProgram");
        when(rs.getString("presenter-id")).thenReturn("pre1");
        when(rs.getString("producer-id")).thenReturn("prod1");
        
        List<ProgramSlot> schedules = sDao.getListByDate(dateOfProgram);
        assertEquals(1, schedules.size());
        assertEquals("testProgram", schedules.get(0).getRadioProgram().getName());
        
    }

    /**
     * Test of findObject method, of class ScheduleDAOImpl.
     */
    @Test
    public void testFindObject() throws Exception {
        System.out.println("findObject");
        String sql = "SELECT * FROM APP.\"program-slot\" WHERE (\"id\" = ? ) ";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date dateOfProgram = cal.getTime();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR, 1);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND,0);
        Date startTime = time.getTime();
        Date duration = time.getTime();
        ProgramSlot  p = new ProgramSlot();
        p.setId(1);
        p.setDateOfProgram(dateOfProgram);
        p.setDuration(duration);
        p.setStartTime(startTime);
        p.setPresenter(new Presenter("pre1"));
        p.setProducer(new Producer("prod1"));
        p.setRadioProgram(new RadioProgram("testProgram"));
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("duration")).thenReturn("01:00:00");
        when(rs.getString("startTime")).thenReturn("01:00:00");
        when(rs.getString("dateOfProgram")).thenReturn("2012-01-01");
        when(rs.getString("program-name")).thenReturn("testProgram");
        when(rs.getString("presenter-id")).thenReturn("pre1");
        when(rs.getString("producer-id")).thenReturn("prod1");
        
        ProgramSlot schedule = sDao.findObject(1);
        assertNotNull(schedule);
        assertEquals("pre1", schedule.getPresenter().getId());
        
    }

    /**
     * Test of loadById method, of class ScheduleDAOImpl.
     */
    @Test
    public void testLoadById() throws Exception {
        System.out.println("loadById");
        String sql = "SELECT * FROM APP.\"program-slot\" WHERE (\"id\" = ? ) ";
        
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("duration")).thenReturn("01:00:00");
        when(rs.getString("startTime")).thenReturn("01:00:00");
        when(rs.getString("dateOfProgram")).thenReturn("2012-01-01");
        when(rs.getString("program-name")).thenReturn("testProgram");
        when(rs.getString("presenter-id")).thenReturn("pre1");
        when(rs.getString("producer-id")).thenReturn("prod1");
        ProgramSlot valueObject = new ProgramSlot();
        valueObject.setId(1);
        
         sDao.loadById(valueObject);
        assertNotNull(valueObject);
        assertEquals("pre1", valueObject.getPresenter().getId());
        assertEquals("prod1", valueObject.getProducer().getId());
        
        
    }

    /**
     * Test of loadAll method, of class ScheduleDAOImpl.
     */
    @Test
    public void testLoadAll() throws Exception {
        System.out.println("loadAll");
       String sql = "SELECT * FROM APP.\"program-slot\" ORDER BY \"dateOfProgram\" DESC ";
       when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("duration")).thenReturn("01:00:00");
        when(rs.getString("startTime")).thenReturn("01:00:00");
        when(rs.getString("dateOfProgram")).thenReturn("2012-01-01");
        when(rs.getString("program-name")).thenReturn("testProgram");
        when(rs.getString("presenter-id")).thenReturn("pre1");
        when(rs.getString("producer-id")).thenReturn("prod1");
        
        List<ProgramSlot> schedules = sDao.loadAll();
        assertEquals(1, schedules.size());
        assertEquals("testProgram", schedules.get(0).getRadioProgram().getName());
        
    }

    /**
     * Test of create method, of class ScheduleDAOImpl.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        String sql = "INSERT INTO APP.\"program-slot\" (\"duration\", \"dateOfProgram\",\"startTime\", \"program-name\", \"presenter-id\", \"producer-id\") VALUES (?,?,?,?,?,?) ";
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);  
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date dateOfProgram = cal.getTime();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR, 1);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND,0);
        Date startTime = time.getTime();
        Date duration = time.getTime();
        ProgramSlot  p = new ProgramSlot();
        p.setDateOfProgram(dateOfProgram);
        p.setDuration(duration);
        p.setStartTime(startTime);
        p.setPresenter(new Presenter("pre1"));
        p.setProducer(new Producer("prod1"));
        p.setRadioProgram(new RadioProgram("testProgram"));
        
        sDao.create(p);
        
    }

    /**
     * Test of save method, of class ScheduleDAOImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
       String sql = "UPDATE APP.\"program-slot\" SET \"duration\" = ?, \"dateOfProgram\" = ?, \"startTime\" = ?, \"program-name\" = ?, \"presenter-id\" = ?, \"producer-id\" = ? WHERE (\"id\" = ? ) ";
       when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);  
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date dateOfProgram = cal.getTime();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR, 1);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND,0);
        Date startTime = time.getTime();
        Date duration = time.getTime();
        ProgramSlot  p = new ProgramSlot();
        p.setId(1);
        p.setDateOfProgram(dateOfProgram);
        p.setDuration(duration);
        p.setStartTime(startTime);
        p.setPresenter(new Presenter("pre1"));
        p.setProducer(new Producer("prod1"));
        p.setRadioProgram(new RadioProgram("testProgram"));
        
        sDao.save(p);
        
    }

    /**
     * Test of delete method, of class ScheduleDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
       String sql = "delete FROM APP.\"program-slot\" WHERE (\"id\" = ? ) ";
       when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);  
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH,1);
        Date dateOfProgram = cal.getTime();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR, 1);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND,0);
        Date startTime = time.getTime();
        Date duration = time.getTime();
        ProgramSlot  p = new ProgramSlot();
        p.setId(1);
        p.setDateOfProgram(dateOfProgram);
        p.setDuration(duration);
        p.setStartTime(startTime);
        p.setPresenter(new Presenter("pre1"));
        p.setProducer(new Producer("prod1"));
        p.setRadioProgram(new RadioProgram("testProgram"));
        sDao.delete(p);
        
        
    }

}
