/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.presenter.delegate.PresenterDelegate;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.producer.delegate.ProducerDelegate;
import sg.edu.nus.iss.phoenix.producer.entity.Producer;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.RPDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.PSSearchObject;
import sg.edu.nus.iss.phoenix.utils.SDFUtils;
import sg.edu.nus.iss.phoenix.utils.URLUtils;

/**
 *
 * @author Jha Archana
 */
@WebServlet("/ScheduleController/*")
public class ScheduleController extends HttpServlet {

    /**
     * Default constructor.
     */
    public ScheduleController() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        processRequest(request, response);
    }

    /**
     * Process requests from front controller.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selection = FCUtilities.stripPath(request.getPathInfo())
                .toLowerCase();
        switch (selection) {
            case "setupschedule":
                ScheduleDelegate scheDel1 = new ScheduleDelegate();
                ProgramSlot ps = new ProgramSlot();
                String ins =  request.getParameter("ins");
                String duration = request.getParameter("duration");
                Date durationDt = scheDel1.getDateValueOfString(duration);
                System.out.println("Duration COntroller=="+durationDt);
                ps.setDuration(durationDt);
                String startTime = request.getParameter("startTime");
                Date startTimeDt = scheDel1.getDateValueOfString(startTime);
                ps.setStartTime(startTimeDt);
                String dateOfProgram = request.getParameter("dateOfProgram");
                ps.setDateOfProgram(dateOfProgram);
                RPDelegate rpDel = new RPDelegate();
                String programName = request.getParameter("radioProgram");
                RadioProgram rp = rpDel.findRP(programName);
                ps.setRadioProgram(rp);
                PresenterDelegate pDel = new PresenterDelegate();
                String p1_id = request.getParameter("presenterId");
                String p1_name = request.getParameter("presenterName");
                Presenter presenter = pDel.findPresenter(p1_id);
                ps.setPresenter(presenter);
                String p2_id = request.getParameter("producerId");
                String p2_name = request.getParameter("producerName");
                ProducerDelegate prDel = new ProducerDelegate();
                Producer producer = prDel.findProducer(p2_id);
                ps.setProducer(producer);
                String psId = "";
                 psId = request.getParameter("id");
               String errorMessage = "";
                if(!scheDel1.checkConflict(ps,psId)){
                        if ("true".equalsIgnoreCase(ins)) {
                          scheDel1.insertProgramSlot(ps);
                      } else {
                         ps.setId(Integer.parseInt(psId));
                         scheDel1.updateProgramSlot(ps);
                      } 
                }else{
                    errorMessage="Schedule not Saved ! Conflicted with other Programs !!!";
                }
                if (errorMessage.equals("")) {
                    request.setAttribute("successMsg", "Schedule Saved Successfully");
                } else {
                    request.setAttribute("errorMsg", errorMessage);
                }
                ArrayList<ProgramSlot> data1 = scheDel1.findAllProgramSlot();
                request.setAttribute("schd", data1);
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/pages/loadSchedule.jsp");
                rd.forward(request, response);
                break;
            case "loadSched":
                ScheduleDelegate scheDel2 = new ScheduleDelegate();
                ArrayList<ProgramSlot> data2 = scheDel2.findAllProgramSlot();
                request.setAttribute("schd", data2);
                RequestDispatcher rd2 = request
                        .getRequestDispatcher("/pages/loadSchedule.jsp");
                rd2.forward(request, response);
                break;
            case "searchsched":
                 ScheduleDelegate scheddel = new ScheduleDelegate();
                 PSSearchObject ssearch = new PSSearchObject();
                 RPDelegate rpDel2 = new RPDelegate();
                 ArrayList<ProgramSlot> schedlist = new ArrayList<ProgramSlot>();
                 if ((request.getParameter("date") != null && !"".equals(request.getParameter("date").trim())) || 
                         (request.getParameter("time") != null && !"".equals(request.getParameter("time").trim())) ||
                         (request.getParameter("prog") != null && !"".equals(request.getParameter("prog").trim()))) {
                         ssearch.setStartTime(request.getParameter("time"));
                         ssearch.setDateOfProgram(request.getParameter("date"));
                         ssearch.setRadioProgramName(request.getParameter("prog"));
                         schedlist = scheddel.searchProgramSlot(ssearch);                        
                 } else {
                         schedlist = scheddel.findAllProgramSlot();
                 }
                 request.getSession().setAttribute("searchschedlist", schedlist);
                 RequestDispatcher ss = getServletContext().getRequestDispatcher(
                                 "/pages/searchsched.jsp");
                 ;
                 ss.forward(request, response);
                 break;
               case "deleteschedule":
                 ScheduleDelegate psDelete = new ScheduleDelegate();
                 Integer id = Integer.parseInt(request.getParameter("id").toString()) ;
                  ProgramSlot psDel=  psDelete.findProgramSlot(id) ;
                 psDelete.deleteProgramSlot(psDel);
                 
                ScheduleDelegate scheDel3 = new ScheduleDelegate();
                ArrayList<ProgramSlot> data3 = scheDel3.findAllProgramSlot();
                request.setAttribute("schd", data3);
                RequestDispatcher rd3 = request
                        .getRequestDispatcher("/pages/loadSchedule.jsp");
                rd3.forward(request, response);
               
               
                break;
            default:
                ScheduleDelegate scheDel4 = new ScheduleDelegate();
                ArrayList<ProgramSlot> data4 = scheDel4.findAllProgramSlot();
                request.setAttribute("schd", data4);
                RequestDispatcher rd4 = request
                        .getRequestDispatcher("/pages/loadSchedule.jsp");
                rd4.forward(request, response);
                break;
        }

    }
}
