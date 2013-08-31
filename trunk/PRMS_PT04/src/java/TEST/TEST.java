/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author A0107605M
 */
@WebServlet(name = "TEST", urlPatterns = {"/TEST"})
public class TEST extends HttpServlet {

    @Resource(mappedName = "jdbc/PrmsDatasource")    DataSource ds;
  

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
        //processRequest(request, response);
        List<String> result = new LinkedList<>();
        
        try(Connection conn = ds.getConnection()){
            Statement stat = conn. createStatement();
            ResultSet rs = stat.executeQuery("select * from APP.USERINFO");
            
            while(rs.next())
            {
                result.add(rs.getString("UserName"));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        
    
    try(PrintWriter pw = response.getWriter()){
pw.println("<html><body><ul>");
for(String s : result)
{
    pw.println("<li>" + s + "</li>");
}
pw.println("</ul></body></html>");
}
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
        //processRequest(request, response);
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
}
