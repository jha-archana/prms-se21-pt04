/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.schedule.entity.PSSearchObject;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author Jeremy
 */
public class PSDelegate {
    public ArrayList<ProgramSlot> searchProgramSlot(PSSearchObject psso) {
		//to do
		return null;	
	}
	
	public ArrayList<ProgramSlot> findProgramSlotByCriteria(PSSearchObject psso) {
		//to do
		return null;	
	}
	
	public ProgramSlot findProgramSlot(String psName) {
		//to do
		return null;	
		
	}
	public ArrayList<ProgramSlot> findAllProgramSlot() {
		ScheduleService service = new ScheduleService();
		return service.findAllProgramSlot();
		
	}
	
	public void insertProgramSlot(ProgramSlot ps) {
		//to do
	}
	public void updateProgramSlot(ProgramSlot ps) {
		//to do
	}
	public void deleteProgramSlot(ProgramSlot ps) {
		//to do
	}
}
