/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.producer.service;

import java.sql.SQLException;
import java.util.ArrayList;
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
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterService;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;

/**
 * Unit Test class for ProducerService
* @author Wang Jiqin <a0107596@nus.edu.sg>
 */
public class ProducerServiceTest {
    private ProducerService pService;
    
    @Mock
    private DataSource ds;
    @Mock
    private DAOFactory factory;
    @Mock
    private UserDao uDao;
    
    public ProducerServiceTest() {
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
         pService = new ProducerService(factory);
         pService.setUdao(uDao);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAllProducers method, of class ProducerService.
     */
    @Test
    public void testFindAllProducers_Producer_PaginationCriteria() throws SQLException {
        System.out.println("findAllProducers");
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("p1");
        user1.setName("producer 1");
        
        users.add(user1);
        
        Producer searchP = new Producer();
        searchP.setId("p1");
        searchP.setName("");
        
        User searchU = new User();
        searchU.setId("p1");
        searchU.setName("");
        searchU.setRoles(PresenterService.PRESENTER_ROLE);
        
        when(uDao.searchMatching(searchU)).thenReturn(users);

        List<Producer> result = pService.findAllProducers(searchP, null);
        assertEquals(1, result.size());
        assertEquals("p1",result.get(0).getId());
    }

    /**
     * Test of findAllProducers method, of class ProducerService.
     */
    @Test
    public void testFindAllProducers_Producer() throws SQLException {
        System.out.println("findAllProducers");
          ArrayList<User> users = new ArrayList<>();
        User user1 = new User("p1");
        user1.setName("producer 1");
        
        users.add(user1);
        
        Producer searchP = new Producer();
        searchP.setId("p1");
        searchP.setName("");
        
        User searchU = new User();
        searchU.setId("p1");
        searchU.setName("");
        searchU.setRoles(ProducerService.PRODUCER_ROLE);
        
        when(uDao.searchMatching(searchU)).thenReturn(users);

        List<Producer> result = pService.findAllProducers(searchP);
        assertEquals(1, result.size());
        assertEquals("p1",result.get(0).getId());
        assertEquals("producer 1", result.get(0).getName());
    }

    /**
     * Test of findProducer method, of class ProducerService.
     */
    @Test
    public void testFindProducer() throws SQLException, NotFoundException {
        System.out.println("findProducer");
         User user1 = new User("p1");
        user1.setName("producer 1");
        
        when(uDao.getObject("p1")).thenReturn(user1);
       
        Producer p = pService.findProducer("p1");
        assertNotNull(p);
        assertEquals("producer 1",p.getName());
    }

    /**
     * Test of findAllProducers method, of class ProducerService.
     */
    @Test
    public void testFindAllProducers_0args() throws SQLException {
        System.out.println("findAllProducers");
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("p1");
        user1.setName("producer 1");
        
        users.add(user1);
        
        Producer searchP = new Producer();
        searchP.setId("");
        searchP.setName("");
        
        User searchU = new User();
        searchU.setId("");
        searchU.setName("");
        searchU.setRoles(ProducerService.PRODUCER_ROLE);
        when(uDao.searchMatching(searchU)).thenReturn(users);

        List<Producer> result = pService.findAllProducers();
        assertEquals(1, result.size());
        assertEquals("p1",result.get(0).getId());
    }
}