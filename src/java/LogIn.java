
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataDAO.*;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xue Zhang
 */
public class LogIn extends HttpServlet {

    private static Connection con;
    private String user = null;
    private String pw_con = null;

    /*Get the database user and password from config file*/
    @Override
    public void init() throws ServletException {

        user = getInitParameter("dbUser");
        pw_con = getInitParameter("dbPassword");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {             
        String email = request.getParameter("username");       
        String pw = request.getParameter("password");     
        String disasterType = request.getParameter("Disaster");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
        try {
            con = (Connection) DriverManager.getConnection(connectionStr, user, pw_con);
            User UserInstance = new User();

            List<String> errors = new ArrayList<String>();
            request.setAttribute("errors", errors);


            UserInstance = User.lookup(email, con);
            if (UserInstance == null) {
                errors.add("The e-mail address you entered is not registered. Please enter a valid e-mail address, or use the Register button to create an account.");
            } else if (UserInstance.getIsApproved().equals("N")) {

                errors.add("Your account has not been approved yet by an administrator. Please contact your supervisor for more details.");
            } else if (!User.CheckPassword(email, con, pw)) {
                errors.add("The password you entered was incorrect. Please try again.");
            }

            if (errors.size() > 0) {               
                String destination = "/index.jsp";
                RequestDispatcher red = getServletContext().getRequestDispatcher(destination);
                red.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("userName", UserInstance.getfName() + " " + UserInstance.getlName());
                session.setAttribute("volunteerId", Integer.toString(UserInstance.getUserId()));
                session.setAttribute("loginStatus", "OK");
                session.setAttribute("adminStatus", UserInstance.getRole());
                if(UserInstance.getRole().equals("Admin"))
                    response.sendRedirect("approveuser.jsp");
                else
                response.sendRedirect("welcome.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
