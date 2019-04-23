package com.my.myapplication.entity;

public class KeyNoteEntity {
    private String num;
    private String title;
    private String utl;

    public KeyNoteEntity(String num,String title, String utl) {
        this.num=num;
        this.title = title;
        this.utl = utl;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUtl() {
        return utl;
    }

    public void setUtl(String utl) {
        this.utl = utl;
    }
}
