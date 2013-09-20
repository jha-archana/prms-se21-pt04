/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;


/**
 * Unit Test for UserDaoImpl class
 * 
 */
public class UserDaoImplTest {
    
    
    private UserDaoImpl uDao;
    
    @Mock
    private DataSource ds;
    @Mock
     private Connection conn;
    @Mock
    private ResultSet rs;
    @Mock
    private PreparedStatement stmt;
    
    public UserDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks( this );
        uDao = new UserDaoImpl(ds);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createValueObject method, of class UserDaoImpl.
     */
    @Test
    public void testCreateValueObject() {
        System.out.println("createValueObject");
        User result = uDao.createValueObject();
        assertEquals(result.getId(),null);
        assertEquals(result.getName(),null);
        assertEquals(result.getPassword(),null);
        assertEquals(result.getRoleString(), "");
        assertTrue(result.getRoles().isEmpty());
        
    }

    /**
     * Test of getObject method, of class UserDaoImpl.
     */
    @Test
    public void testGetObject() throws Exception {
        
        System.out.println("getObject");
        String sql = "SELECT * FROM APP.\"user\" WHERE (\"id\" = ? ) ";
        User user  = new User("test0001");
        user.setName("test 0001");
        user.setPassword("testpassword");
        user.setRoles(new ArrayList<Role>());
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true);
        when(rs.getString("id")).thenReturn(user.getId());
        when(rs.getString("name")).thenReturn(user.getName());
        when(rs.getString("password")).thenReturn(user.getPassword());
        when(rs.getString("role")).thenReturn(user.getRoleString());
        User result = uDao.getObject("test0001");
        
        assertEquals("test0001", result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getPassword(),result.getPassword());
        assertEquals(user.getRoleString(),result.getRoleString());
        
    }

    /**
     * Test of load method, of class UserDaoImpl.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
         String sql = "SELECT * FROM APP.\"user\" WHERE (\"id\" = ? ) ";
        User user  = new User("test0001");
        user.setName("test 0001");
        user.setPassword("testpassword");
        user.setRoles(new ArrayList<Role>());
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true);
        when(rs.getString("id")).thenReturn(user.getId());
        when(rs.getString("name")).thenReturn(user.getName());
        when(rs.getString("password")).thenReturn(user.getPassword());
        when(rs.getString("role")).thenReturn(user.getRoleString());
        
        User valueObject = new User("test0001");
        assertNull(valueObject.getName());
        assertNull(valueObject.getPassword());
        assertEquals(0, valueObject.getRoles().size());
        assertEquals("", valueObject.getRoleString());
        
        uDao.load(valueObject);
        
        assertEquals(user.getName(), valueObject.getName());
        assertEquals(user.getPassword(),valueObject.getPassword());
        assertEquals(user.getRoleString(),valueObject.getRoleString());
        
    }

    /**
     * Test of loadAll method, of class UserDaoImpl.
     */
    @Test
    public void testLoadAll() throws Exception {
        System.out.println("loadAll");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class UserDaoImpl.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class UserDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAll method, of class UserDaoImpl.
     */
    @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of countAll method, of class UserDaoImpl.
     */
    @Test
    public void testCountAll() throws Exception {
        System.out.println("countAll");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMatching method, of class UserDaoImpl.
     */
    @Test
    public void testSearchMatching_String() throws Exception {
        System.out.println("searchMatching");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMatching method, of class UserDaoImpl.
     */
    @Test
    public void testSearchMatching_User() throws Exception {
        System.out.println("searchMatching");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUserMatching method, of class UserDaoImpl.
     */
    @Test
    public void testSearchUserMatching() throws Exception {
        System.out.println("searchUserMatching");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of databaseUpdate method, of class UserDaoImpl.
     */
    @Test
    public void testDatabaseUpdate() throws Exception {
        System.out.println("databaseUpdate");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of singleQuery method, of class UserDaoImpl.
     */
    @Test
    public void testSingleQuery() throws Exception {
        System.out.println("singleQuery");
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of listQuery method, of class UserDaoImpl.
     */
    @Test
    public void testListQuery() throws Exception {
        System.out.println("listQuery");
        
        fail("The test case is a prototype.");
    }
}
