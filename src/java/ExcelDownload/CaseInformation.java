package ExcelDownload;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Soundarya R
 */
public class CaseInformation {

    private String comments;
    private java.sql.Timestamp startTime,
            completionTime;

    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setComment(String c) {
        comments = c;
    }

    public String getComment() {
        return comments;
    }
}
