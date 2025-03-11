package com.Predvca.lab03;

public class Fruit implements Identifiable<Integer> {
    private int id;
    private String name;
    private String variety;
    private String distributor;
    private int quantity;
    private String unit;

    public Fruit(String name, String variety, String distributor, int quantity, String unit) {
        this.name = name;
        this.variety = variety;
        this.distributor = distributor;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Id=" + id + ", Name=" + name + ", Variety=" + variety +
                ", Distributor=" + distributor + ", Quantity=" + quantity +
                ", Unit=" + unit;
    }
}
