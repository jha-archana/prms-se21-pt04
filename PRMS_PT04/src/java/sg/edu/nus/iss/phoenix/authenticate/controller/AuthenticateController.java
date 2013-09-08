/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;

/**
 *
 * @author erik.pyanto
 */
@WebServlet("/AuthenticateController/*")
public class AuthenticateController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String selection = FCUtilities.stripPath(request.getPathInfo())
                .toLowerCase();
        switch (selection) {
            case "process":
                AuthenticateDelegate adel = new AuthenticateDelegate();
                User u = new User();
                u.setId(request.getParameter("id"));
                u.setName(request.getParameter("name"));
                u.setPassword(request.getParameter("pwd"));

                ArrayList<Role> roleList = new ArrayList<Role>();
                String[] roles = request.getParameterValues("role");
                for (int i = 0; i < roles.length; i++) {
                    Role r = adel.findRole(roles[i].toString());
                    if (r != null) {
                        roleList.add(r);
                    }
                }
                u.setRoles(roleList);
                String ins = (String) request.getParameter("ins");

                String errorMessage = "";
                if (adel.checkUser(u)) {
                    errorMessage = "Error!!!! User exists.";
                }

                Logger.getLogger(getClass().getName()).log(Level.INFO, "Insert Flag: " + ins);
                if (ins.equalsIgnoreCase("true")) {
                    adel.insertUser(u);
                } else {
                    adel.updateUser(u);
                }
                if (errorMessage == "") {
                    request.setAttribute("successMsg", "User saved or updated.");
                } else {
                    request.setAttribute("errorMsg", errorMessage);
                }
                ArrayList<User> data = adel.findAllUser();
                request.setAttribute("usrs", data);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/pages/crudusr.jsp");
                rd.forward(request, response);
                break;
            case "delete":

                break;
            case "load":
                AuthenticateDelegate adel1 = new AuthenticateDelegate();
                ArrayList<User> userList = adel1.findAllUser();
                request.setAttribute("usrs", userList);
                RequestDispatcher rd3 = request
                        .getRequestDispatcher("/pages/crudusr.jsp");
                rd3.forward(request, response);
                break;
            case "search":

                break;
            default:
                AuthenticateDelegate adel2 = new AuthenticateDelegate();
                ArrayList<User> userList2 = adel2.findAllUser();
                request.setAttribute("usrs", userList2);
                RequestDispatcher rd4 = request
                        .getRequestDispatcher("/pages/crudusr.jsp");
                rd4.forward(request, response);
                break;
        }
    }
}
