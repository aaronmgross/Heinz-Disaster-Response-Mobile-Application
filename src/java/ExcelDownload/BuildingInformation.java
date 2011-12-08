/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelDownload;

/**
 *
 * @author Soundarya R
 */
public class BuildingInformation {
    private String dwellingType,ownership,insurance,landlordName,contactInfo;
    public void setDwellingType(String dt){
        dwellingType=dt;
    }
    public String getDwellingType(){
        return dwellingType;
    }
    public void setOwnership(String o){
        ownership=o;
    }
    public String getownership(){
        return ownership;
    }
    public void setInsurance(String ins){
        insurance=ins;
    }
    public String getinsurance(){
        return insurance;
    }
    public void setLandlordName(String lName){
        landlordName=lName;
    }
    public String getlandlordName(){
        return landlordName;
    }
    public void setContactInfo(String contact){
        contactInfo=contact;
    }
    public String getcontactInfo(){
        return contactInfo;
    }


}
