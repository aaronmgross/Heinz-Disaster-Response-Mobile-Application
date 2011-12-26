package ExcelDownload;

/**
 *
 * @author Soundarya R
 */
public class DamageAssmntInformation {

    private String structuralDamage,
            debrisAmount,
            businessInventoryLoss,
            isThereBasement,
            isElectricityOn,
            isGasOn,
            basementOccupied,
            occupiedDescription,
            classificationReason,
            electricalServiceBox,
            furnace,
            hotWaterHeater,
            washer,
            dryer,
            stove,
            refrigerator;
    private int numberOfFloors, waterLevelLivingArea, waterLevelBasement;

    public void setStructuralDamage(String damage) {
        structuralDamage = damage;
    }

    public String getStructuralDamage() {
        return structuralDamage;
    }

    public void setDebrisAmount(String debris) {
        debrisAmount = debris;
    }

    public String getDebrisAmount() {
        return debrisAmount;
    }

    public void setBusinessInventoryLoss(String biz) {
        businessInventoryLoss = biz;
    }

    public String getBusinessInventoryLoss() {
        return businessInventoryLoss;
    }

    public void setNumberOfFloors(int n) {
        numberOfFloors = n;
    }

    public int getnumberOfFloors() {
        return numberOfFloors;
    }

    public void setIsThereBasement(String b) {
        isThereBasement = b;
    }

    public String getIsThereBasement() {
        return isThereBasement;
    }

    public void setWaterLevelLivingArea(int level) {
        waterLevelLivingArea = level;
    }

    public int getWaterLevelLivingArea() {
        return waterLevelLivingArea;
    }

    public void setWaterLevelBasement(int level) {
        waterLevelBasement = level;
    }

    public int getWaterLevelBasement() {
        return waterLevelBasement;
    }

    public void setIsElectricityOn(String e) {
        isElectricityOn = e;
    }

    public String getIsElectricityOn() {
        return isElectricityOn;
    }

    public void setIsGasOn(String on) {
        isGasOn = on;
    }

    public String getIsGasOn() {
        return isGasOn;
    }

    public void setBasementOccupied(String bo) {
        basementOccupied = bo;
    }

    public String getBasementOccupied() {
        return basementOccupied;
    }

    public void setOccupiedDescription(String desc) {
        occupiedDescription = desc;
    }

    public String getOccupiedDescription() {
        return occupiedDescription;
    }

    public void setClassificationReason(String c) {
        classificationReason = c;
    }

    public String getClassificationReason() {
        return classificationReason;
    }

    public void setElectricalServiceBox(String e) {
        electricalServiceBox = e;
    }

    public String getElectricalServiceBox() {
        return electricalServiceBox;
    }

    public void setFurnace(String f) {
        furnace = f;
    }

    public String getFurnace() {
        return furnace;
    }

    public void setHotWaterHeater(String hwh) {
        hotWaterHeater = hwh;
    }

    public String getHotWaterHeater() {
        return hotWaterHeater;
    }

    public void setWasher(String wr) {
        washer = wr;
    }

    public String getWasher() {
        return washer;
    }

    public void setDryer(String dr) {
        dryer = dr;
    }

    public String getDryer() {
        return dryer;
    }

    public void setStove(String s) {
        stove = s;
    }

    public String getstove() {
        return stove;
    }

    public void setRefrigerator(String r) {
        refrigerator = r;
    }

    public String getRefrigerator() {
        return refrigerator;
    }
}
