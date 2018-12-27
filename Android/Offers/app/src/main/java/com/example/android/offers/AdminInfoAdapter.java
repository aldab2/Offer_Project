package com.example.android.offers;

/**
 * Created by aldab2 on 11/12/18.
 */

public class AdminInfoAdapter {
    String name;
    String normalPriceRange;
    String discountPriceRange;

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

    public String getDiscountPriceRange() {
        return discountPriceRange;
    }

    public void setDiscountPriceRange(String discountPriceRange) {
        this.discountPriceRange = discountPriceRange;
    }

    public AdminInfoAdapter(String name, String normalPriceRange, String discountPriceRange) {
        this.name = name;
        this.normalPriceRange = normalPriceRange;
        this.discountPriceRange = discountPriceRange;
    }
}


