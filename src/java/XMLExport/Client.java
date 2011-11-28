package XMLExport;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Soundarya R
 */
public class Client {

    private String sourceAgencyID, sourceAgencyName, ID, firstName, middleName, lastName, phone, dateOfBirth,
            gender, preDisasterLivingSituation, dmgAssmnt, dmgAsmntOther, needs, disasterName, disasterDate,
            addressId, disasterAffected, addressLine1, addressLine2, city, county, state, zipcode;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDisasterDate(String disasterDate) {
        this.disasterDate = disasterDate;
    }

    public void setDisasterName(String disasterName) {
        this.disasterName = disasterName;
    }

    public void setDmgAsmntOther(String dmgAsmntOther) {
        this.dmgAsmntOther = dmgAsmntOther;
    }

    public void setDmgAssmnt(String dmgAssmnt) {
        this.dmgAssmnt = dmgAssmnt;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPreDisasterLivingSituation(String preDisasterLivingSituation) {
        this.preDisasterLivingSituation = preDisasterLivingSituation;
    }

    public Client() {
    }
    ;

    public Client(String srcAgcyID, String srcAgcyNm, String id, String fn, String mn, String ln, String ph,
            String dob, String gdr, String pdls, String dAssmnt, String dAssmntOther, String nds, String dName,
            String dDate, String addressId, String disasterAffected, String addressLine1String, String addressLine2,
            String city, String county, String state, String zipcode) {
        sourceAgencyID = srcAgcyID;
        sourceAgencyName = srcAgcyNm;
        ID = id;
        firstName = fn;
        middleName = mn;
        lastName = ln;
        phone = ph;
        dateOfBirth = dob;
        gender = gdr;
        preDisasterLivingSituation = pdls;
        dmgAssmnt = dAssmnt;
        dmgAsmntOther = dAssmntOther;
        needs = nds;
        disasterName = dName;
        disasterDate = dDate;
        this.addressId = addressId;
        this.disasterAffected = disasterAffected;
        this.addressLine1 = addressLine1String;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zipcode = zipcode;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDisasterAffected() {
        return disasterAffected;
    }

    public void setDisasterAffected(String disasterAffected) {
        this.disasterAffected = disasterAffected;
    }

    public String getSourceAgencyID() {
        return sourceAgencyID;
    }

    public void setSourceAgencyID(String sourceAgencyID) {
        this.sourceAgencyID = sourceAgencyID;
    }

    public String getSourceAgencyName() {
        return sourceAgencyName;
    }

    public void setSourceAgencyName(String sourceAgencyName) {
        this.sourceAgencyName = sourceAgencyName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPreDisasterLivingSituation() {
        return preDisasterLivingSituation;
    }

    public String getDmgAssmnt() {
        return dmgAssmnt;
    }

    public String getDmgAsmntOther() {
        return dmgAsmntOther;
    }

    public String getNeeds() {
        return needs;
    }

    public String getDisasterName() {
        return disasterName;
    }

    public String getDisasterDate() {
        return disasterDate;
    }
}
