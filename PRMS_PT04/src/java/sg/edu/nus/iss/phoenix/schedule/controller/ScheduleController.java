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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.RPDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

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
                String duration = request.getParameter("duration");
                ps.setDuration(duration);
                String startTime = request.getParameter("startTime");
                ps.setStartTime(startTime);
                String dateOfProgram = request.getParameter("dateOfProgram");
                DateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
                Date date = new Date();
               try{
                date = formatter.parse(dateOfProgram);
               }catch(Exception e){
                   e.printStackTrace();
               }
                //Date d1 = Date.valueOf(dateOfProgram);
                ps.setDateOfProgram(date);
                RPDelegate rpDel = new RPDelegate();
                String programName = request.getParameter("programName");
                RadioProgram rp = rpDel.findRP(programName);
                ps.setRadioProgram(rp);
                AuthenticateDelegate authDel = new AuthenticateDelegate();
                String p1 = request.getParameter("presenter");
                User presenter = authDel.findUser(p1);
                ps.setPresenter(presenter);
                String p2 = request.getParameter("producer");
                User producer = authDel.findUser(p2);
                ps.setProducer(producer);
                
                System.out.println(ps.toString());
                /*if (ins.equalsIgnoreCase("true")) {
                 rpdel.insertRP(rp);
                 } else {
                 rpdel.updateRP(rp);
                 }*/
                scheDel1.insertProgramSlot(ps);
                ArrayList<ProgramSlot> data1 = scheDel1.findAllProgramSlot();
                request.setAttribute("schd", data1);
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
            default:
                ScheduleDelegate scheDel3 = new ScheduleDelegate();
                ArrayList<ProgramSlot> data3 = scheDel3.findAllProgramSlot();
                request.setAttribute("schd", data3);
                RequestDispatcher rd3 = request
                        .getRequestDispatcher("/pages/loadSchedule.jsp");
                rd3.forward(request, response);
                break;
        }

    }
}
