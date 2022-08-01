package com.whatap.SpringLambda.Domain;

public class Email {
    private final String SENDER = "WhaTap Service <service@whatap.io>";
    private String Title =  "SPRING+LAMBDA TEST EMAIL";
    private String BODY_TEXT = "SPRING+LAMBDA TEST EMAIL";
    private String Carbon_copy="mayf@whatap.io";

    public Email(String title, String BODY_TEXT, String carbon_copy) {
        Title = title;
        this.BODY_TEXT = BODY_TEXT;
        Carbon_copy = carbon_copy;
    }

    public String getSENDER() {
        return SENDER;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBODY_TEXT() {
        return BODY_TEXT;
    }

    public void setBODY_TEXT(String BODY_TEXT) {
        this.BODY_TEXT = BODY_TEXT;
    }

    public String getCarbon_copy() {
        return Carbon_copy;
    }

    public void setCarbon_copy(String carbon_copy) {
        Carbon_copy = carbon_copy;
    }
}
