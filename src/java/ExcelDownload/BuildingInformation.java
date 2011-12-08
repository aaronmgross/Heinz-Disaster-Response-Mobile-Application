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

    private String dwellingType, ownership, landlordName, contactInfo;
    private String insuranceFloor = "";
    private String insuranceStructure = "";
    private String insuranceContents = "";

    public String getInsuranceContents() {
        return insuranceContents;
    }

    public void setInsuranceContents(String insuranceContents) {
        this.insuranceContents = insuranceContents;
    }

    public String getInsuranceFloor() {
        return insuranceFloor;
    }

    public void setInsuranceFloor(String insuranceFloor) {
        this.insuranceFloor = insuranceFloor;
    }

    public String getInsuranceStructure() {
        return insuranceStructure;
    }

    public void setInsuranceStructure(String insuranceStructure) {
        this.insuranceStructure = insuranceStructure;
    }

    public void setDwellingType(String dt) {
        dwellingType = dt;
    }

    public String getDwellingType() {
        return dwellingType;
    }

    public void setOwnership(String o) {
        ownership = o;
    }

    public String getownership() {
        return ownership;
    }

    public void setLandlordName(String lName) {
        landlordName = lName;
    }

    public String getlandlordName() {
        return landlordName;
    }

    public void setContactInfo(String contact) {
        contactInfo = contact;
    }

    public String getcontactInfo() {
        return contactInfo;
    }
}
