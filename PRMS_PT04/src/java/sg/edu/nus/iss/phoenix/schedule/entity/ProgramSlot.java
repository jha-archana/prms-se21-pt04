package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

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
    private Time duration;
    private Date dateOfProgram;
    private Time startTime;
    private RadioProgram radioProgram;



    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public ProgramSlot () {

    }

   
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public Time getDuration() {
          return this.duration;
    }
    public void setDuration(Time durationIn) {
          this.duration = durationIn;
    }

    public Date getdateOfProgram() {
          return this.dateOfProgram;
    }
    public void setDateOfProgram(Date dateOfProgram) {
          this.dateOfProgram = dateOfProgram;
    }

    public Time getStartTime() {
          return this.startTime;
    }
    public void setStartTime(Time startTime) {
          this.startTime = startTime;
    }
    
    public RadioProgram getRadioProgram() {
          return this.radioProgram;
    }
    public void setRadioProgram(RadioProgram radioProgram) {
          this.radioProgram = radioProgram;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     */

   /* public void setAll(String nameIn,
          String descriptionIn,
          Time typicalDurationIn) {
          this.name = nameIn;
          this.description = descriptionIn;
          this.typicalDuration = typicalDurationIn;
    }*/


    /** 
     * hasEqualMapping-method will compare two RadioProgram instances
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
        //to do
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
