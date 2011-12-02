/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DataDAO.User;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Yao
 */
public class ApproveUser extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
String message;
        String[] c = request.getParameterValues("chbox");
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
        String user = "root";
        String pw = "hello";
        try {
            //database,For MySQL it would be "jdbc:mysql:///<dbname>",Optionally you can pass in a user id, & password
            con = DriverManager.getConnection(connectionStr, user, pw);
            int id;
            User u;
            for (int i = 0; i < c.length; i++) {
                id = Integer.parseInt(c[i]);
                u = new User();
                u.getByID(con, id);
                u.setIsApproved("Y");
                u.update(con, id);
            }
            message = c.length + " users have been approved";
            request.setAttribute("message", message);
            String destination = "/approveuser.jsp";
            RequestDispatcher red = getServletContext().getRequestDispatcher(destination);
            red.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            message = "Errors";
        }
    }
}
