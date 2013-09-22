/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.producer.service;

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
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 *
 * @author jiqin
 * @author Eain Dra Nilar ---add comments to prepare javadoc
 */
public class ProducerService {
    DAOFactory factory;
    UserDao udao;
    
    public static ArrayList<Role> PRODUCER_ROLE;
    {
        Role role = new Role("producer");
        PRODUCER_ROLE = new ArrayList<>();
        PRODUCER_ROLE.add(role);
    }
    
 /**
 * Constructor of Producer Service  
 *
 */
    public ProducerService(){
        factory = new DAOFactoryImpl();
        udao = factory.getUserDAO();
    }
    /**
 * Constructor of Producer Service with DAOFactory  
 *
 */
    public ProducerService(DAOFactory factory){
        this.factory = factory;
        udao = factory.getUserDAO();
    }
 /**
 * Returns List of Producer object  
 * <p>
 * This method return Producer list of producers according to its given criteria
 *
 * @param  example Producer object 
 * @param  criteria search criteria 
 * @return      List of producers
 *
 */
    public List<Producer> findAllProducers(Producer example, PaginationCriteria criteria){
        List<Producer> producers = new ArrayList<>();
        User user = new User();
        user.setId(example.getId());
        user.setName(example.getName());
        user.setRoles(PRODUCER_ROLE);
        try{
            List<User> users = udao.searchMatching(user);
            if(users!=null){
                for(User fuser : users){
                    producers.add(new Producer(fuser.getId(),fuser.getName()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return producers;
    }
  /**
 * Returns List of all Producer objects  
 * <p>
 * This method return all  Producer list of producers according to its given criteria
 *
 * @param  example Producer object 
 * @return      List of producers
 *
 */
    public List<Producer> findAllProducers(Producer example) {
        return findAllProducers(example, PaginationCriteria.getDefaultCriteria());
    }
 /**
 * Returns producer object according to argument id 
 * <p>
 * This method return producer object according to its argument id
 *
 * @param  id id of Producer 
 * @return      producer object
 *
 */  
    public Producer findProducer(String id){
        User user;
        try {
            try {
                user = udao.getObject(id);
                   if(user != null ){
                        Producer producer = new Producer();
                        producer.setId(user.getId());
                        producer.setName(user.getName());

                        return producer;
                    }
            } catch (NotFoundException ex) {
                Logger.getLogger(ProducerService.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(ProducerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  /**
 * Returns List of Producer object  
 * <p>
 * This method return all producer object 
 *
 * @return      List of producers
 *
 */
    public List<Producer> findAllProducers(){
         List<Producer> producers = new ArrayList<>();
        User user = new User();
        user.setId("");
        user.setName("");
        user.setRoles(PRODUCER_ROLE);
        try{
            List<User> users = udao.searchMatching(user);
            if(users!=null){
                for(User fuser : users){
                    producers.add(new Producer(fuser.getId(),fuser.getName()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return producers;
    }
    
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
}
