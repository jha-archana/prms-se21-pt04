package sg.edu.nus.iss.phoenix.core.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PhoenixFrontController
 */
//we set the front controller prefix to be app
//@WebServlet(name="front",urlPatterns="/app/*")
public class PhoenixFrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoenixFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DO POST'");
        processRequest(request, response);
    }

    /**
     * Process requests from clients.
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String action = FCUtilities.stripPath(pathInfo);
        String result = chooseUseCase(action);
        System.out.println(result);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(result);
        System.out.println(rd == null);
        rd.forward(request, response);
    }

    private String chooseUseCase(String action) {
        switch (action) {
            case "login":
                return "/LoginController/login";
            case "searchrp":
                return "/ProcessController/search";
            case "searchuser":
                return "/ProcessController/searchuser";
            case "setuprp":
                return "/ProcessController/process";
            case "crudrp":
                return "/CRUDRpController";
            case "loadrp":
                return "/ProcessController/load";
            case "deleterp":
                return "/ProcessController/delete";
            case "loadusr":
                return "/AuthenticateController/load";
            case "setupusr":
                return "/AuthenticateController/process";
            case "loadSchd":
                return "/ScheduleController/loadSched";
            case "logout":
                return "/LoginController/logout";
            default:
                return "/pages/home.jsp";
        }
    }
}
