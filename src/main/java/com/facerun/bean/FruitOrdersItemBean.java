package com.facerun.bean;

import java.io.Serializable;

/**
 * Created by hugo on 2015/9/30 0030.
 */
public class FruitOrdersItemBean extends AbsDomain implements Serializable {

    private int productid;
    private int addressid;
    private int pricetype;
    private int count;
    private String gift;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public int getPricetype() {
        return pricetype;
    }

    public void setPricetype(int pricetype) {
        this.pricetype = pricetype;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }
}
