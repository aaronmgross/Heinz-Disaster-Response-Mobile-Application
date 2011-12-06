/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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


    private String user = null;
    private String pw = null;

    public void init() throws ServletException {

		user = getInitParameter("dbUser");
		pw = getInitParameter("dbPassword");
    }

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
        //String user = "root";
        //String pw = "";
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
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
