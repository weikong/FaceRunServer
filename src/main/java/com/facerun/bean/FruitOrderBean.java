package com.facerun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hugo on 2015/9/30 0030.
 */
public class FruitOrderBean extends AbsDomain implements Serializable {

    private int account_id;
    private int addressid;
    private List<FruitOrdersItemBean> fruits;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getAddressid() {
        return addressid;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public List<FruitOrdersItemBean> getFruits() {
        return fruits;
    }

    public void setFruits(List<FruitOrdersItemBean> fruits) {
        this.fruits = fruits;
    }
}
