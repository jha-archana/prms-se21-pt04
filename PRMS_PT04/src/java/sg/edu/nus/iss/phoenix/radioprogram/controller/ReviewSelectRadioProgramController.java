/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.producer.delegate.ProducerDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectRadioProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.utils.URLUtils;

/**
 *
 * @author eain
 */
@WebServlet(name = "ReviewSelectRadioProgramController", urlPatterns = {"/ReviewSelectRadioProgram/*"})
public class ReviewSelectRadioProgramController extends HttpServlet {

   private static final String RSRadioProgram_PREV_URL = "RSRadioPragram_caller_URL";
    private static final String RETURN_KEY = "selectedRadioProgram";
    

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String selection = FCUtilities.stripPath(request.getPathInfo())
				.toLowerCase();
		switch (selection) {
                        //select 
                    case "select":
                        processSelect(request,response);
			break;
                        //search 
                    case "search":
                        processSearch(request, response);
			break;
                        //process review (default)
                    case "review":
                    default:
                        processReview(request, response);
                        break;
		}
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewSelectRadioProgramDelegate rpdel = new ReviewSelectRadioProgramDelegate();
        //direct from search page
        String returnURL = request.getParameter("returnURL");
        if(returnURL == null){
            //from review page
            returnURL = (String) request.getAttribute("returnURL");
        }
        
        String name = request.getParameter("name"); 
        List<RadioProgram> data = new ArrayList<>();
        if(name == null){
            data = rpdel.findAllRadioPrograms();
        }else{
            RadioProgram rp = new RadioProgram();
            rp.setName( name);
             data = rpdel.findAllRadioPrograms(rp);
        }
        
        request.setAttribute("radioprograms", data);
         request.setAttribute("returnURL", returnURL);
        RequestDispatcher rd = request
                        .getRequestDispatcher("/pages/radioprogram/search.jsp");
        rd.forward(request, response);
    }

    private void processSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /**
            * Select the presenter and return to previous page
            */
           ReviewSelectRadioProgramDelegate rpdel = new ReviewSelectRadioProgramDelegate();              
           RadioProgram rp = new RadioProgram();
           Time t=null;
           rp.setName(request.getParameter("name"));
           //rp.setDescription(request.getParameter("desc"));
           ///change String to date for duration
           //String duration = request.getParameter("duration");
           //String dateString = "01-01-50:"+duration+":00";
//           SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:SS");
//           try{
//                 long ms = DATE_FORMAT.parse(duration).getTime();
//                 t=new Time(ms);
//           }catch(Exception e){
//                    e.printStackTrace();
//           }
//           System.out.println("Duration COntroller=="+t.toString());
//           rp.setTypicalDuration(t);
           
           
           
           String returnURL = request.getParameter("returnURL");
           Map<String, String> params = new HashMap<>();
           params.put("radioProgram", rp.getName());
           returnURL = URLUtils.formURL(returnURL, params);
           //RequestDispatcher rd1 = request
             //              .getRequestDispatcher(returnURL);
           System.out.println(" -- return URL --- "+returnURL);
           response.sendRedirect(returnURL);
           
           
    }
    
     /**
     * Process the Review request, it is the default action for ReviewSelectRadioProgram Use Case
     * @param request servlet request
     * @param response  servlet response
     * @throws ServletException
     * @throws IOException 
     */
    private void processReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String returnURL = (String) request.getParameter("returnURL");
        request.setAttribute("returnURL", returnURL);
        processSearch(request,response);
        
    }
}
