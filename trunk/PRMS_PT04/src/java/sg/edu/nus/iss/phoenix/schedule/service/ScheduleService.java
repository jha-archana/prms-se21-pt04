package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Jha Archana
 */
public class ScheduleService {
        
        DAOFactoryImpl factory;
	ScheduleDAO schdao;

	public ScheduleService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		schdao = factory.getScheduleDAO();
	}

	public ArrayList<ProgramSlot> searchProgramSlot(ProgramSlot progSl) {
		//to do 
		return null;
	}

	public ArrayList<ProgramSlot> findProgramSlotByCriteria(ProgramSlot ps) {
		//to do 
		return null;
	}

	public ProgramSlot findProgramSlot(String psName) {
		//to do 
		return null;
	}

	public ArrayList<ProgramSlot> findAllProgramSlot() {
		ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
		try {
			currentList = (ArrayList<ProgramSlot>) schdao.loadAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;

	}

	public void insertProgramSlot(ProgramSlot ps) {
		//to do 
	}

	public void updateProgramSlot(ProgramSlot ps) {
		//to do 
	}

	public void deleteProgramSlot(ProgramSlot ps) {
		//to do 
	}

}
