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
 */
public class ScheduleService {

    public DAOFactory getFactory() {
        return factory;
    }

    public void setFactory(DAOFactory factory) {
        this.factory = factory;
    }

    public ScheduleDAO getSchdao() {
        return schdao;
    }

    public void setSchdao(ScheduleDAO schdao) {
        this.schdao = schdao;
    }

    public PresenterService getPresenterService() {
        return presenterService;
    }

    public void setPresenterService(PresenterService presenterService) {
        this.presenterService = presenterService;
    }

    public ProducerService getProducerService() {
        return producerService;
    }

    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    public RadioProgramDAO getRpDao() {
        return rpDao;
    }

    public void setRpDao(RadioProgramDAO rpDao) {
        this.rpDao = rpDao;
    }

    DAOFactory factory;
    ScheduleDAO schdao;
    PresenterService presenterService;
    ProducerService producerService;
    RadioProgramDAO rpDao;

    public ScheduleService() {
        super();
        factory = new DAOFactoryImpl();
        schdao = factory.getScheduleDAO();
        presenterService = new PresenterService();
        producerService = new ProducerService();
        rpDao = factory.getRadioProgramDAO();
    }
    
    public ScheduleService(DAOFactory factory) {    
        this.factory = factory;
        schdao = factory.getScheduleDAO();
        presenterService = new PresenterService(factory);
        producerService = new ProducerService(factory);
        rpDao = factory.getRadioProgramDAO();
    }

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

    public List<ProgramSlot> findProgramSlotByDate(Date dateOfProgram) {
        List<ProgramSlot> programSlot =new ArrayList<ProgramSlot>();
        try{
            programSlot =  schdao.getListByDate(dateOfProgram);
        }catch(Exception e){
            e.printStackTrace();
        }
        return programSlot;
    }

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
