/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.presenter.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 *
 * @author jiqin
 */
public class PresenterService {
    DAOFactory factory;
    UserDao udao;

    public DAOFactory getFactory() {
        return factory;
    }

    public void setFactory(DAOFactory factory) {
        this.factory = factory;
    }

    public UserDao getUdao() {
        return udao;
    }

    public void setUdao(UserDao udao) {
        this.udao = udao;
    }
    
    public static ArrayList<Role> PRESENTER_ROLE;
    {
        Role role = new Role("presenter");
        PRESENTER_ROLE = new ArrayList<>();
        PRESENTER_ROLE.add(role);
    }
    
    
    public PresenterService(){
        factory = new DAOFactoryImpl();
        udao = factory.getUserDAO();
    }
    
    public PresenterService(DAOFactory factory)
    {
        this.factory = factory;
         udao = factory.getUserDAO();
    }
    
    public List<Presenter> findAllPresenters(Presenter example, PaginationCriteria criteria){
        List<Presenter> presenters = new ArrayList<>();
        User user = new User();
        user.setId(example.getId());
        user.setName(example.getName());
        user.setRoles(PRESENTER_ROLE);
        try{
            List<User> users = udao.searchMatching(user);
            if(users!=null){
                for(User fuser : users){
                    presenters.add(new Presenter(fuser.getId(),fuser.getName()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return presenters;
    }
    
    public List<Presenter> findAllPresenters(Presenter example) {
        return findAllPresenters(example, PaginationCriteria.getDefaultCriteria());
    }
    
    public Presenter findPresenter(String id){
        User user;
        try {
            try {
                user = udao.getObject(id);
                if(user !=null){
                Presenter presenter = new Presenter();
                presenter.setId(user.getId());
                presenter.setName(user.getName());
                return presenter;
             }
            } catch (NotFoundException ex) {
                Logger.getLogger(PresenterService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PresenterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Presenter> findAllPresenters()
    {
         List<Presenter> presenters = new ArrayList<>();
        User user = new User();
        user.setId("");
        user.setName("");
        user.setRoles(PRESENTER_ROLE);
        try{
            List<User> users = udao.searchMatching(user);
            if(users!=null){
                for(User fuser : users){
                    presenters.add(new Presenter(fuser.getId(),fuser.getName()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return presenters;
    }
}
