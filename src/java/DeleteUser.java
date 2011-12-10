
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
public class DeleteUser extends HttpServlet {

    private String user = null;
    private String pw = null;

    @Override
    public void init() throws ServletException {
        user = getInitParameter("dbUser");
        pw = getInitParameter("dbPassword");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String message;
        String[] c = request.getParameterValues("chbox");
        if (c != null) {


            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
            String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
            try {
                con = DriverManager.getConnection(connectionStr, user, pw);
                int id;
                User u;

                for (int i = 0; i < c.length; i++) {
                    id = Integer.parseInt(c[i]);
                    u = new User();
                    u.delete(con, id);
                }
                message = c.length + " users have been deleted";
                request.setAttribute("message", message);
                String destination = "/deleteuser.jsp";
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
}
