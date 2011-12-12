/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelDownload;

import java.sql.Date;

/**
 *
 * @author Soundarya R
 */
public class CaseInformation {
    private String comments;
     private java.sql.Date  startTime,
     completionTime ;

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public void setComment(String c){
        comments=c;
    }
    public String getComment(){
        return comments;
    }



}
