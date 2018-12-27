package com.example.android.offers;


import java.sql.Date;

public class OffersInfoAdapter {

    private String name;
    private String normalPriceRange;
    private String discountPrice;
    private Date startDate;
    private Date endDate;
    private String deviceDescription;
    private String color;
    private String brand;
    private String company;
    private String type;
    private int image = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OffersInfoAdapter(String name, String normalPriceRange, String discountPrice, Date startDate, Date endDate, String deviceDescription, String color, String brand, String company) {
        this.name = name;
        this.normalPriceRange = normalPriceRange;
        this.discountPrice = discountPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deviceDescription = deviceDescription;
        this.color = color;
        this.brand = brand;
        this.company= company;
        type = "E";
    }

    public OffersInfoAdapter(String name, String normalPriceRange, String discountPrice, Date startDate, Date endDate, String company) {
        this.name = name;
        this.normalPriceRange = normalPriceRange;
        this.discountPrice = discountPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
        type = "F";
    }

    public OffersInfoAdapter( OffersInfoAdapter other) {
        this.name = other.name;
        this.normalPriceRange = other.normalPriceRange;
        this.discountPrice = other.discountPrice;
        this.startDate = other.startDate;
        this.endDate = other.endDate;
        this.deviceDescription = other.deviceDescription;
        this.color = other.color;
        this.brand = other.brand;
        this.company = other.company;
        this.type = other.type;
        this.image = other.image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalPriceRange() {
        return normalPriceRange;
    }

    public void setNormalPriceRange(String normalPriceRange) {
        this.normalPriceRange = normalPriceRange;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public boolean hasImage(){
        return image!=NO_IMAGE_PROVIDED;
    }
}
