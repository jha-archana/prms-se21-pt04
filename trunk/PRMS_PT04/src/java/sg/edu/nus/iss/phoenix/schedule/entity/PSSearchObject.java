package sg.edu.nus.iss.phoenix.schedule.entity;

/**
 *
 * @author Jeremy
 */

public class PSSearchObject {
        private String dateOfProgram;
        private String startTime;
        private String radioProgramName;

        public PSSearchObject() {
                super();
        }
        public PSSearchObject(String dateOfProgram,String startTime,String radioProgramName) {
                super();
                this.dateOfProgram = dateOfProgram;
                this.startTime = startTime;
                this.radioProgramName= radioProgramName;
        }

        public String getDateOfProgram() {
            return this.dateOfProgram;
        }

        public void setDateOfProgram(String dateOfProgram) {
             this.dateOfProgram = dateOfProgram;
        }

        public String getStartTime() {
             return this.startTime;
        }

        public void setStartTime(String startTime){
             this.startTime = startTime;
        }

        public String getRadioProgramName() {
              return this.radioProgramName;
        }
        public void setRadioProgramName(String radioProgramName) {
              this.radioProgramName = radioProgramName;
        }
        
        @Override 
        public boolean equals(Object obj){
            if(!(obj instanceof PSSearchObject)) return false;
            PSSearchObject os = (PSSearchObject) obj;
            return this.dateOfProgram.equals(os.getDateOfProgram()) &&
                    this.radioProgramName.equals(os.getRadioProgramName()) &&
                    this.startTime.equals(os.getStartTime());
        }
}