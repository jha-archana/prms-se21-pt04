/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.producer.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterService;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.producer.service.ProducerService;
import sg.edu.nus.iss.phoenix.utils.PaginationCriteria;

/**
 *
 * @author jiqin
 */
public class ProducerDelegate {
    private ProducerService producerService;
    
    public ProducerDelegate() {
        producerService = new ProducerService();
    }
    
    public List<Producer> findAllProducers(Producer example) {
        return producerService.findAllProducers(example);
    }
    
    public List<Producer> findAllProducers(Producer example, PaginationCriteria criteria) {
        return producerService.findAllProducers(example, criteria);
    }
}
