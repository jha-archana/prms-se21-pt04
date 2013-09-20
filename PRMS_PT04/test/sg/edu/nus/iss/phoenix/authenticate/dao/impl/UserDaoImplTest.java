/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;


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
        //mock up
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true);
        when(rs.getString("id")).thenReturn(user.getId());
        when(rs.getString("name")).thenReturn(user.getName());
        when(rs.getString("password")).thenReturn(user.getPassword());
        when(rs.getString("role")).thenReturn(user.getRoleString());
        //test
        User result = uDao.getObject("test0001");
        //verification
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
    
     @Test(expected=NotFoundException.class)
    public void testLoadNotFound() throws Exception {
        //load a not in db object
        System.out.println("testLoadNotFound");
        String sql = "SELECT * FROM APP.\"user\" WHERE (\"id\" = ? ) ";
       
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(false);
        
         User valueObject404 = new User("test0002");
         uDao.load(valueObject404);
         
     }

    /**
     * Test of loadAll method, of class UserDaoImpl.
     */
    @Test
    public void testLoadAll() throws Exception {
        System.out.println("loadAll");
         String sql = "SELECT * FROM APP.\"user\" ORDER BY \"id\" ASC ";
        User user  = new User("test0001");
        user.setName("test 0001");
        user.setPassword("testpassword");
        user.setRoles(new ArrayList<Role>());
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);  
        when(rs.next()).thenReturn(true).thenReturn(false);
        
        when(rs.getString("id")).thenReturn(user.getId());
        when(rs.getString("name")).thenReturn(user.getName());
        when(rs.getString("password")).thenReturn(user.getPassword());
        when(rs.getString("role")).thenReturn(user.getRoleString());
        List<User> users = uDao.loadAll();
        
        assertEquals(1, users.size());
        assertEquals(user.getId(), users.get(0).getId());
        
    }

    /**
     * Test of create method, of class UserDaoImpl.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        String sql = "INSERT INTO APP.\"user\" ( \"id\", \"password\", \"name\", \"role\") VALUES (?, ?, ?, ?) ";
          User user  = new User("test0001");
        user.setName("test 0001");
        user.setPassword("testpassword");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("presenter"));
        roles.add(new Role("producer"));
        user.setRoles(roles);
        
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);
        
        uDao.create(user);
        
    }

    /**
     * Test of save method, of class UserDaoImpl.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        String sql = "UPDATE APP.\"user\" SET \"password\" = ?, \"name\" = ?, \"role\" = ? WHERE (\"id\" = ? ) ";
         User user  = new User("test0001");
        user.setName("test 0001");
        user.setPassword("testpassword");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("presenter"));
        roles.add(new Role("producer"));
        user.setRoles(roles);
        
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);
        
         uDao.save(user);
        
    }

    /**
     * Test of delete method, of class UserDaoImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
         String sql = "DELETE FROM APP.\"user\" WHERE (\"id\" = ? ) ";
          User user  = new User("test0001");
         when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);
         
        uDao.delete(user);
    }

    /**
     * Test of deleteAll method, of class UserDaoImpl.
     */
    @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
         String sql = "DELETE FROM APP.\"user\"";
         when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);
        uDao.deleteAll();
    }

    /**
     * Test of countAll method, of class UserDaoImpl.
     */
    @Test
    public void testCountAll() throws Exception {
        System.out.println("countAll");
        String sql = "SELECT count(*) FROM APP.\"user\"";
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(sql)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs); 
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt(1)).thenReturn(1);
        int counter = uDao.countAll();
        assertEquals(1,counter );
        
    }
}
