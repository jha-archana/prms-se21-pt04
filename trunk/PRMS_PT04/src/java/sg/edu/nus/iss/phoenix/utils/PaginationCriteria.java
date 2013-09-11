/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.utils;

import java.util.List;

/**
 *
 * @author jiqin
 */
public class PaginationCriteria {
    private int pageNo = 1;
    private int numberPerPage = 10;
    private List<String> orders = null;
    
    private static final PaginationCriteria defaultCriteria = new PaginationCriteria();
    
    public PaginationCriteria(){
        
    }
    
    public static PaginationCriteria getDefaultCriteria()
    {
        return defaultCriteria;
    }
}
