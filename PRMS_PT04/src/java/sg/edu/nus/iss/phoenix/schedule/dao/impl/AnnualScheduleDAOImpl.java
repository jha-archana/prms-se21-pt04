/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;

/**
 *
 * @author tmswj
 */
public class AnnualScheduleDAOImpl implements AnnualScheduleDAO {
    
    private DataSource ds;

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
    
    
    public AnnualScheduleDAOImpl() {
    }
    
    public AnnualScheduleDAOImpl(DataSource ds) {
       this.ds = ds;
    }
    
}
