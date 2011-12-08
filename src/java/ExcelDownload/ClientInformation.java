/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExcelDownload;

/**
 *
 * @author Soundarya R
 */
public class ClientInformation {
    private String address,aptNo  ,
     city ,
     state  ,
     zipCode  ,
     municipality  ,
     county  ,
     lastName ,
     firstName ;
    public void setAddress(String add){
        address=add;
    }
    public String getAddress(){
        return address;
    }
     public void setAptNo(String anum){
        aptNo=anum;
    }
    public String getAptNo(){
        return aptNo;
    }
     public void setCity(String c){
        city=c;
    }
    public String getCity(){
        return city;
    }
     public void setState(String s){
        state=s;
    }
    public String getState(){
        return state;
    }
     public void setZipCode(String zip){
        zipCode=zip;
    }
    public String getZipCode(){
        return zipCode;
    }
     public void setMunicipality(String mn){
         municipality=mn;
    }
    public String getMunicipality(){
        return  municipality;
    }
     public void setCounty(String ct){
        county=ct;
    }
    public String getCounty(){
        return county;
    }
     public void setLastName(String ln){
        lastName=ln;
    }
    public String getLastName(){
        return lastName;
    }
     public void setFirstName(String fn){
        firstName=fn;
    }
    public String getFirstName(){
        return firstName;
    }
   

}
