
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import XMLExport.XMLCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yao
 */
public class ExportXML extends HttpServlet {

    private String user = null;
    private String pw = null;

    public void init() throws ServletException {

        user = getInitParameter("dbUser");
        pw = getInitParameter("dbPassword");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterApp";
        try {
        //user = "root";
        //pw = "hello";
            //con = DriverManager.getConnection(connectionStr, user, pw);
            con = DriverManager.getConnection(connectionStr, user, pw);
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Cases");
        }
catch (SQLException e) {
            e.printStackTrace();
        }
         String time=request.getParameter("xmlTime");
         
    //create an instance
    XMLCreator xce = new XMLCreator(user,pw,java.sql.Timestamp.valueOf(time));

    //run the example
    xce.export ();
    File f=xce.downloadHelp();
         response.setContentType("text/xml");
        response.setHeader("Content-Disposition", "attachment;filename="+f.getName());

        FileInputStream stream = new FileInputStream(f);
        response.setContentLength(stream.available());
        OutputStream os=response.getOutputStream();
        int read;
         while((read=stream.read())!=-1){
            os.write(read);
        }
        os.close();
}

}
