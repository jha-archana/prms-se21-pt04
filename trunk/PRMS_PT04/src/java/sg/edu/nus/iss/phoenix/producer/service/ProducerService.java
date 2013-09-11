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
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 *
 * @author jiqin
 */
public class ProducerService {
    DAOFactoryImpl factory;
    UserDao udao;
    
    private static ArrayList<Role> PRODUCER_ROLE;
    {
        Role role = new Role("producer");
        PRODUCER_ROLE = new ArrayList<>();
        PRODUCER_ROLE.add(role);
    }
    
    
    public ProducerService(){
        factory = new DAOFactoryImpl();
        udao = factory.getUserDAO();
    }
    
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
    
    public List<Producer> findAllProducers(Producer example) {
        return findAllProducers(example, PaginationCriteria.getDefaultCriteria());
    }
    
    public Producer findProducer(String id){
        User user;
        try {
            user = udao.searchMatching(id);
            if(user != null ){
                Producer producer = new Producer();
                producer.setId(user.getId());
                producer.setName(user.getName());
                
                return producer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProducerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
