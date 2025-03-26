package org.example.databasegui;

public class DataHolder {
    private final static DataHolder dataHolderInstance = new DataHolder();
    private String text;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return dataHolderInstance;

    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
