package com.fastcheckoutlibrary.typecode;

public enum  GenderTypeCode {
    MALE (1, "Male"),
    FEMALE (2, "Female");

    private int _id;
    private String _description;

    GenderTypeCode(int id, String description) {
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
