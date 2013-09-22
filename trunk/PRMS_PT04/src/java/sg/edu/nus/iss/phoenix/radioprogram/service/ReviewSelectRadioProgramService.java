/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.service;

import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.RadioProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 *
 *
 * @author Eain Dra Nilar
 */
public class ReviewSelectRadioProgramService {
    DAOFactoryImpl factory;
    RadioProgramDAO rpdao;
    
    public ReviewSelectRadioProgramService(){
        factory=new DAOFactoryImpl();
        rpdao=factory.getRadioProgramDAO();
    }
    public List<RadioProgram> findAllRadioPrograms(RadioProgram example, PaginationCriteria criteria){
        List<RadioProgram> programs = new ArrayList<>();
        RadioProgram rp = new RadioProgram();
        rp.setName(example.getName());
        rp.setDescription(example.getDescription());
        rp.setTypicalDuration(example.getTypicalDuration());
        try{
            List<RadioProgram> radioProgram = rpdao.searchMatching(rp);
            if(radioProgram!=null){
                for(RadioProgram rpLoop : radioProgram){
                    programs.add(new RadioProgram(rpLoop.getName()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return programs;
    }
    public List<RadioProgram> findAllRadioPrograms(RadioProgram example) {
        return findAllRadioPrograms(example, PaginationCriteria.getDefaultCriteria());
    }
     public List<RadioProgram> findAllRadioPrograms(){
         List<RadioProgram> programs = new ArrayList<>();
        RadioProgram rp = new RadioProgram();
        rp.setName("");
        rp.setDescription("");
        rp.setTypicalDuration(null);
        try{
            List<RadioProgram> rprograms =rpdao.searchMatching(rp);
            if(rprograms!=null){
                for(RadioProgram frp : rprograms){
                    programs.add(new RadioProgram(frp.getName()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return programs;
    }
}
