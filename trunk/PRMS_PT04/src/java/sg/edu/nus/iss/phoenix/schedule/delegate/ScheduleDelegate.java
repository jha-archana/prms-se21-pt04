/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;
import sg.edu.nus.iss.phoenix.schedule.entity.PSSearchObject;

/**
 *
 * @author Jha Archana
 */
public class ScheduleDelegate {
        public ArrayList<ProgramSlot> searchProgramSlot(PSSearchObject rpso) {
		//to do
		return null;	
	}
	
	/*public ArrayList<ProgramSlot> findProgramSlotByCriteria(PSSearchObject rpso) {
		//to do
		return null;	
	}*/
	
	public ProgramSlot findProgramSlot(int id) {
		ScheduleService service = new ScheduleService();
		return service.findProgramSlotById(id);
		
	}
	public ArrayList<ProgramSlot> findAllProgramSlot() {
		ScheduleService service = new ScheduleService();
		return service.findAllProgramSlot();
		
	}
	
	public void insertProgramSlot(ProgramSlot ps) {
            if (ps!=null){
		ScheduleService service = new ScheduleService();
                service.insertProgramSlot(ps);
            }
	}
	public void updateProgramSlot(ProgramSlot ps) {
		ScheduleService service = new ScheduleService();
		service.updateProgramSlot(ps);
	}
	public void deleteProgramSlot(ProgramSlot ps) {
		ScheduleService service = new ScheduleService();
		service.deleteProgramSlot(ps);
	}
}
