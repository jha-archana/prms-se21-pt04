/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;

/**
 *
 * @author tmswj
 */
public class WeeklyScheduleDAOImpl implements WeeklyScheduleDAO {
     
     
     public WeeklyScheduleDAOImpl() {
        
    }
    private DataSource ds;

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    
    public WeeklyScheduleDAOImpl(DataSource ds) {
        this.ds = ds;
    }
    
}
