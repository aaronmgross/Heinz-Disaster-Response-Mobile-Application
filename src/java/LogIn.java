/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author Michelle
 */
public class LogIn extends HttpServlet {

    private static Connection con;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String email = request.getParameter("name");
        String pw = request.getParameter("password");
        String disasterType = request.getParameter("Disaster");
         try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
        //String connectionStr = "jdbc:mysql:///DisasterAssessment";
        String user = "root";
        String pw_con = "";
        try {
            con = (Connection) DriverManager.getConnection(connectionStr,user,pw_con);
            User client = new User();
            client= User.lookup(email, con);
            if(client!=null&&client.getPassword().equals("pw"));

            response.sendRedirect("welcome.html");


//
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here*/
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LogIn</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LogIn at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//
//        } finally {
//            out.close();
//        }
//
    }   catch (IOException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
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
