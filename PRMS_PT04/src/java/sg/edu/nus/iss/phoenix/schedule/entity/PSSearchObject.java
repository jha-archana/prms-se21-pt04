package sg.edu.nus.iss.phoenix.schedule.entity;
import java.sql.Time;
import java.util.Date;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;


public class PSSearchObject {
                private Date dateOfProgram;
                private Time startTime;
                private RadioProgram radioProgram;
		public PSSearchObject() {
			super();
		}
		public PSSearchObject(Date dateOfProgram,Time startTime,String radioProgram) {
			super();
			this.dateOfProgram = dateOfProgram;
                        this.startTime = startTime;
                        RadioProgram rp= new RadioProgram(radioProgram);
                        this.radioProgram= rp;
		}
		public Date getDateOfProgram() {
			return dateOfProgram;
		}
		public void setDateOfProgram(Date dateOfProgram) {
			this.dateOfProgram = dateOfProgram;
		}
		public Time getStartTime() {
			return startTime;
		}
		public void setStartTime(Time startTime) {
			this.startTime = startTime;
		}
		public RadioProgram getRadioProgram() {
			return radioProgram;
		}
		public void setRadioProgram(RadioProgram radioProgram) {
			this.radioProgram = radioProgram;
		}
}
