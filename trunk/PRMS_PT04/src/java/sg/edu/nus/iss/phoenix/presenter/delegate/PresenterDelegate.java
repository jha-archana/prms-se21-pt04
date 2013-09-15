package sg.edu.nus.iss.phoenix.presenter.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterService;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 * Presenter Delegate class
 * @author Wang Jiqin <a0107596@nus.edu.sg>
 * @version 1.0
 * @since  1.0
 */
public class PresenterDelegate {
    
    private PresenterService presenterService;
    
    /**
     * constructor
     */
    public PresenterDelegate() {
        presenterService = new PresenterService();
    }
    
    /**
     * find the presenter by its identifier
     * @param id
     * @return Presetner
     */
    public Presenter findPresenter(String id){
        return presenterService.findPresenter(id);
    }
    
    /**
     * Find all presenters by given an example of Presenter value object
     * @param example, an example of Presenter search criteria to do matching
     * @return List contains matched presenters
     * @since  1.0
     */
    public List<Presenter> findAllPresenters(Presenter example) {
        return presenterService.findAllPresenters(example);
    }
    
    /**
     * Find all presenters by given an example of Presenter value object 
     * and a specification for Pagination criteria
     * @param example , an example of Presenter search criteria to do matching
     * @param criteria, specification for Pagination criteria
     * @return List of Presenters
     * @since 1.0
     */
    public List<Presenter> findAllPresenters(Presenter example, PaginationCriteria criteria) {
        return presenterService.findAllPresenters(example, criteria);
    }
    
    /**
     * Find all the presenters in the database
     * @return List of Presenters
     * @since 1.0
     */
    public List<Presenter> findAllPresenters(){
        return presenterService.findAllPresenters();
    }
            
}
