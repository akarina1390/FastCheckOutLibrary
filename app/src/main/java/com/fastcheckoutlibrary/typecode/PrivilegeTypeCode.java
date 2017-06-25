package com.fastcheckoutlibrary.typecode;

public enum PrivilegeTypeCode {
    ADMIN (1, "Admin"),
    LIBRARY_USER (2, "Library User");

    private int _id;
    private String _description;

    PrivilegeTypeCode(int id, String description) {
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
