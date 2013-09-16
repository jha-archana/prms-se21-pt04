package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterService;
import sg.edu.nus.iss.phoenix.producer.service.ProducerService;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Jha Archana
 */
public class ScheduleService {
        
        DAOFactoryImpl factory;
	ScheduleDAO schdao;
        PresenterService presenterService;
        ProducerService producerService;
        RadioProgramDAO rpDao;

	public ScheduleService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		schdao = factory.getScheduleDAO();
                presenterService = new PresenterService();
                producerService = new ProducerService();
                rpDao = factory.getRadioProgramDAO();
	}

	public ArrayList<ProgramSlot> searchProgramSlot(ProgramSlot progSl) {
		//to do 
		return null;
	}

	public ArrayList<ProgramSlot> findProgramSlotByCriteria(ProgramSlot ps) {
		//to do 
		return null;
	}

	public ProgramSlot findProgramSlot(int id) {
            ProgramSlot programSlot = new ProgramSlot();
                try{
                    programSlot = schdao.findObject(id);
                    //eager loading
                    rpDao.load(programSlot.getRadioProgram());
                    //update presenter
                    programSlot.setPresenter(presenterService.findPresenter(programSlot.getPresenter().getId()));
                    programSlot.setProducer(producerService.findProducer(programSlot.getProducer().getId()));
                }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                return programSlot;
	}

	public ArrayList<ProgramSlot> findAllProgramSlot() {
		ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
		try {
			currentList = (ArrayList<ProgramSlot>) schdao.loadAll();
                        //eager loading the objects
                        for(ProgramSlot ps: currentList){
                            rpDao.load(ps.getRadioProgram());
                            //update presenter
                            ps.setPresenter(presenterService.findPresenter(ps.getPresenter().getId()));
                            ps.setProducer(producerService.findProducer(ps.getProducer().getId()));
                        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException ex) {
                Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
            }
		return currentList;

	}

	public void insertProgramSlot(ProgramSlot ps) {
		try {
			schdao.create(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void updateProgramSlot(ProgramSlot ps) {
		try {
				schdao.save(ps);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}

	public void deleteProgramSlot(ProgramSlot ps) {
		//to do 
	}

}
