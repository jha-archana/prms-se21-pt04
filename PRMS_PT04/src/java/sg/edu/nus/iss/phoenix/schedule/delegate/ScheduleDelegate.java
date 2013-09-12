/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RPSearchObject;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author Jha Archana
 */
public class ScheduleDelegate {
    public ArrayList<ProgramSlot> searchSchedule(RPSearchObject rpso) {
		//to do
		return null;	
	}
	
	public ArrayList<ProgramSlot> findProgramSlotByCriteria(RPSearchObject rpso) {
		//to do
		return null;	
	}
	
	public ProgramSlot findProgramSlot(String rpName) {
		//to do
		return null;	
		
	}
	public ArrayList<ProgramSlot> findAllProgramSlot() {
		ScheduleService service = new ScheduleService();
		return service.findAllProgramSlot();
		
	}
	
	public void insertProgramSlot(ProgramSlot rp) {
            if (rp!=null){
		ScheduleService service = new ScheduleService();
                service.insertProgramSlot(rp);
            }
	}
	public void updateProgramSlot(ProgramSlot rp) {
		//to do
	}
	public void deleteProgramSlot(ProgramSlot rp) {
		//to do
	}
}
