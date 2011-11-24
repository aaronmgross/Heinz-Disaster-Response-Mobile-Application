package XMLExport;


import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Soundarya R
 */
public class ClientDataSet {

    private String namespace;
    private String namespaceXSI;
    private String schemaLocation;
    private String dataSourceName;
    private String generationStamp;
    private String schemaVersion;
    private String taxonomyVersion;
    private String generatorApp;
    private String generatorVendor;

    public ClientDataSet() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd'T'hh-mm-ss");
        
        namespace = "http://www.clientdatastandard.org/dcds/schema/1.1";
        namespaceXSI = "http://www.w3.org/2001/XMLSchema-instance";
        schemaLocation = "http://www.clientdatastandard.org/dcds/schema/1.1 http://www.clientdatastandard.org/dcds/schema/1.1";
        dataSourceName = "can.communityos.org";
        generationStamp = sdf.format(today);
        schemaVersion = "1.1";
        taxonomyVersion = sdf.format(today);
        generatorApp = "DisasterAssessment";
        generatorVendor = "CMU";
    }

    public String getNameSpace() {
        return namespace;
    }

    public String genamespaceXSI() {
        return namespaceXSI;
    }

    public String getschemaLocation() {
        return schemaLocation;
    }

    public String getdataSourceName() {
        return dataSourceName;
    }

    public String getgenerationStamp() {
        return generationStamp;
    }

    public String getschemaVersion() {
        return schemaVersion;
    }

    public String gettaxonomyVersion() {
        return taxonomyVersion;
    }

    public String getgeneratorApp() {
        return generatorApp;
    }

    public String getgeneratorVendor() {
        return generatorVendor;
    }
}
