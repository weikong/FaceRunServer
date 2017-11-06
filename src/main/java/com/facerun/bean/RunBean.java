package com.facerun.bean;

import java.io.Serializable;

/**
 * Created by hugo on 2015/9/30 0030.
 */
public class RunBean extends AbsDomain implements Serializable {

    public int mID;
    public double mLat;
    public double mLon;
    public String mAddress;
    public String mNearBy; //兴趣点
    public String mCity; //城市信息
    public String mDistrict; //城区信息
    public String mRunDate;
    public String mRunDistance;
    public String mRunSpeed;
    public String mUseTime;
    public String mRunHeat;
    public String mRunCoordinateList;
    public String mRunCover;
    public double centerLat;
    public double centerLon;
//    public LatLng centerPoint;

    public boolean isCheck;
    public String runHeadDate;
    public double runHeadDistance;
    public int type;

    public int getType() {
        return type;
    }

    public RunBean setType(int type) {
        this.type = type;
        return this;
    }

    public int getmID() {
        return mID;
    }

    public RunBean setmID(int mID) {
        this.mID = mID;
        return this;
    }

    public double getmLat() {
        return mLat;
    }

    public RunBean setmLat(double mLat) {
        this.mLat = mLat;
        return this;
    }

    public double getmLon() {
        return mLon;
    }

    public RunBean setmLon(double mLon) {
        this.mLon = mLon;
        return this;
    }

    public String getmAddress() {
        return mAddress;
    }

    public RunBean setmAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    public String getmNearBy() {
        return mNearBy;
    }

    public RunBean setmNearBy(String mNearBy) {
        this.mNearBy = mNearBy;
        return this;
    }

    public String getmCity() {
        return mCity;
    }

    public RunBean setmCity(String mCity) {
        this.mCity = mCity;
        return this;
    }

    public String getmRunDate() {
        return mRunDate;
    }

    public RunBean setmRunDate(String mRunDate) {
        this.mRunDate = mRunDate;
        return this;
    }

    public String getmRunDistance() {
        return mRunDistance;
    }

    public RunBean setmRunDistance(String mRunDistance) {
        this.mRunDistance = mRunDistance;
        return this;
    }

    public String getmRunSpeed() {
        return mRunSpeed;
    }

    public RunBean setmRunSpeed(String mRunSpeed) {
        this.mRunSpeed = mRunSpeed;
        return this;
    }

    public String getmUseTime() {
        return mUseTime;
    }

    public RunBean setmUseTime(String mUseTime) {
        this.mUseTime = mUseTime;
        return this;
    }

    public String getmRunHeat() {
        return mRunHeat;
    }

    public RunBean setmRunHeat(String mRunHeat) {
        this.mRunHeat = mRunHeat;
        return this;
    }

    public String getmRunCoordinateList() {
        return mRunCoordinateList;
    }

    public RunBean setmRunCoordinateList(String mRunCoordinateList) {
        this.mRunCoordinateList = mRunCoordinateList;
        return this;
    }

    public String getmRunCover() {
        return mRunCover;
    }

    public RunBean setmRunCover(String mRunCover) {
        this.mRunCover = mRunCover;
        return this;
    }

    public String getmDistrict() {
        return mDistrict;
    }

    public RunBean setmDistrict(String mDistrict) {
        this.mDistrict = mDistrict;
        return this;
    }

//    public LatLng getCenterPoint() {
//        return centerPoint;
//    }
//
//    public RunBean setCenterPoint(LatLng centerPoint) {
//        this.centerPoint = centerPoint;
//        return this;
//    }

    public boolean isCheck() {
        return isCheck;
    }

    public RunBean setCheck(boolean check) {
        isCheck = check;
        return this;
    }

    public String getRunHeadDate() {
        return runHeadDate;
    }

    public RunBean setRunHeadDate(String runHeadDate) {
        this.runHeadDate = runHeadDate;
        return this;
    }

    public double getRunHeadDistance() {
        return runHeadDistance;
    }

    public RunBean setRunHeadDistance(double runHeadDistance) {
        this.runHeadDistance = runHeadDistance;
        return this;
    }

    public double getCenterLat() {
        return centerLat;
    }

    public RunBean setCenterLat(double centerLat) {
        this.centerLat = centerLat;
        return this;
    }

    public double getCenterLon() {
        return centerLon;
    }

    public RunBean setCenterLon(double centerLon) {
        this.centerLon = centerLon;
        return this;
    }
}
