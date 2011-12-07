
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import XMLExport.XMLCreator;

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

    //create an instance
    XMLCreator xce = new XMLCreator(user,pw);

    //run the example
    xce.export ();
}

}
