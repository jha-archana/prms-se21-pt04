/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.delegate;
import java.util.List;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectRadioProgramService;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 *
 * @author eain
 */
public class ReviewSelectRadioProgramDelegate {
    private ReviewSelectRadioProgramService rpService;
    
    public ReviewSelectRadioProgramDelegate() {
       rpService = new ReviewSelectRadioProgramService();
    }
    
    
    public List<RadioProgram> findAllRadioPrograms(RadioProgram example) {
        return rpService.findAllRadioPrograms(example);
    }
    
    public List<RadioProgram> findAllRadioPrograms(RadioProgram example, PaginationCriteria criteria) {
        return rpService.findAllRadioPrograms(example, criteria);
    }
    
    public List<RadioProgram> findAllRadioPrograms() {
        return rpService.findAllRadioPrograms();
    }
   
}



