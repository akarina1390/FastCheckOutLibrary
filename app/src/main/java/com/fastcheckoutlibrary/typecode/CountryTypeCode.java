package com.fastcheckoutlibrary.typecode;

public enum CountryTypeCode {
    ARGENTINA (1, "Argentina"),
    BRAZIL (2, "Brazil"),
    CANADA (3, "Canada"),
    COLOMBIA (4, "Colombia"),
    DENMARK (5, "Denmark"),
    DOMINICAN_REPUBLIC (6, "Dominican Republic"),
    ENGLAND (7, "England"),
    GERMANY (8, "Germany"),
    ITALY (9, "Italy"),
    JAPAN (10, "Japan"),
    MEXICO (11, "Mexico"),
    PORTUGAL (12, "Portugal"),
    SPAIN (13, "Spain"),
    UNITED_STATES (14, "United States"),
    VENEZUELA (15, "Venezuela");

    private int _id;
    private String _description;

    CountryTypeCode(int id, String description) {
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
        for ( CountryTypeCode value : CountryTypeCode.values()) {
            if (value.getDescription().equals(description)) {
                return value.getId();
            }
        }
        return "0";
    }
}
