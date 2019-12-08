package com.partyA.bean;

/**
 * User: Nana Yin
 * Date: 10/30/19
 */
public class Match {
    private int ID;

    private int whiteID;
    private String whiteName;
    private int blackID;
    private String blackName;

    private String result;
    private String startTime;
    private String endTime;


    public Match() {
        super();
    }

    public Match( int blackID, int whiteID) {
        this.blackID = blackID;
        this.whiteID = whiteID;
    }
    public Match(int id,int whiteId,int blackId, String whiteName, String blackName, String result, String startTime, String endTime) {
        super();
        this.ID = id;
        this.whiteID=whiteId;
        this.blackID = blackId;
        this.whiteName=whiteName;
        this.blackName=blackName;
        this.result=result;
        this.startTime=startTime;
        this.endTime=endTime;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
