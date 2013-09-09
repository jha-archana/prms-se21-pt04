/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;


/**
 *
 * @author Jha Archana
 */
@WebServlet("/ScheduleController/*")
public class ScheduleController extends HttpServlet{
     /**
     * Default constructor. 
     */
    public ScheduleController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
		/*case "process":
			RPDelegate rpdel = new RPDelegate();
			RadioProgram rp = new RadioProgram();
			rp.setName(request.getParameter("name"));
			rp.setDescription(request.getParameter("description"));
			String dur = request.getParameter("typicalDuration");
			System.out.println(rp.toString());
			Time t = Time.valueOf(dur);
			rp.setTypicalDuration(t);
			String ins = (String) request.getParameter("ins");
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Insert Flag: " + ins);
			if (ins.equalsIgnoreCase("true")) {
				rpdel.insertRP(rp);
			} else {
				rpdel.updateRP(rp);
			}
			ArrayList<RadioProgram> data = rpdel.findAllRP();
			request.setAttribute("rps", data);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/crudrp.jsp");
			rd.forward(request, response);
			break;
		case "delete":
			RPDelegate rpdel1 = new RPDelegate();
			String name = request.getParameter("name");
			RadioProgram rp1 = new RadioProgram(name);
			rpdel1.deleteRP(rp1);
			ArrayList<RadioProgram> data1 = rpdel1.findAllRP();
			request.setAttribute("rps", data1);
			RequestDispatcher rd1 = request
					.getRequestDispatcher("/pages/crudrp.jsp");
			rd1.forward(request, response);
			break;*/
		case "loadSched":
			ScheduleDelegate scheDel2 = new ScheduleDelegate();
			ArrayList<ProgramSlot> data2 = scheDel2.findAllProgramSlot();
			//request.setAttribute("rps", data2);
                        request.setAttribute("schd", data2);
			RequestDispatcher rd2 = request
					.getRequestDispatcher("/pages/loadSchedule.jsp");
			rd2.forward(request, response);
			break;
		/*case "search":
			RPDelegate rdel3 = new RPDelegate();
			RPSearchObject search3 = new RPSearchObject();
			ArrayList<RadioProgram> list = new ArrayList<RadioProgram>();
			if (request.getParameter("name") != null
					|| request.getParameter("description") != null) {
				search3.setName(request.getParameter("name"));
				search3.setDescription(request.getParameter("description"));
				list = rdel3.searchPrograms(search3);
			} else {
				list = rdel3.findAllRP();
			}
			request.getSession().setAttribute("searchrplist", list);
			RequestDispatcher rd3 = getServletContext().getRequestDispatcher(
					"/pages/searchrp.jsp");
			;
			rd3.forward(request, response);
			break;*/
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
