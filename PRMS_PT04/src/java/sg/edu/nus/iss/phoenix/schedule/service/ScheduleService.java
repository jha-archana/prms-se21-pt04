package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterService;
import sg.edu.nus.iss.phoenix.producer.service.ProducerService;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.PSSearchObject;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.utils.SDFUtils;

/**
 *
 * @author Jha Archana
 * @author Srinivasa Reddy Puchakayala
 * @author Wang Jiqin
 * @author Eain Dra Nilar ---add comments to prepare javadoc
 */
public class ScheduleService {
/**
 * Call DaoFactory 
 *
 * @retun factory object 
 */
    public DAOFactory getFactory() {
        return factory;
    }
/**
 *Register DaoFactory 
 *
 * @param factory DAOFactory object 
 */
    public void setFactory(DAOFactory factory) {
        this.factory = factory;
    }
/**
 * Call ScheduleDAO 
 *
 * @retun ScheduleDAO 
 */
    public ScheduleDAO getSchdao() {
        return schdao;
    }
/**
 *Register scheduleDao  
 *
 * @param schdao ScheduleDAO object 
 */
    public void setSchdao(ScheduleDAO schdao) {
        this.schdao = schdao;
    }
/**
 * Call PresenterService to use its operation  
 *
 * @retun PresenterService  
 */
    public PresenterService getPresenterService() {
        return presenterService;
    }
 /**
 *Register PresenterService 
 *
 * @param presenterService PresenterService object 
 */
    public void setPresenterService(PresenterService presenterService) {
        this.presenterService = presenterService;
    }
/**
 * Call ProducerService to use its operation  
 *
 * @retun ProducerService  
 */
    public ProducerService getProducerService() {
        return producerService;
    }
/**
 *Register ProducerService 
 *
 * @param producerService ProducerService object 
 */
    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }
/**
 * Call RadioProgramDAO to use its operation  
 *
 * @retun RadioProgramDAO object 
 */
    public RadioProgramDAO getRpDao() {
        return rpDao;
    }
/**
 *Register RadioProgram 
 *
 * @param rpDao RadioProgramDAO object
 */
    public void setRpDao(RadioProgramDAO rpDao) {
        this.rpDao = rpDao;
    }

    DAOFactory factory;
    ScheduleDAO schdao;
    PresenterService presenterService;
    ProducerService producerService;
    RadioProgramDAO rpDao;
/**
 * Constructor of Schedule Service  
 *
 */
    public ScheduleService() {
        super();
        factory = new DAOFactoryImpl();
        schdao = factory.getScheduleDAO();
        presenterService = new PresenterService();
        producerService = new ProducerService();
        rpDao = factory.getRadioProgramDAO();
    }
 /**
 * Constructor of Schedule Service with DAOFactory
 *
 */
    public ScheduleService(DAOFactory factory) {    
        this.factory = factory;
        schdao = factory.getScheduleDAO();
        presenterService = new PresenterService(factory);
        producerService = new ProducerService(factory);
        rpDao = factory.getRadioProgramDAO();
    }
