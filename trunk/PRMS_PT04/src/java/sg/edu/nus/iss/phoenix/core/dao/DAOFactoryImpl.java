package sg.edu.nus.iss.phoenix.core.dao;

import java.util.logging.Level;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDAOImpl;

public class DAOFactoryImpl implements DAOFactory {

    
    private UserDao userDAO;
    private RoleDao roleDAO;
    private RadioProgramDAO radioProgramDA0;
    private AnnualScheduleDAO annualScheduleDAO;
    private WeeklyScheduleDAO weeklyScheduleDAO;
    private ScheduleDAO scheduleDAO;
    private DataSource ds;

    public DAOFactoryImpl() {
        userDAO = new UserDaoImpl();
        roleDAO = new RoleDaoImpl();
        radioProgramDA0 = new RadioProgramDAOImpl();
        annualScheduleDAO = new AnnualScheduleDAOImpl();
        weeklyScheduleDAO = new WeeklyScheduleDAOImpl();
        scheduleDAO = new ScheduleDAOImpl();
    }
    
    public DAOFactoryImpl(DataSource ds){
         this.ds = ds;
        userDAO = new UserDaoImpl(ds);
         roleDAO = new RoleDaoImpl(ds);
         radioProgramDA0 = new RadioProgramDAOImpl(ds);
         annualScheduleDAO = new AnnualScheduleDAOImpl(ds);
         weeklyScheduleDAO = new WeeklyScheduleDAOImpl(ds);
         scheduleDAO = new ScheduleDAOImpl(ds);
    }
    
    @Override
    public void setDataSource(DataSource ds){
       
    }
    
    @Override
    public UserDao getUserDAO() {
        return userDAO;
    }

    @Override
    public RoleDao getRoleDAO() {
        return roleDAO;
    }

    @Override
    public RadioProgramDAO getRadioProgramDAO() {
        return radioProgramDA0;
    }

    @Override
    public AnnualScheduleDAO getAnnualScheduleDAO() {
        return annualScheduleDAO;
    }

    @Override
    public WeeklyScheduleDAO getWeeklyScheduleDAO() {
        return weeklyScheduleDAO;
    }

    @Override
    public ScheduleDAO getScheduleDAO() {
        return scheduleDAO;
    }
}
