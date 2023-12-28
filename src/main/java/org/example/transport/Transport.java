package org.example.transport;

public class Transport {

    private String id;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    protected static int count;
    private String manufacturer;
    private int productionYear;
    private int weightKillogram;

    protected Transport(String id, String manufacturer, int productionYear, int weightKillogram) {
        count++;
        this.id = id;
        this.manufacturer = manufacturer;
        this.productionYear = productionYear;
        this.weightKillogram = weightKillogram;
    }
    public Transport(String manufacturer, int productionYear, int weightKillogram) {
        count++;
        this.manufacturer = manufacturer;
        this.productionYear = productionYear;
        this.weightKillogram = weightKillogram;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getWeightKillogram() {
        return weightKillogram;
    }

    public void setWeightKillogram(int weightKillogram) {
        this.weightKillogram = weightKillogram;
    }
}
