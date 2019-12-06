package com.partyA.bean;

public class Invitation {
    private String inviter;
    private String invitee;
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
    public String getTime() {
        return time;
    }
    public void setTime(String time) {

        this.time = time;
    }

    public Invitation(String inviter, String invitee, String time) {
        super();
        this.inviter = inviter;
        this.invitee = invitee;
        this.time = time;
    }
    public Invitation() {
        super();
    }

}
