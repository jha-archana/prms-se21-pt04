/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImplTest;
import sg.edu.nus.iss.phoenix.presenter.service.PresenterServiceTest;
import sg.edu.nus.iss.phoenix.producer.service.ProducerServiceTest;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImplTest;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleServiceTest;

/**
 *
 * @author tmswj
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
   UserDaoImplTest.class,
   PresenterServiceTest.class,
   ProducerServiceTest.class,
   ScheduleDAOImplTest.class,
   ScheduleServiceTest.class
})
public class AllTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}