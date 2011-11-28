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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michelle
 */
public class LogIn extends HttpServlet {

    private static Connection con;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("username");
        System.out.println("username:"+ email);
        String pw = request.getParameter("password");
        System.out.println("password:"+ pw);
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
            
            //System.out.println("password from db:"+client.getPassword());
            List<String> errors = new ArrayList<String>();
            request.setAttribute("errors",errors);

            client= User.lookup(email, con);
            if (client == null) {
	            errors.add("Email not found");
	            String destination = "/index.jsp";

            RequestDispatcher red = getServletContext().getRequestDispatcher(destination);

            red.forward(request, response);
	        }

            else if(client.getPassword().equals(pw))
            {
              request.setAttribute("email", email);

              HttpSession session = request.getSession();
              session.setAttribute("userName", client.getfName()+" "+client.getlName());

              response.sendRedirect("welcome.jsp");
            }


            else{
            errors.add("wrong password!");
            request.setAttribute("errors", errors);

            if (errors != null) {
                                for (int i = 0; i < errors.size(); i++) {
                                    System.out.println(errors.get(i));
                                }

            String destination = "/index.jsp";

            RequestDispatcher red = getServletContext().getRequestDispatcher(destination);

            red.forward(request, response);

            //response.sendRedirect("login.html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LogIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogIn Failed!</h1>");
            out.println("</body>");
            out.println("</html>");
            }
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
