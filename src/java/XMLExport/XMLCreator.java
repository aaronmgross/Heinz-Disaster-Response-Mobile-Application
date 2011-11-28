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
import java.util.Date;

public class XMLCreator {

    //No generics
    List myData;
    Document dom;

    public XMLCreator() {

        //create a list to hold the data
        myData = new ArrayList();
        try {
            //initialize the list
            loadData();
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
    private void loadData() throws SQLException {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
        String user = "root";
        String pw = "";
        try {

            con = DriverManager.getConnection(connectionStr, user, pw);
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Cases");
            ResultSet rsAll = stat.executeQuery();
            if (rsAll.next()) {
                Client c = new Client();
                int clientID = rsAll.getInt("Client_Id");
                DataDAO.Client client = new DataDAO.Client();
                DamageAssessment dmg = new DamageAssessment();

                client.getByID(con, clientID);
                dmg.getByID(con, rsAll.getInt("Damage_Assessment_Id"));

                c.setFirstName(client.getfName());
                c.setLastName(client.getlName());
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

                c.setID(clientID + "");
                c.setDisasterAffected("true");
                c.setGender("undetermined");

                myData.add(c);
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
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd'T'hh-mm-ss");

        Element clientEle = dom.createElement("Client");

        if (c.getSourceAgencyID() != null && !c.getSourceAgencyID().equals("")) {
            clientEle.setAttribute("sourceAgencyID", c.getSourceAgencyID());
        }
        if (c.getSourceAgencyName() != null && !c.getSourceAgencyName().equals("")) {
            clientEle.setAttribute("sourceAgencyName", c.getSourceAgencyName());
        }
        dataSetEle.appendChild(clientEle);

        if (c.getID() != null && !c.getID().equals("")) {
            Element idEle = dom.createElement("ID");
            idEle.setAttribute("effective", sdf.format(today));
            idEle.setAttribute("updated", sdf.format(today));
            idEle.setTextContent(c.getID());
            clientEle.appendChild(idEle);
        }

        if (c.getFirstName() != null && !c.getFirstName().equals("")) {
            Element fNameEle = dom.createElement("FirstName");
            fNameEle.setAttribute("updated", sdf.format(today));
            fNameEle.setAttribute("effective", sdf.format(today));
            fNameEle.setTextContent(c.getFirstName());
            clientEle.appendChild(fNameEle);
        }

        if (c.getMiddleName() != null && !c.getMiddleName().equals("")) {
            Element mNameEle = dom.createElement("MiddleName");
            mNameEle.setAttribute("effective", sdf.format(today));
            mNameEle.setAttribute("updated", sdf.format(today));
            mNameEle.setTextContent(c.getMiddleName());
            clientEle.appendChild(mNameEle);
        }

        if (c.getLastName() != null && !c.getLastName().equals("")) {
            Element lNameEle = dom.createElement("LastName");
            lNameEle.setAttribute("effective", sdf.format(today));
            lNameEle.setAttribute("updated", sdf.format(today));
            lNameEle.setTextContent(c.getLastName());
            clientEle.appendChild(lNameEle);
        }


        Element addresses = dom.createElement("Addresses");
        Element address = dom.createElement("Address");
        address.setAttribute("effective", sdf.format(today));
        address.setAttribute("updated", sdf.format(today));
        if (c.getAddressId() != null && !c.getAddressId().equals("")) {
            Element addressId = dom.createElement("ID");
            addressId.setTextContent("1331-1");
            address.appendChild(addressId);
        }
        if (c.getDisasterAffected() != null && !c.getDisasterAffected().equals("")) {
            Element disasterAffected = dom.createElement("DisasterAffected");
            disasterAffected.setTextContent(c.getDisasterAffected());
            address.appendChild(disasterAffected);
        }
        if (c.getAddressLine1() != null && !c.getAddressLine1().equals("")) {
            Element line1 = dom.createElement("Line1");
            line1.setTextContent(c.getAddressLine1());
            address.appendChild(line1);
        }
        if (c.getAddressLine2() != null && !c.getAddressLine2().equals("")) {
            Element line2 = dom.createElement("Line2");
            line2.setTextContent(c.getAddressLine2());
            address.appendChild(line2);
        }
        if (c.getCity() != null && !c.getCity().equals("")) {
            Element city = dom.createElement("City");
            city.setTextContent(c.getCity());
            address.appendChild(city);
        }
        if (c.getCounty() != null && !c.getCounty().equals("")) {
            Element county = dom.createElement("County");
            county.setTextContent(c.getCounty());
            address.appendChild(county);
        }
        if (c.getState() != null && !c.getState().equals("")) {
            Element state = dom.createElement("State");
            state.setTextContent(c.getState());
            address.appendChild(state);
        }
        if (c.getZipcode() != null && !c.getZipcode().equals("")) {
            Element zipcode = dom.createElement("ZipCode");
            zipcode.setTextContent(c.getZipcode());
            address.appendChild(zipcode);
        }
        addresses.appendChild(address);
        clientEle.appendChild(addresses);

        if (c.getPhone() != null && !c.getPhone().equals("")) {
            Element phEle = dom.createElement("BestPhone");
            phEle.setAttribute("effective", sdf.format(today));
            phEle.setAttribute("updated", sdf.format(today));
            phEle.setTextContent(c.getPhone());
            clientEle.appendChild(phEle);
        }
        if (c.getDateOfBirth() != null && !c.getDateOfBirth().equals("")){
            Element dobEle = dom.createElement("DateOfBirth");
            dobEle.setAttribute("effective", sdf.format(today));
            dobEle.setAttribute("updated", sdf.format(today));
            dobEle.setTextContent(c.getDateOfBirth());
            clientEle.appendChild(dobEle);
        }
        if (c.getGender() != null && !c.getGender().equals("")) {
            Element gdrEle = dom.createElement("Gender");
            gdrEle.setAttribute("effective", sdf.format(today));
            gdrEle.setAttribute("updated", sdf.format(today));
            gdrEle.setTextContent(c.getGender());
            clientEle.appendChild(gdrEle);
        }
        if (c.getPreDisasterLivingSituation() != null &&!c.getPreDisasterLivingSituation().equals("")) {
            Element pdlsEle = dom.createElement("PreDisasterLivingSituation");
            pdlsEle.setAttribute("effective", sdf.format(today));
            pdlsEle.setAttribute("updated", sdf.format(today));
            pdlsEle.setTextContent(c.getPreDisasterLivingSituation());
            clientEle.appendChild(pdlsEle);
        }
        if (c.getDmgAssmnt() != null && !c.getDmgAssmnt().equals("")) {
            Element daEle = dom.createElement("DamageAssessment");
            daEle.setAttribute("effective", sdf.format(today));
            daEle.setAttribute("updated", sdf.format(today));
            daEle.setTextContent(c.getDmgAssmnt());
            clientEle.appendChild(daEle);
        }
        if (c.getDmgAsmntOther() != null && !c.getDmgAsmntOther().equals("")) {
            Element daOtherEle = dom.createElement("DamageAssessmentOther");
            daOtherEle.setAttribute("effective", sdf.format(today));
            daOtherEle.setAttribute("updated", sdf.format(today));
            daOtherEle.setTextContent(c.getDmgAsmntOther());
            clientEle.appendChild(daOtherEle);
        }
        if (c.getNeeds() != null && !c.getNeeds().equals("")) {
            Element splNeedsEle = dom.createElement("SpecialNeeds");
            splNeedsEle.setAttribute("effective", sdf.format(today));
            splNeedsEle.setAttribute("updated", sdf.format(today));
            splNeedsEle.setTextContent(c.getNeeds());
            clientEle.appendChild(splNeedsEle);
        }
        if (c.getDisasterName() != null && !c.getDisasterName().equals("")) {
            Element dNameEle = dom.createElement("DisasterName");
            dNameEle.setAttribute("effective", sdf.format(today));
            dNameEle.setAttribute("updated", sdf.format(today));
            dNameEle.setTextContent(c.getDisasterName());
            clientEle.appendChild(dNameEle);
        }
        if (c.getDisasterName() != null && !c.getDisasterName().equals("")) {
            Element dDateEle = dom.createElement("DisasterEventDate");
            dDateEle.setAttribute("effective", sdf.format(today));
            dDateEle.setAttribute("updated", sdf.format(today));
            dDateEle.setTextContent(c.getDisasterDate());
            clientEle.appendChild(dDateEle);
        }

//        Element cases = dom.createElement("Cases");
//        Element _case = dom.createElement("Case");
//        _case.setAttribute("effective", "2009-06-11T00:38:50");
//        _case.setAttribute("updated", "2009-06-11T00:38:50");
//        _case.setAttribute("sourceAgencyID", c.getsourceAgencyID());
//        _case.setAttribute("sourceAgencyName", c.getsourceAgencyName());
//        cases.appendChild(_case);
//        clientEle.appendChild(cases);
//
//        Element servicesNeeded = dom.createElement("ServicesNeeded");
//        service = dom.createElement("ServiceNeeded");
//        service.setAttribute("effective", "2009-06-11T00:38:50");
//        service.setAttribute("updated", "2009-06-11T00:38:50");
//        service.setAttribute("sourceAgencyID", c.getsourceAgencyID());
//        service.setAttribute("sourceAgencyName", c.getsourceAgencyName());
//        serviceId = dom.createElement("ID");
//        serviceId.setTextContent("234423");
//        service.appendChild(serviceId);
//        serviceName = dom.createElement("ServiceName");
//        serviceName.setTextContent("Heater Needed");
//        service.appendChild(serviceName);
//        serviceCode = dom.createElement("ServiceCode");
//        serviceCode.setTextContent("HTR");
//        service.appendChild(serviceCode);
//        serviceDesc = dom.createElement("ServiceDescription");
//        serviceDesc.setTextContent("Hot water heater has been damaged");
//        service.appendChild(serviceDesc);
//        servicesNeeded.appendChild(service);
//        service = dom.createElement("ServiceNeeded");
//        service.setAttribute("effective", "2009-06-11T00:38:50");
//        service.setAttribute("updated", "2009-06-11T00:38:50");
//        service.setAttribute("sourceAgencyID", c.getsourceAgencyID());
//        service.setAttribute("sourceAgencyName", c.getsourceAgencyName());
//        serviceId = dom.createElement("ID");
//        serviceId.setTextContent("234424");
//        service.appendChild(serviceId);
//        serviceName = dom.createElement("ServiceName");
//        serviceName.setTextContent("Stove needed");
//        service.appendChild(serviceName);
//        serviceCode = dom.createElement("ServiceCode");
//        serviceCode.setTextContent("WTR");
//        service.appendChild(serviceCode);
//        serviceDesc = dom.createElement("ServiceDescription");
//        serviceDesc.setTextContent("Client's stove has been damaged");
//        service.appendChild(serviceDesc);
//        servicesNeeded.appendChild(service);
//        clientEle.appendChild(servicesNeeded);

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
                    new FileOutputStream(new File("SampleClientRecord2.xml")), format);

            serializer.serialize(dom);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args){
               //create an instance
        XMLCreator xce = new XMLCreator();

        //run the example
        xce.export();
    }
}
