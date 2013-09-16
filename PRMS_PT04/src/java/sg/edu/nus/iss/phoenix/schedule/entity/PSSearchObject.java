package sg.edu.nus.iss.phoenix.schedule.entity;
import java.sql.Time;
import java.util.Date;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;


public class PSSearchObject {
                private Date dateOfProgram;
                private Time startTime;
                private String radioProgram;
		public PSSearchObject() {
			super();
		}
		public PSSearchObject(Date dateOfProgram,Time startTime,String radioProgram) {
			super();
			this.dateOfProgram = dateOfProgram;
                        this.startTime = startTime;
                        this.radioProgram= radioProgram;
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
		public String getRadioProgramName() {
			return radioProgram;
		}
		public void setRadioProgram(String radioProgram) {
			this.radioProgram = radioProgram;
		}
}
