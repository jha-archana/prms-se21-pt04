/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.dao;

import javax.sql.DataSource;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;

/**
 *
 * @author projects
 */
public interface DAOFactory {

	RadioProgramDAO getRadioProgramDAO();

	RoleDao getRoleDAO();

	UserDao getUserDAO();
        
        AnnualScheduleDAO getAnnualScheduleDAO();
       
        WeeklyScheduleDAO getWeeklyScheduleDAO();
        
        ScheduleDAO getScheduleDAO();
        
        void setDataSource(DataSource ds);
	
}
