package com.partyA.bean;

/**
 * User: Nana Yin
 * Date: 12/5/19
 */
public class GameRecord {
    private int ID;
    private int whiteID;
    private String whiteName;
    private int blackID;
    private String blackName;
    private String result;
    private String beginTime;
    private String endTime;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWhiteID() {
        return whiteID;
    }

    public void setWhiteID(int whiteID) {
        this.whiteID = whiteID;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public void setWhiteName(String whiteName) {
        this.whiteName = whiteName;
    }

    public int getBlackID() {
        return blackID;
    }

    public void setBlackID(int blackID) {
        this.blackID = blackID;
    }

    public String getBlackName() {
        return blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
