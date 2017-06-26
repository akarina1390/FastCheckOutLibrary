package com.fastcheckoutlibrary.typecode;

public enum LibraryTypeCode {
    CALGARY_PUBLIC(1, "Calgary Public Library - Auburn Bay");

    private int _id;
    private String _description;

    LibraryTypeCode(int id, String description) {
        _id = id;
        _description = description;
    }

    public String getId() {
        return String.valueOf(_id);
    }

    public String getDescription() {
        return _description;
    }

    public static String getId(String description) {
        for (LibraryTypeCode value : LibraryTypeCode.values()) {
            if (value.getDescription().equals(description)) {
                return value.getId();
            }
        }
        return "0";
    }
}
