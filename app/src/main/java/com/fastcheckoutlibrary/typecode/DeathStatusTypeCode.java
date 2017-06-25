package com.fastcheckoutlibrary.typecode;

public enum  DeathStatusTypeCode {
    DEAD (0, "Dead"),
    ALIVE (1, "Alive");

    private int _id;
    private String _description;

    DeathStatusTypeCode(int id, String description) {
        _id = id;
        _description = description;
    }

    public String getID() {
        return String.valueOf(_id);
    }

    public String getDescription() {
        return _description;
    }
}
