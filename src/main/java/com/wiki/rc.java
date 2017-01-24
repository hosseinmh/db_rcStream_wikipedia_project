package com.wiki;

import java.util.Date;




public class rc {
    char change;
    Date timeStamp;
    public String user; // "RileyBot"
    public Boolean bot; // true
    public String type; // "categorize"
    public long id; // 215733984
    public Object length;

    public rc(char s, long t) {
        change = s;
        timeStamp = new Date(t);
    }
    public char getChange() {return change;}
    public Date getTimeStamp() {return timeStamp;}
    public Boolean getBot() {
        return bot;
    }

    public String getUser() {
        return user;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setLength(Object length) {
        this.length = length;
    }


    @Override
    public String toString() {
        return "Change: " + change + " time: " + timeStamp.toString();
    }
}