/**
 * Returns Program Slot object that is existing schedule program searching with program slot object 
 * The ps argument must specify program slot object 
 * <p>
 * This method always returns immediately, whether or not program 
 * slot exists. 
 *
 * @param  ps schedule program or program slot object 
 * @return      the program slot finding by ps
 *
 */
    public ArrayList<ProgramSlot> searchProgramSlot(PSSearchObject ps) {
        ArrayList<ProgramSlot> schedList = new ArrayList<>();
        try {
            schedList = (ArrayList<ProgramSlot>) schdao.searchMatching(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return schedList;
    }
/**
 * Returns Program Slot object that is existing schedule program searching with date argument  
 * The dateOfProgram argument must specify an date of program slot 
 * <p>
 * This method always returns immediately, whether or not program 
 * slot exists in argument dateOfProgram. 
 *
 * @param  dateOfProgram date of program slot
 * @return      the program slot finding by its date
 *
 */
    public List<ProgramSlot> findProgramSlotByDate(Date dateOfProgram) {
        List<ProgramSlot> programSlot =new ArrayList<ProgramSlot>();
        try{
            programSlot =  schdao.getListByDate(dateOfProgram);
        }catch(Exception e){
            e.printStackTrace();
        }
        return programSlot;
    }
/**
 * Returns Program Slot object that is existing schedule program searching with id  
 * The id argument must specify an id of program slot 
 * <p>
 * This method always returns immediately, whether or not program 
 * slot exists. 
 *
 * @param  id id of program slot
 * @return      the program slot finding by its id
 *
 */
    public ProgramSlot findProgramSlotById(int id) {
        ProgramSlot programSlot = new ProgramSlot();
        try {
            programSlot = schdao.findObject(id);
            //eager loading
            rpDao.load(programSlot.getRadioProgram());
            //update presenter
            programSlot.setPresenter(presenterService.findPresenter(programSlot.getPresenter().getId()));
            programSlot.setProducer(producerService.findProducer(programSlot.getProducer().getId()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return programSlot;
    }
/**
 * Returns the list of all existing  Program Slot object 
 * <p>
 * This method always returns immediately, load all existing 
 * program slots with it assigned presenters and producers 
 *
 * @return      the array List of existing program slots
 *
 */
    public ArrayList<ProgramSlot> findAllProgramSlot() {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) schdao.loadAll();
            //eager loading the objects
            for (ProgramSlot ps : currentList) {
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
/**
 * Inserting new program slot
 * <p>
 * This method insert new schedule with its radio program , presenter and producer
 *
 * @param  ps- new schedule or program slot 
 * @return      void
 *
 */
    public void insertProgramSlot(ProgramSlot ps) {
        try {
            schdao.create(ps);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/**
 * Updating schedule program 
 * <p>
 * This method update schedule program
 *
 * @param  ps- existing program slot 
 * @return      void
 *
 */
    
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
/**
 * deleting schedule program or program slot
 * <p>
 * This method is to delete existing schedule program
 *
 * @param  ps- existing program slot object that want to delete 
 * @return      void
 *
 */

    public void deleteProgramSlot(ProgramSlot ps) {
        try {
            schdao.delete(ps);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/**
 * Return the list of program slots that is search by argument userId 
 * 
 * <p>
 * This method is to return list of program slots which is assigned user .
 * User can be presenter or producer
 *
 * @param  userId ,the id of presenter or producer
 * @return  list of program Slots that assigned argument userId
 *
 */
    public ArrayList<ProgramSlot> findProgramSlotByUserId(String userId) {
        ArrayList<ProgramSlot> filterList = new ArrayList<ProgramSlot>();
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) schdao.loadAll();
            //eager loading the objects
            for (ProgramSlot ps : currentList) {
                rpDao.load(ps.getRadioProgram());
                if (ps.getPresenter().getId().equalsIgnoreCase(userId)
                        || ps.getProducer().getId().equalsIgnoreCase(userId)) {
                    //update presenter
                    ps.setPresenter(presenterService.findPresenter(ps.getPresenter().getId()));
                    ps.setProducer(producerService.findProducer(ps.getProducer().getId()));
                    filterList.add(ps);
                }
            }
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filterList;
    }
    /**
 * Return Date value of argument String time 
 * <p>
 * This method is to format and  covert Date value from string time
 *
 * @param  time String value of time  
 * @return  Date Date type return after format and convert string argument
 *
 */

    public Date getDateValueOfString(String time){
        Date date = null;
        String dateString = "01-01-50:"+time+":00";
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
        try{
          date = DATE_FORMAT.parse(dateString);
        }catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }
    /**
 * Return the boolean value after checking conflict of program slot
 * <p>
 * This method is to check the conflict between two argument
 *
 * @param  psInput input program slot 
 * @param  id  id of prgram slot 
 * @return     true for conflicts exist and false for not exist conflict
 *
 */
    public boolean checkConflict(ProgramSlot psInput,String id) {
        boolean isConflict = false;
        List<ProgramSlot> psList = findProgramSlotByDate(psInput.getDateOfProgram());
        Iterator<ProgramSlot> psListIterator = psList.iterator();
        while(psListIterator.hasNext()){
            ProgramSlot progSlot = psListIterator.next();
            System.out.println("StartTime=="+progSlot.getStartTime());
            Date dbstartTimeDur = addHourAndMinute(progSlot.getStartTime(),progSlot.getDuration());
            System.out.println("StartTime + hr=="+dbstartTimeDur);
            Date inStartTimeDur = addHourAndMinute(psInput.getStartTime(),psInput.getDuration());
            //For modification then skip if same id
            if(!id.equals("")){
                if(Integer.parseInt(id)==progSlot.getId()){
                    continue;
                }
            }
            //Check if input start time equals to db start time
            if(progSlot.getStartTime().compareTo(psInput.getStartTime())==0){
                isConflict = true;
            }
            //Check after adding the duration plus start time of db and input start time
            else if(psInput.getStartTime().compareTo(dbstartTimeDur)<=0){
                isConflict = true;
             }
            //Check after adding the duration plus start time of input and db start time
            else if(inStartTimeDur.compareTo(progSlot.getStartTime())<=0){
                isConflict = true;
             }
        }
        return isConflict;
    }
/**
 * Return the date object of two Date argument
 * 
 * <p>
 * This method is to calculate two date and returned it as Date type
 *
 * @param  date the date to calculate
 * @param  DateToAdd the date to add
 * @return     the Date after calculation
 *
 */
    public Date addHourAndMinute(Date date,Date DateToAdd){
            Calendar durHr = Calendar.getInstance();
            durHr.setTime(DateToAdd);
            int hour = durHr.get(Calendar.HOUR_OF_DAY);
            int minute = durHr.get(Calendar.MINUTE);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR_OF_DAY, hour);
            cal.add(Calendar.MINUTE, minute);
            return cal.getTime();
    }
}
