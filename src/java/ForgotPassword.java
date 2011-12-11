
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataDAO.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Yao
 */
public class ForgotPassword extends HttpServlet {

    private static Connection con;
    private String user = null;
    private String pw_con = null;

    @Override
    public void init() throws ServletException {

        user = getInitParameter("dbUser");
        pw_con = getInitParameter("dbPassword");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String email = request.getParameter("email");
//        User u = null;
//        String password = "";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new AssertionError(e);
//        }
//        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
//
//        try {
//            con = (Connection) DriverManager.getConnection(connectionStr, user, pw_con);
//            u = User.lookup(email, con);
//        } catch (SQLException ex) {
//            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if(u != null){
//            password = u.getPassword();
//        }

        sendEmail("yaoyao19880529@gmail.com", "123");
    }

    private void sendEmail(String toAddress, String pw) {
       		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("disasterassessment","disasterassessment");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("account_update@redcross.org"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toAddress));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
}
