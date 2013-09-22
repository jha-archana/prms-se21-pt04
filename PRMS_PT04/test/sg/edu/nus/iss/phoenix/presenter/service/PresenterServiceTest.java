/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.presenter.service;

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
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 * Unit Test class for PresenterService
 * @author Wang Jiqin <a0107596@nus.edu.sg>
 */
public class PresenterServiceTest {
    
    private PresenterService pService;
    
    @Mock
    private DataSource ds;
    @Mock
    private DAOFactory factory;
    @Mock
    private UserDao uDao;
    
    public PresenterServiceTest() {
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
         pService = new PresenterService(factory);
         pService.setUdao(uDao);
         
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAllPresenters method, of class PresenterService.
     */
    @Test
    public void testFindAllPresenters_Presenter_PaginationCriteria() throws SQLException {
        System.out.println("findAllPresenters");
      
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("p1");
        user1.setName("presenter 1");
        
        users.add(user1);
        
        Presenter searchP = new Presenter();
        searchP.setId("p1");
        searchP.setName("");
        
        User searchU = new User();
        searchU.setId("p1");
        searchU.setName("");
        searchU.setRoles(PresenterService.PRESENTER_ROLE);
        
        when(uDao.searchMatching(searchU)).thenReturn(users);

        List<Presenter> result = pService.findAllPresenters(searchP, null);
        assertEquals(1, result.size());
        assertEquals("p1",result.get(0).getId());
    }

    /**
     * Test of findAllPresenters method, of class PresenterService.
     */
    @Test
    public void testFindAllPresenters_Presenter() throws SQLException {
        System.out.println("findAllPresenters");
          ArrayList<User> users = new ArrayList<>();
        User user1 = new User("p1");
        user1.setName("presenter 1");
        
        users.add(user1);
        
        Presenter searchP = new Presenter();
        searchP.setId("p1");
        searchP.setName("");
        
        User searchU = new User();
        searchU.setId("p1");
        searchU.setName("");
        searchU.setRoles(PresenterService.PRESENTER_ROLE);
        
        when(uDao.searchMatching(searchU)).thenReturn(users);

        List<Presenter> result = pService.findAllPresenters(searchP);
        assertEquals(1, result.size());
        assertEquals("p1",result.get(0).getId());
        
    }

    /**
     * Test of findPresenter method, of class PresenterService.
     */
    @Test
    public void testFindPresenter() throws SQLException, NotFoundException {
        System.out.println("findPresenter");
        
        User user1 = new User("p1");
        user1.setName("presenter 1");
        
        when(uDao.getObject("p1")).thenReturn(user1);
       
        Presenter p = pService.findPresenter("p1");
        assertNotNull(p);
        assertEquals("presenter 1",p.getName());
    }

    /**
     * Test of findAllPresenters method, of class PresenterService.
     * the method without any parameters
     */
    @Test
    public void testFindAllPresenters_0args() throws SQLException {
        System.out.println("findAllPresenters");
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("p1");
        user1.setName("presenter 1");
        
        users.add(user1);
        
        Presenter searchP = new Presenter();
        searchP.setId("");
        searchP.setName("");
        
        User searchU = new User();
        searchU.setId("");
        searchU.setName("");
        searchU.setRoles(PresenterService.PRESENTER_ROLE);
        when(uDao.searchMatching(searchU)).thenReturn(users);

        List<Presenter> result = pService.findAllPresenters();
        assertEquals(1, result.size());
        assertEquals("p1",result.get(0).getId());
    }
}