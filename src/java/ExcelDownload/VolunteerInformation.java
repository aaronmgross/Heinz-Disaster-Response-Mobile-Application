/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelDownload;

/**
 *
 * @author Soundarya R
 */
public class VolunteerInformation {
    private String lastName,firstName;
    public void setFName(String fn){
        firstName=fn;
    }
    public String getFName(){
        return firstName;
    }
     public void setLName(String ln){
        lastName=ln;
    }
    public String getLName(){
        return lastName;
    }

}
