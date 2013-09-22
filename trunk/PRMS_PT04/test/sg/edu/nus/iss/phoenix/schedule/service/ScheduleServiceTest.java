/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
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
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterService;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.producer.service.ProducerService;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.PSSearchObject;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * Unit Test class for ScheduleService
 * @author Wang Jiqin <a0107596@nus.edu.sg>
 */
public class ScheduleServiceTest {

    private ScheduleService sService;
    
    @Mock
    private DataSource ds;
    @Mock
    private DAOFactory factory;
    @Mock
    private ScheduleDAO sDao;
    @Mock
    private ProducerService prodService;
    @Mock
    private PresenterService preService;
    @Mock
    private RadioProgramDAO rpDao;
    

 
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
        MockitoAnnotations.initMocks( this );
         factory.setDataSource(ds);
         sService = new ScheduleService(factory);
         sService.setPresenterService(preService);
         sService.setProducerService(prodService);
         sService.setRpDao(rpDao);
         sService.setSchdao(sDao);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of searchProgramSlot method, of class ScheduleService.
     */
    @Test
    public void testSearchProgramSlot() throws SQLException {
        System.out.println("searchProgramSlot");
        ArrayList<ProgramSlot> schedList = new ArrayList<>();
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
        schedList.add(p);
        
        PSSearchObject obj = new PSSearchObject();
        obj.setDateOfProgram("2012-01-01");
        obj.setStartTime("01:00:00");
        obj.setRadioProgramName("");
                
        when(sDao.searchMatching(obj)).thenReturn(schedList);
        
        ArrayList<ProgramSlot> result = sService.searchProgramSlot(obj);
        assertEquals(1, result.size());
        
    }

    /**
     * Test of findProgramSlotByDate method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotByDate() throws NotFoundException, NotFoundException, NotFoundException, SQLException {
        System.out.println("findProgramSlotByDate");
        ArrayList<ProgramSlot> schedList = new ArrayList<>();
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
        schedList.add(p);
        
        PSSearchObject obj = new PSSearchObject();
        obj.setDateOfProgram("2012-01-01");
        obj.setStartTime("01:00:00");
        obj.setRadioProgramName("");
                
        when(sDao.getListByDate(dateOfProgram)).thenReturn(schedList);
        
        List<ProgramSlot> result = sService.findProgramSlotByDate(dateOfProgram);
        assertEquals(1, result.size());
        
    }

    /**
     * Test of findProgramSlotById method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotById() throws SQLException, NotFoundException {
        System.out.println("findProgramSlotById");
        
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
        
        
        PSSearchObject obj = new PSSearchObject();
        obj.setDateOfProgram("2012-01-01");
        obj.setStartTime("01:00:00");
        obj.setRadioProgramName("");
                
        when(sDao.findObject(1)).thenReturn(p);
        
        ProgramSlot result = sService.findProgramSlotById(1);
        assertNotNull( result);
        
    }

    /**
     * Test of findAllProgramSlot method, of class ScheduleService.
     */
    @Test
    public void testFindAllProgramSlot() throws SQLException {
        System.out.println("findAllProgramSlot");
        ArrayList<ProgramSlot> schedList = new ArrayList<>();
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
        schedList.add(p);
        
        when(sDao.loadAll()).thenReturn(schedList);
        
        ArrayList<ProgramSlot> result = sService.findAllProgramSlot();
        assertEquals(1, result.size());
    }

   
    /**
     * Test of findProgramSlotByUserId method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotByUserId() throws SQLException {
        System.out.println("findProgramSlotByUserId");
        ArrayList<ProgramSlot> schedList = new ArrayList<>();
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
        schedList.add(p);
        
        when(sDao.loadAll()).thenReturn(schedList);
        
        ArrayList<ProgramSlot> result = sService.findProgramSlotByUserId("pre1");
        assertEquals(1, result.size());
        
    }


    /**
     * Test of checkConflict method, of class ScheduleService.
     */
    @Test
    public void testCheckConflict() throws NotFoundException, SQLException {
        System.out.println("checkConflict");
        ArrayList<ProgramSlot> schedList = new ArrayList<>();
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
        schedList.add(p);
        
        when(sDao.getListByDate(dateOfProgram)).thenReturn(schedList);
        
        ProgramSlot  psInput = new ProgramSlot();
        
        psInput.setDateOfProgram(dateOfProgram);
        psInput.setDuration(duration);
        psInput.setStartTime(startTime);
        psInput.setPresenter(new Presenter("pre1"));
        psInput.setProducer(new Producer("prod1"));
        psInput.setRadioProgram(new RadioProgram("testProgram"));
        
        boolean conflict = sService.checkConflict(psInput, "");
        assertTrue(conflict);
        
    }

    /**
     * Test of addHourAndMinute method, of class ScheduleService.
     */
    @Test
    public void testAddHourAndMinute() {
        System.out.println("addHourAndMinute");
                Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.HOUR_OF_DAY,10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        
        Date dOrig = cal.getTime();
        
        
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, 1);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND,0);
        Date addHourM = time.getTime();
        
        
        Date date = sService.addHourAndMinute(dOrig, addHourM);
        
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        
        assertEquals(11, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(2012, cal1.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, cal1.get(Calendar.MONTH));
        assertEquals(1, cal1.get(Calendar.DAY_OF_MONTH));
        
    }
}