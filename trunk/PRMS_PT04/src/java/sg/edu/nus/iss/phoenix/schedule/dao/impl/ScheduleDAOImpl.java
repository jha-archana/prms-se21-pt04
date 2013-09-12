package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAOImpl;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Jha Archana
 */
public class ScheduleDAOImpl implements ScheduleDAO {
    private static final Logger logger = Logger.getLogger(ScheduleDAOImpl.class.getName());
        DataSource ds ;
        private UserDao uDao = new UserDaoImpl();
        private RadioProgramDAO rpDao = new RadioProgramDAOImpl();
	//Connection connection;
        
        public ScheduleDAOImpl()
        {
             try {
            ds = (DataSource) InitialContext.doLookup("jdbc/PrmsDatasource");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Cannot connect to the DS");
            }
        }
        
	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#createValueObject()
	 */
	@Override
	public ProgramSlot createValueObject() {
		return new ProgramSlot();
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#getObject(java.lang.String)
	 */
	@Override
	public ProgramSlot getObject(Time duration, Date dateOfProgram, Time startTime) throws NotFoundException,
			SQLException {

		 ProgramSlot valueObject = createValueObject();
                valueObject.setDateOfProgram(dateOfProgram);
                valueObject.setDuration(duration);
                valueObject.setStartTime(startTime);
                load(valueObject);
                return valueObject;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#load(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
	 */
	@Override
	public void load(ProgramSlot valueObject) throws NotFoundException,
			SQLException {
               String sql = "SELECT * FROM APP.\"program-slot\" WHERE (\"dateOfProgram\" = ? AND \"duration\" = ? ) ";
                //PreparedStatement stmt = null;
                try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
                    stmt.setDate(1, new java.sql.Date(valueObject.getdateOfProgram().getTime()));
                    stmt.setTime(2, valueObject.getDuration());
                    singleQuery(stmt, valueObject);
                }
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#loadAll()
	 */
	@Override
	public List<ProgramSlot> loadAll() throws SQLException {
		List<ProgramSlot> searchResults = null;
                 String sql = "SELECT * FROM APP.\"program-slot\" ORDER BY \"dateOfProgram\",\"startTime\" ";
                try (Connection conn = ds.getConnection()) {
                    searchResults = listQuery(conn.prepareStatement(sql));
                }
                return searchResults;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#create(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
	 */
	@Override
	public synchronized void create(ProgramSlot valueObject)
			throws SQLException {
		String sql = "INSERT INTO APP.\"program-slot\" ( \"duration\", \"dateOfProgram\", \"startTime\",\"program-name\" ,\"presenter-id\",\"producer-id\") VALUES (?, ?, ?, ?,?,?) ";

            try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
                stmt.setTime(1, valueObject.getDuration());
                stmt.setDate(2, new java.sql.Date(valueObject.getdateOfProgram().getTime()));
                stmt.setTime(3, valueObject.getStartTime() );
                stmt.setString(4, valueObject.getRadioProgram().getName());
                stmt.setString(5, valueObject.getPresenter().getId());
                stmt.setString(6, valueObject.getProducer().getId());

                int rowcount = databaseUpdate(stmt);
                if (rowcount != 1) {
                    // System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
                }
            } catch (SQLException se) {
                se.printStackTrace();;
            }

	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#save(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
	 */
	@Override
	public void save(ProgramSlot valueObject) throws NotFoundException,
			SQLException {
            String sql = "UPDATE APP.\"program-slot\" SET  \"startTime\"=?,\"program-name\"=? ,\"presenter-id\"=?,\"producer-id\"=? WHERE (\"duration\"=? AND \"dateOfProgram\"=?,)";

        try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setTime(1, valueObject.getStartTime());
            stmt.setString(2, valueObject.getRadioProgram().getName());
            stmt.setString(3, valueObject.getPresenter().getId());
            stmt.setString(4, valueObject.getProducer().getId());
            stmt.setTime(5, valueObject.getDuration());
            stmt.setDate(6,new java.sql.Date(valueObject.getdateOfProgram().getTime()));

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException(
                        "Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        }

	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#delete(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
	 */
	@Override
	public void delete(ProgramSlot valueObject) throws NotFoundException,
			SQLException {
            String sql = "DELETE FROM APP.\"program-slot\" WHERE (\"duration\" = ? AND \"dateOfProgram\"=? ) ";

            try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
                stmt.setTime(1, valueObject.getStartTime());
                stmt.setDate(2, new java.sql.Date( valueObject.getdateOfProgram().getTime()));
                
                int rowcount = databaseUpdate(stmt);
                if (rowcount == 0) {
                    // System.out.println("Object could not be deleted (PrimaryKey not found)");
                    throw new NotFoundException(
                            "Object could not be deleted! (PrimaryKey not found)");
                }
                if (rowcount > 1) {
                    // System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                    throw new SQLException(
                            "PrimaryKey Error when updating DB! (Many objects were deleted!)");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#deleteAll(java.sql.Connection)
	 */
	@Override
	public void deleteAll(Connection conn) throws SQLException {
               String sql = "DELETE FROM APP.\"program-slot\"";
            try ( PreparedStatement stmt = conn.prepareStatement(sql);) {
                int rowcount = databaseUpdate(stmt);
                System.out.println("Deleted rows :" + rowcount);
            }
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#countAll()
	 */
	@Override
	public int countAll() throws SQLException {
		String sql = "SELECT count(*) FROM APP.\"program-slot\"";

        ResultSet result = null;
        int allRows = 0;
        try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

            result = stmt.executeQuery();

            if (result.next()) {
                allRows = result.getInt(1);
            }
        }
        return allRows;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.radioprogram.dao.impl.RadioProgramDAO#searchMatching(sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram)
	 */
	@Override
	public List<ProgramSlot> searchMatching(ProgramSlot valueObject) throws SQLException {

		List<ProgramSlot> searchResults = new ArrayList<ProgramSlot>();
		//To do
		return searchResults;
	}

	/**
	 * databaseUpdate-method. This method is a helper method for internal use.
	 * It will execute all database handling that will change the information in
	 * tables. SELECT queries will not be executed here however. The return
	 * value indicates how many rows were affected. This method will also make
	 * sure that if cache is used, it will reset when data changes.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 */
	protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return only one row. The
	 * resultset will be converted to valueObject. If no rows were found,
	 * NotFoundException will be thrown.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 * @param valueObject
	 *            Class-instance where resulting data will be stored.
	 */
	protected void singleQuery(PreparedStatement stmt, ProgramSlot valueObject)
			throws NotFoundException, SQLException {
              ResultSet result = null;

            try {
                result = stmt.executeQuery();

                if (result.next()) {

                    valueObject.setDateOfProgram(result.getDate("dateOfProgram"));
                    valueObject.setDuration(result.getTime("duration"));
                    valueObject.setStartTime(result.getTime("startTime"));
                   String programName = result.getString("program-name");
                    RadioProgram rp = rpDao.getObject(programName);
                    if(rp!=null){
                        valueObject.setRadioProgram(rp);
                    }
                    
                   String presenterId = result.getString("presenter-id");
                   User user = uDao.getObject(presenterId);
                   if(user != null){
                       Presenter presenter = new Presenter();
                       presenter.setId(user.getId());
                       presenter.setName(user.getName());
                       valueObject.setPresenter(presenter);
                   }
                   
                   String producerId = result.getString("producer-id");
                   User prod = uDao.getObject(producerId);
                   if(prod!=null){
                       Producer producer = new Producer();
                       producer.setId(prod.getId());
                       producer.setName(prod.getName());
                       valueObject.setProducer(producer);
                   }

                } else {
                    // System.out.println("User Object Not Found!");
                    throw new NotFoundException("User Object Not Found!");
                }
            } finally {
                if (result != null) {
                    result.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * resultset will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param conn
	 *            This method requires working database connection.
	 * @param stmt
	 *            This parameter contains the SQL statement to be excuted.
	 */
	protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                ProgramSlot valueObject = createValueObject();

                    valueObject.setDateOfProgram(result.getDate("dateOfProgram"));
                    valueObject.setDuration(result.getTime("duration"));
                    valueObject.setStartTime(result.getTime("startTime"));
                   String programName = result.getString("program-name");
                    
                try {
                    RadioProgram rp = rpDao.getObject(programName);
                    if(rp!=null){
                        valueObject.setRadioProgram(rp);
                    }
                    
                   String presenterId = result.getString("presenter-id");
                   User user = uDao.getObject(presenterId);
                   if(user != null){
                       Presenter presenter = new Presenter();
                       presenter.setId(user.getId());
                       presenter.setName(user.getName());
                       valueObject.setPresenter(presenter);
                   }
                   
                   String producerId = result.getString("producer-id");
                   User prod = uDao.getObject(producerId);
                   if(prod!=null){
                       Producer producer = new Producer();
                       producer.setId(prod.getId());
                       producer.setName(prod.getName());
                       valueObject.setProducer(producer);
                   }
                } catch (NotFoundException ex) {
                    Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                    

                searchResults.add(valueObject);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List<ProgramSlot>) searchResults;
	}
        
        /*
	private void openConnection() {
		try {
			Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.connection = DriverManager.getConnection(DBConstants.dbUrl,
					DBConstants.dbUserName, DBConstants.dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
