package XMLExport;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Soundarya R
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

//For jdk1.5 with built in xerces parser
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

//For JDK 1.3 or JDK 1.4  with xerces 2.7.1
//import org.apache.xml.serialize.XMLSerializer;
//import org.apache.xml.serialize.OutputFormat;

import DataDAO.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;


public class XMLCreator {

    //No generics
    List myData;
    Document dom;
    File file=new File("ClientRecord.xml");
    Date reqdDate;

    public XMLCreator(String user, String pw,Date time) {

        //create a list to hold the data
        myData = new ArrayList();
        reqdDate=time;
        try {
            //initialize the list
            loadData(user, pw);
        } catch (SQLException ex) {
            System.out.println("Cannot close the JDBC connection");
        }
        //Get a DOM object
        createDocument();
    }

    public void export() {
        System.out.println("Started .. ");
        ClientDataSet c = new ClientDataSet();
        createDOMTree(c);
        printToFile();
        System.out.println("Generated file successfully.");
    }

    /**
     * Add a list of clients to the list
     */
    private void loadData(String user, String pw) throws SQLException {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterApp";
        try {
        user = "root";
        pw = "";
            con = DriverManager.getConnection(connectionStr, user, pw);
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Cases");
            PreparedStatement bldg = con.prepareStatement("Select * from Building");
            ResultSet rsAll = stat.executeQuery();
            ResultSet rsBldg = bldg.executeQuery();
           
            while (rsAll.next() && rsBldg.next()) {
               
               
                if(rsAll.getDate("Start_Time").equals(reqdDate) ||rsAll.getDate("Start_Time").after(reqdDate) ){
                Client c = new Client();

                int clientID = rsAll.getInt("Client_Id");
                int bldgId = rsBldg.getInt("Build_Id");
                int caseID = rsAll.getInt("Case_Id");

                DataDAO.Client client = new DataDAO.Client();
                Building building = new DataDAO.Building();
                Cases cases = new DataDAO.Cases();
                DamageAssessment dmg = new DamageAssessment();

                client.getByID(con, clientID);
                building.getById(con, bldgId);
                cases.getById(con, caseID);
                dmg.getByID(con, rsAll.getInt("Damage_Assessment_Id"));

                c.setFirstName(client.getfName());
                c.setLastName(client.getlName());
                String dwelling = building.getDwellingType();
                
                if ((dwelling.equals("S") || dwelling.equals("A")) && building.getLandlordName().isEmpty()) {
                    c.setPreDisasterLivingSituation("Apartment or house that you own");
                } else if ((dwelling.equals("S") || dwelling.equals("A")) && !building.getLandlordName().isEmpty()) {
                    c.setPreDisasterLivingSituation("Room Apartment or house that you rent");
                } else if (dwelling.equals("M")) {
                    c.setPreDisasterLivingSituation("Other");
                } else {
                    c.setPreDisasterLivingSituation("Undetermined");
                }

                c.setAddressLine1(client.getAddress());
                String aptNo = client.getAptNum();
                if (aptNo != null && !aptNo.equals("")) {
                    c.setAddressLine2("Apt " + aptNo);
                }
                c.setCity(client.getCity());
                c.setState(client.getState());
                c.setCounty(client.getCounty());
                c.setZipcode(client.getZipCode());
                c.setDmgAssmnt(dmg.getStructuralDamage());
                c.setDmgAsmntOther(dmg.getWaterLevelBasement() + " " + dmg.getWaterLevelLivingArea());
                c.setServiceNeeded1("Appliance Damage: Electric box- " + dmg.getElectriccalBox() + ";Furnace- "
                        + dmg.getFurnace()+ ";Washer- " + dmg.getWasher() + ";Heater- " + dmg.getHotWaterHeater() +
                        ";Dryer- "+ dmg.getDryer() + ";Stove- " + dmg.getStove() + ";Refrigerator- " +
                        dmg.getRefrigerator());
                c.setServiceNeeded2(cases.getComment()+" Reason: "+dmg.getReason());
                c.setID(clientID + "");
                c.setDisasterAffected("true");
                c.setGender("Undetermined");

                myData.add(c);

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }


    }

    /**
     * Using JAXP in implementation independent manner create a document object
     * using which we create a xml tree in memory
     */
    private void createDocument() {

        //get an instance of factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //get an instance of builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //create an instance of DOM
            dom = db.newDocument();

        } catch (ParserConfigurationException pce) {
            //dump it
            System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
            System.exit(1);
        }

    }

    /**
     * The real workhorse which creates the XML structure
     */
    private void createDOMTree(ClientDataSet c) {

        Element rootEle = dom.createElement("ClientDataSet");
        dom.appendChild(rootEle);
        rootEle.setAttribute("xmlns", c.getNameSpace());
        rootEle.setAttribute("xmlns:xsi", c.genamespaceXSI());
        rootEle.setAttribute("xsi:schemaLocation", c.getschemaLocation());
        rootEle.setAttribute("dataSourceName", c.getdataSourceName());
        rootEle.setAttribute("generationStamp", c.getgenerationStamp());
        rootEle.setAttribute("schemaVersion", c.getschemaVersion());
        rootEle.setAttribute("taxonomyVersion", c.gettaxonomyVersion());
        rootEle.setAttribute("generatorApp", c.getgeneratorApp());
        rootEle.setAttribute("generatorVendor", c.getgeneratorVendor());
        Element clientDataSetEle = dom.createElement("Clients");

        rootEle.appendChild(clientDataSetEle);

        Iterator it = myData.iterator();
        while (it.hasNext()) {
            Client client = (Client) it.next();
            createClientElement(client, clientDataSetEle);
        }

    }

    /**
     * Helper method which creates a XML element <Book>
     * @param b The book for which we need to create an xml representation
     * @return XML element snippet representing a book
     */
    private Element createClientElement(Client c, Node dataSetEle) {
        Element service, serviceId, serviceName, serviceCode, serviceDesc;
        java.util.Date today = new java.util.Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Element clientEle = dom.createElement("Client");
        clientEle.setAttribute("sourceAgencyID", "");
        clientEle.setAttribute("sourceAgencyName", "");
        dataSetEle.appendChild(clientEle);


        Element idEle = dom.createElement("ID");
        idEle.setAttribute("effective", sdf.format(today));
        idEle.setAttribute("updated", sdf.format(today));
        if (c.getID() != null && !c.getID().equals("")) {
            idEle.setTextContent(c.getID());
        } else {
            idEle.setTextContent("");
        }
        clientEle.appendChild(idEle);

        Element fNameEle = dom.createElement("FirstName");
        fNameEle.setAttribute("updated", sdf.format(today));
        fNameEle.setAttribute("effective", sdf.format(today));
        if (c.getFirstName() != null && !c.getFirstName().equals("")) {
            fNameEle.setTextContent(c.getFirstName());
        } else {
            fNameEle.setTextContent("");
        }
        clientEle.appendChild(fNameEle);

        Element lNameEle = dom.createElement("LastName");
        lNameEle.setAttribute("effective", sdf.format(today));
        lNameEle.setAttribute("updated", sdf.format(today));
        if (c.getLastName() != null && !c.getLastName().equals("")) {
            lNameEle.setTextContent(c.getLastName());
        } else {
            lNameEle.setTextContent("");
        }
        clientEle.appendChild(lNameEle);

        Element addresses = dom.createElement("Addresses");
        Element address = dom.createElement("Address");
        address.setAttribute("effective", sdf.format(today));
        address.setAttribute("updated", sdf.format(today));

        Element addressId = dom.createElement("ID");
        addressId.setTextContent(c.getID() + "-1");
        address.appendChild(addressId);


        Element disasterAffected = dom.createElement("DisasterAffected");
        if (c.getDisasterAffected() != null && !c.getDisasterAffected().equals("")) {
            disasterAffected.setTextContent(c.getDisasterAffected());
        } else {
            disasterAffected.setTextContent("");
        }
        address.appendChild(disasterAffected);


        Element line1 = dom.createElement("Line1");
        if (c.getAddressLine1() != null && !c.getAddressLine1().equals("")) {
            line1.setTextContent(c.getAddressLine1());
        } else {
            line1.setTextContent("");
        }
        address.appendChild(line1);


        Element line2 = dom.createElement("Line2");
        if (c.getAddressLine2() != null && !c.getAddressLine2().equals("")) {
            line2.setTextContent(c.getAddressLine2());
        } else {
            line2.setTextContent("");
        }
        address.appendChild(line2);


        Element city = dom.createElement("City");
        if (c.getCity() != null && !c.getCity().equals("")) {
            city.setTextContent(c.getCity());
        } else {
            city.setTextContent("");
        }
        address.appendChild(city);


        Element county = dom.createElement("County");
        if (c.getCounty() != null && !c.getCounty().equals("")) {
            county.setTextContent(c.getCounty());
        } else {
            county.setTextContent("");
        }
        address.appendChild(county);


        Element state = dom.createElement("State");
        if (c.getState() != null && !c.getState().equals("")) {
            state.setTextContent(c.getState());
        } else {
            state.setTextContent("");
        }
        address.appendChild(state);


        Element zipcode = dom.createElement("ZipCode");
        if (c.getZipcode() != null && !c.getZipcode().equals("")) {
            zipcode.setTextContent(c.getZipcode());
        } else {
            zipcode.setTextContent("");
        }
        address.appendChild(zipcode);

        addresses.appendChild(address);
        clientEle.appendChild(addresses);


        Element phEle = dom.createElement("BestPhone");
        phEle.setAttribute("effective", sdf.format(today));
        phEle.setAttribute("updated", sdf.format(today));
        if (c.getPhone() != null && !c.getPhone().equals("")) {
            phEle.setTextContent(c.getPhone());
        }
        clientEle.appendChild(phEle);


        Element dobEle = dom.createElement("DateOfBirth");
        dobEle.setAttribute("effective", sdf.format(today));
        dobEle.setAttribute("updated", sdf.format(today));
        if (c.getDateOfBirth() != null && !c.getDateOfBirth().equals("")) {
            dobEle.setTextContent(c.getDateOfBirth());
        } else {
            dobEle.setTextContent(dateFormat.format(today));
        }
        clientEle.appendChild(dobEle);


        Element gdrEle = dom.createElement("Gender");
        gdrEle.setAttribute("effective", sdf.format(today));
        gdrEle.setAttribute("updated", sdf.format(today));
        if (c.getGender() != null && !c.getGender().equals("")) {
            gdrEle.setTextContent(c.getGender());
        } else {
            gdrEle.setTextContent("");
        }
        clientEle.appendChild(gdrEle);


        Element pdlsEle = dom.createElement("PreDisasterLivingSituation");
        pdlsEle.setAttribute("effective", sdf.format(today));
        pdlsEle.setAttribute("updated", sdf.format(today));

        if (c.getPreDisasterLivingSituation() != null && !c.getPreDisasterLivingSituation().equals("")) {

            pdlsEle.setTextContent(c.getPreDisasterLivingSituation());
        } else {
            pdlsEle.setTextContent("");
        }
        clientEle.appendChild(pdlsEle);


        Element daEle = dom.createElement("DamageAssessment");
        daEle.setAttribute("effective", sdf.format(today));
        daEle.setAttribute("updated", sdf.format(today));
        if (c.getDmgAssmnt() != null && !c.getDmgAssmnt().equals("")) {
            daEle.setTextContent(c.getDmgAssmnt());
        } else {
            daEle.setTextContent("");
        }
        clientEle.appendChild(daEle);


        Element daOtherEle = dom.createElement("DamageAssessmentOther");
        daOtherEle.setAttribute("effective", sdf.format(today));
        daOtherEle.setAttribute("updated", sdf.format(today));
        if (c.getDmgAsmntOther() != null && !c.getDmgAsmntOther().equals("")) {
            daOtherEle.setTextContent(c.getDmgAsmntOther());
        } else {
            daOtherEle.setTextContent("");
        }
        clientEle.appendChild(daOtherEle);


        Element splNeedsEle = dom.createElement("SpecialNeeds");
        splNeedsEle.setAttribute("effective", sdf.format(today));
        splNeedsEle.setAttribute("updated", sdf.format(today));
        if (c.getNeeds() != null && !c.getNeeds().equals("")) {
            splNeedsEle.setTextContent(c.getNeeds());
        } else {
            splNeedsEle.setTextContent("");
        }
        clientEle.appendChild(splNeedsEle);


        Element dNameEle = dom.createElement("DisasterName");
        dNameEle.setAttribute("effective", sdf.format(today));
        dNameEle.setAttribute("updated", sdf.format(today));
        if (c.getDisasterName() != null && !c.getDisasterName().equals("")) {
            dNameEle.setTextContent(c.getDisasterName());
        } else {
            dNameEle.setTextContent("");
        }
        clientEle.appendChild(dNameEle);


        Element dDateEle = dom.createElement("DisasterEventDate");
        dDateEle.setAttribute("effective", sdf.format(today));
        dDateEle.setAttribute("updated", sdf.format(today));
        if (c.getDisasterDate() != null && !c.getDisasterDate().equals("")) {
            dDateEle.setTextContent(c.getDisasterDate());
        } else {
            dDateEle.setTextContent(dateFormat.format(today));
        }
        clientEle.appendChild(dDateEle);


        Element cases = dom.createElement("Cases");
        Element _case = dom.createElement("Case");
        _case.setAttribute("effective", sdf.format(today));
        _case.setAttribute("updated", sdf.format(today));
        _case.setAttribute("sourceAgencyID", "");
        _case.setAttribute("sourceAgencyName", "");
        cases.appendChild(_case);
        clientEle.appendChild(cases);

        Element servicesNeeded = dom.createElement("ServicesNeeded");
        service = dom.createElement("ServiceNeeded");
        service.setAttribute("effective", sdf.format(today));
        service.setAttribute("updated", sdf.format(today));
        service.setAttribute("sourceAgencyID", " ");
        service.setAttribute("sourceAgencyName", " ");
        serviceId = dom.createElement("ID");
        serviceId.setTextContent("1");
        service.appendChild(serviceId);
        serviceName = dom.createElement("ServiceName");
        serviceName.setTextContent("Appliance Damage");
        service.appendChild(serviceName);
        serviceCode = dom.createElement("ServiceCode");
        serviceCode.setTextContent(" ");
        service.appendChild(serviceCode);
        serviceDesc = dom.createElement("ServiceDescription");
        serviceDesc.setTextContent(c.getServiceNeeded1());
        service.appendChild(serviceDesc);
        servicesNeeded.appendChild(service);
        service = dom.createElement("ServiceNeeded");
        service.setAttribute("effective", sdf.format(today));
        service.setAttribute("updated", sdf.format(today));
        service.setAttribute("sourceAgencyID", " ");
        service.setAttribute("sourceAgencyName", " ");
        serviceId = dom.createElement("ID");
        serviceId.setTextContent("2");
        service.appendChild(serviceId);
        serviceName = dom.createElement("ServiceName");
        serviceName.setTextContent("Comments on Page 3");
        service.appendChild(serviceName);
        serviceCode = dom.createElement("ServiceCode");
        serviceCode.setTextContent(" ");
        service.appendChild(serviceCode);
        serviceDesc = dom.createElement("ServiceDescription");
        serviceDesc.setTextContent(c.getServiceNeeded2());
        service.appendChild(serviceDesc);
        servicesNeeded.appendChild(service);
        clientEle.appendChild(servicesNeeded);

        return clientEle;

    }

    /**
     * This method uses Xerces specific classes
     * prints the XML document to file.
     */
    private void printToFile() {

        try {
            //print
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            //to generate output to console use this serializer
            //XMLSerializer serializer = new XMLSerializer(System.out, format);


            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(file), format);

            serializer.serialize(dom);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public File downloadHelp(){
        return file;
    }
}
