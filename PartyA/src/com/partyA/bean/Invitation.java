package com.partyA.bean;

public class Invitation {
    private String inviter;
    private String invitee;
    private String inviterID;
    private String inviteeID;
    private String time;

    public String getInviter() {

        return inviter;
    }
    public void setInviter(String inviter) {

        this.inviter = inviter;
    }
    public String getInvitee() {

        return invitee;
    }
    public void setInvitee(String invitee) {

        this.invitee = invitee;
    }
    public void setInviteeID(String inviteeID){
        this.inviteeID=inviteeID;
    }
    public void setInviterID(String inviterID){
        this.inviterID=inviterID;
    }
    public String getInviteeID(){
        return inviteeID;
    }
    public String getInviterID(){
        return inviterID;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {

        this.time = time;
    }

    public Invitation(String inviter, String invitee, String time, String inviterID, String inviteeID) {
        super();
        this.inviter = inviter;
        this.invitee = invitee;
        this.time = time;
        this.inviterID = inviterID;
        this.inviteeID = inviteeID;
    }
    public Invitation() {
        super();
    }

}
