package sg.edu.nus.iss.phoenix.authenticate.service;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.*;

import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

public class AuthenticateService {

    private static final Logger logger =
            Logger.getLogger(AuthenticateService.class.getName());
    DAOFactory factory;
    UserDao udao;
    RoleDao rdao;

    public UserDao getUdao() {
        return udao;
    }

    public void setUdao(UserDao udao) {
        this.udao = udao;
    }

    public RoleDao getRdao() {
        return rdao;
    }

    public void setRdao(RoleDao rdao) {
        this.rdao = rdao;
    }

    /**
     * default constructor, it will use the default implementation of DAOFactory
     */
    public AuthenticateService() {
        this.factory = new DAOFactoryImpl();
        udao = factory.getUserDAO();
        rdao = factory.getRoleDAO();
    }

    /**
     * Constructor with instance of DAOFactory
     *
     * @param factory DAOFactory
     */
    public AuthenticateService(DAOFactory factory) {
        this.factory = factory;
        this.udao = factory.getUserDAO();
        this.rdao = factory.getRoleDAO();
    }

    /**
     * Set the DAOFacotry
     *
     * @param factory DAOFactory
     */
    public void setFactory(DAOFactory factory) {
        this.factory = factory;
        udao = factory.getUserDAO();
        rdao = factory.getRoleDAO();
    }

    /*
     public User validateUserIdPassword(User user) {
     ArrayList<User> userList = new ArrayList<User>();
     try {
     userList = (ArrayList<User>) udao.loadAll();
     for (Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
     User user1 = (User) iterator.next();
     if (user.getId().equalsIgnoreCase(user1.getId()) && user.getPassword().equalsIgnoreCase(user1.getPassword()) ) {
     return user1;
     }
     //System.out.println(user1.toString());
     }
     } catch (SQLException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     return null;
     }
     */
    /**
     * The user that we pass in to authenticate is different from the instance
     * that is returned
     *
     * @param toAuth
     * @return
     */
    public User validateUserIdPassword(final User toAuth) {
        User found = null;
        try {
            found = udao.searchMatching(toAuth.getId());
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "user searchMatching", ex);
            return (null);
        }
        if (null == found) {
            return (null);
        }

        //Populate the roles
        try {
            for (Role r : found.getRoles()) {
                Role _role = rdao.searchMatching(r.getRole());
                //Should we do something with roles that are without access privilege?
                if (null != _role) {
                    r.setAccessPrivilege(_role.getAccessPrivilege());
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "user searchMatching", ex);
        }

        return (found);
    }

    public User evaluateAccessPreviledge(User user) {
        try {
            Role role = rdao.getObject(user.getRoles().get(0).getRole());
            //user.setAccessPrivilege(role.getAccessPrivilege());
            return user;
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }

    /**
     * to retrieve all user in the repository
     *
     * @return list of user object
     */
    public ArrayList<User> findAllUser() {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            userList = (ArrayList<User>) udao.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * to retrieve user from repository with matching user criteria to see user
     * criteria, go to searchUserMatching method
     *
     * @param uso
     * @return a list of user object matching the criteria
     */
    public ArrayList<User> searchUsers(User uso) {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            userList = (ArrayList<User>) udao.searchUserMatching(uso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * to retrieve user by id
     *
     * @param userId
     * @return specific user object or null if not found
     */
    public User findUser(String userId) {
        User u = null;
        try {
            u = udao.searchMatching(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(AuthenticateService.class.getName()).log(Level.SEVERE, null, e);
        }
        return u;
    }

    /**
     * to insert new user
     *
     * @param user t
     */
    public void insertUser(User user) {
        try {
            if (!isExist(user.getId())) {
                udao.create(user);
            } else {
                throw new Exception("User is Found");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * to update existing user
     *
     * @param user t
     */
    public void updateUser(User user) {
        try {
            udao.save(user);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * to delete existing user
     *
     * @param user t
     */
    public void deleteUser(User user) {
        try {
            udao.delete(user);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * to check if user is exist by id
     *
     * @param userId
     * @return true or false
     */
    public boolean isExist(String userId) {
        User userExist = null;
        try {
            userExist = udao.searchMatching(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userExist != null) {
            return true;
        }
        return false;
    }

    /**
     * to find all role
     *
     * @return an array list of Role object
     */
    public ArrayList<Role> findAllRoles() {
        ArrayList<Role> roleList = new ArrayList<Role>();
        try {
            roleList = (ArrayList<Role>) rdao.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * to find role by name
     *
     * @param role
     * @return instance of Role object
     */
    public Role findRole(String role) {
        Role r = null;
        try {
            r = rdao.getObject(role);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return r;
    }
}
