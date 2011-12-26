
import ExcelDownload.ExcelCreator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Soundarya R
 */
public class ExportExcel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String time = request.getParameter("excelTime");
            ExcelCreator excel = new ExcelCreator(java.sql.Timestamp.valueOf(time));
            File f = excel.downloadHelp();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + f.getName());

            FileInputStream stream = new FileInputStream(f);
            response.setContentLength(stream.available());
            OutputStream os = response.getOutputStream();
            int read;
            while ((read = stream.read()) != -1) {
                os.write(read);
            }
            os.close();
        } catch (SQLException ex) {
        }
    }
}
