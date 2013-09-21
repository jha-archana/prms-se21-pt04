/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.service;

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
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author jiqin
 */
public class AuthenticateServiceTest {
    
    private AuthenticateService aService;
    
    @Mock
    private DataSource ds;
    
    @Mock
    private DAOFactory factory;
    
    @Mock
    private UserDao uDao;
    
    @Mock
    private RoleDao rDao;
    
    
    
    public AuthenticateServiceTest() {
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
         aService = new AuthenticateService(factory);
         aService.setRdao(rDao);
         aService.setUdao(uDao);
         
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of validateUserIdPassword method, of class AuthenticateService.
     */
    @Test
    public void testValidateUserIdPassword() throws SQLException {
        System.out.println("validateUserIdPassword");
        User toAuth = new User("found");
        toAuth.setPassword("found");
        
        User toAuth1 = new User("notfound");
        toAuth1.setPassword("notfound");
        
        
        when(uDao.searchMatching(toAuth.getId())).thenReturn(toAuth);
        
        User result = aService.validateUserIdPassword(toAuth);
        assertNotNull(result);
        when(uDao.searchMatching(toAuth1.getId())).thenReturn(null);
        User result1 = aService.validateUserIdPassword(toAuth1);
        
        assertNull(result1);
    }

    /**
     * Test of evaluateAccessPreviledge method, of class AuthenticateService.
     */
    @Test
    public void testEvaluateAccessPreviledge() throws NotFoundException, SQLException {
        System.out.println("evaluateAccessPreviledge");
        Role role = new Role("presenter");
        when(rDao.getObject("presenter")).thenReturn(role);
        User user = new User("test");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        
        User result = aService.evaluateAccessPreviledge(user);
        assertNotNull(result);

    }

    /**
     * Test of findAllUser method, of class AuthenticateService.
     */
    @Test
    public void testFindAllUser() throws SQLException {
        System.out.println("findAllUser");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("1"));
        users.add(new User("2"));
        users.add(new User("3"));
        
        when(uDao.loadAll()).thenReturn(users);
        
        ArrayList<User> result = aService.findAllUser();
        assertEquals(3, result.size());
        
    }

    /**
     * Test of searchUsers method, of class AuthenticateService.
     */
    @Test
    public void testSearchUsers() throws SQLException {
        System.out.println("searchUsers");
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("u1");
        user1.setName("u1");
        User user2 = new User("u2");
        user2.setName("u2");
        User user3 = new User("u3");
        user3.setName("u3");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        
        User valueObj = new User("");
        valueObj.setName("u");
        
        when(uDao.searchUserMatching(valueObj)).thenReturn(users);
        
        List<User> result = aService.searchUsers(valueObj);
        assertEquals(3, result.size());
    }

    /**
     * Test of findUser method, of class AuthenticateService.
     */
    @Test
    public void testFindUser() throws SQLException {
        System.out.println("findUser");
        User user1 =  new User("u1");
        user1.setName("u1");
        
        when(uDao.searchMatching("u1")).thenReturn(user1);
        User result = aService.findUser("u1");
        
        assertNotNull(result);
        
    }
    
    /**
     * Test of isExist method, of class AuthenticateService.
     */
    @Test
    public void testIsExist() throws SQLException {
        System.out.println("isExist");
            User user1 =  new User("u1");
        user1.setName("u1");
        
        when(uDao.searchMatching("u1")).thenReturn(user1);
        when(uDao.searchMatching("u2")).thenReturn(null);
        
        boolean existed = aService.isExist("u1");
        assertTrue(existed);
        boolean notexisted = aService.isExist("u2");
        assertFalse(notexisted);
        
    }

    /**
     * Test of findAllRoles method, of class AuthenticateService.
     */
    @Test
    public void testFindAllRoles() throws SQLException {
        System.out.println("findAllRoles");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("admin"));
        roles.add(new Role("manager"));
        
        when(rDao.loadAll()).thenReturn(roles);
        List<Role> result = aService.findAllRoles();
        assertEquals(2, result.size());

    }

    /**
     * Test of findRole method, of class AuthenticateService.
     */
    @Test
    public void testFindRole() throws NotFoundException, SQLException {
        System.out.println("findRole");
                List<Role> roles = new ArrayList<>();
        roles.add(new Role("admin"));
        roles.add(new Role("manager"));
        
        when(rDao.getObject("admin")).thenReturn(roles.get(0));
        when(rDao.getObject("admin1")).thenReturn(null);
        
        Role result = aService.findRole("admin");
        assertNotNull(result);
        result = aService.findRole("admin1");
        assertNull(result);
        
    }
}