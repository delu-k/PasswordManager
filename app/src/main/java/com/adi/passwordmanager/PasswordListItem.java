package com.adi.passwordmanager;

public class PasswordListItem {
    private String pText1;
    private String pText2;

    public PasswordListItem(String t1, String t2) {
        pText1 = t1;
        pText2 = t2;
    }

    public void changeText2(String text) {
        pText2 = text;
    }

    public String getText1() {
        return pText1;
    }

    public String getText2() {
        return pText2;
    }
}
