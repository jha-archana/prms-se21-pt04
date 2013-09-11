/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.presenter.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterService;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 *
 * @author jiqin
 */
public class PresenterDelegate {
    private PresenterService presenterService;
    
    public PresenterDelegate() {
        presenterService = new PresenterService();
    }
    
    public List<Presenter> findAllPresenters(Presenter example) {
        return presenterService.findAllPresenters(example);
    }
    
    public List<Presenter> findAllPresenters(Presenter example, PaginationCriteria criteria) {
        return presenterService.findAllPresenters(example, criteria);
    }
            
}
