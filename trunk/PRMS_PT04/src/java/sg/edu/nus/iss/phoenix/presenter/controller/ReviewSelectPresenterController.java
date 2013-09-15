package sg.edu.nus.iss.phoenix.presenter.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import sg.edu.nus.iss.phoenix.presenter.delegate.PresenterDelegate;
import sg.edu.nus.iss.phoenix.presenter.entity.Presenter;
import sg.edu.nus.iss.phoenix.utils.URLUtils;

/**
 * ReviewSelectPresenterController, it will handle the Reivew Select Presenter Use Case
 * @author Wang Jiqin <a0107596@nus.edu.sg>
 * @version 1.0
 * @since 1.0
 */
@WebServlet(name = "ReviewSelectPresenterController", urlPatterns = {"/ReviewSelectPresenter/*"})
public class ReviewSelectPresenterController extends HttpServlet {
    
    private static final String RETURN_KEY = "selectedPresenter";
    
    

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

    /**
     * Process the Search request 
     * @param request servlet request
     * @param response  servlet response
     * @throws ServletException
     * @throws IOException 
     */
    private void processSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PresenterDelegate rpdel = new PresenterDelegate();
        //direct from search page
        String returnURL = request.getParameter("returnURL");
        if(returnURL == null){
            //from review page
            returnURL = (String) request.getAttribute("returnURL");
        }
        String name = request.getParameter("name"); 
        List<Presenter> data = new ArrayList<>();
       if(name == null){
           data = rpdel.findAllPresenters();
       }else{
            Presenter rp = new Presenter();
            rp.setName(name);
            data = rpdel.findAllPresenters(rp);
       }
        request.setAttribute("presenters", data);
        request.setAttribute("returnURL", returnURL);
        RequestDispatcher rd = request.getRequestDispatcher("/pages/presenter/search.jsp");
        rd.forward(request, response);
    }
    
     /**
     * Process the Select request 
     * @param request servlet request
     * @param response  servlet response
     * @throws ServletException
     * @throws IOException 
     */
    private void processSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /**
            * Select the presenter and return to previous page
            */
           PresenterDelegate rpdel = new PresenterDelegate();
           Presenter rp = new Presenter();
           rp.setName(request.getParameter("name"));
           rp.setId(request.getParameter("id"));
           String returnURL = request.getParameter("returnURL");
           Map<String, String> params = new HashMap<>();
           params.put("presenter_id", rp.getId());
           params.put("presenter_name",rp.getName());
           returnURL = URLUtils.formURL(returnURL, params);
           
           response.sendRedirect(returnURL);
           
           
    }
    
     /**
     * Process the Review request, it is the default action for ReviewSelectPresenter Use Case
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
