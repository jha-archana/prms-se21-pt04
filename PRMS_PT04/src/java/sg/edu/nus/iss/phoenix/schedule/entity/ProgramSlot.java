package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.utils.SDFUtils;

/**
 * ProgramSlot aka Schedule
 * @author tmswj
 */

public class ProgramSlot implements Cloneable, Serializable {
    /**
	 * eclipse identifier
	 */
	private static final long serialVersionUID = -5500218812568593553L;
	
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int id; 
    private Date duration;
    private Date dateOfProgram;
    private Date startTime;
    private RadioProgram radioProgram;
    private Presenter presenter;
    private Producer producer;



    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public ProgramSlot () {

    }

    public ProgramSlot (int id) {
            this.id=id;
    }
   
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id=id;
    }
    public Date getDuration() {
          return this.duration;
    }
    
    public void setDuration(Date duration){
        this.duration = duration;
    }
    
    public void setDuration(String durationIn) {
        try{
          Date dur = SDFUtils.SCHEDULE_SDF_TIME.parse(durationIn);
          setDuration(dur);
        }catch(ParseException e){
            e.printStackTrace();;
        }
    }

    public Date getDateOfProgram() {
          return this.dateOfProgram;
    }
    
    public void setDateOfProgram(String dateOfProgramStr) {
          try{
          Date dur = SDFUtils.SCHEDULE_SDF_DATE.parse(dateOfProgramStr);
          setDateOfProgram(dur);
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
     
    public void setDateOfProgram(Date dateOfProgram) {
          this.dateOfProgram = dateOfProgram;
    }

    public Date getStartTime() {
          return this.startTime;
    }
    
    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }
    
    public void setStartTime(String startTime) {
        try{
          Date dur = SDFUtils.SCHEDULE_SDF_TIME.parse(startTime);
          setStartTime(dur);
        }catch(ParseException e){
            e.printStackTrace();;
        }
    }
    
    public RadioProgram getRadioProgram() {
          return this.radioProgram;
    }
    public void setRadioProgram(RadioProgram radioProgram) {
          this.radioProgram = radioProgram;
    }

    public Presenter getPresenter() {
          return this.presenter;
    }
    public void setPresenter(Presenter presenter) {
          this.presenter = presenter;
    }
    
    public Producer getProducer() {
          return this.producer;
    }
    public void setProducer(Producer producer) {
          this.producer = producer;
    }


    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     */

    public void setAll(String durationIn,Date dateOfProgramIn,String startTimeIn,
            String radioProgramIn,String presenterIn,String producerIn) {
          setDuration(durationIn);
          this.dateOfProgram = dateOfProgramIn;
          setStartTime( startTimeIn);
          RadioProgram rp= new RadioProgram(radioProgramIn);
          this.radioProgram= rp;
          Presenter p1 = new Presenter(presenterIn);
          this.presenter=p1;
          Producer p2 = new Producer(producerIn);
          this.producer=p2;
    }
    /** 
     * hasEqualMapping-method will compare two ProgramSlot instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(RadioProgram valueObject) {
            // to do
          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nProgramSlot class, mapping to table program-slot\n");
        out.append("Persistent attributes: \n"); 
        out.append("duration = " + this.duration + "\n"); 
        out.append("dateOfProgram = " + this.dateOfProgram + "\n"); 
        out.append("startTime = " + this.startTime + "\n"); 
        out.append("radioProgram = " + this.radioProgram.getName()+ "\n");
        out.append("presenter = " + this.presenter.getId()+ "\n");
        out.append("producer = " + this.producer.getId()+ "\n");
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        ProgramSlot cloned = new ProgramSlot();

       //to do
        return cloned;
    }

}


