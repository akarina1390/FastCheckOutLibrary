package com.fastcheckoutlibrary.typecode;

public enum GenreTypeCode {
    FICTION(1, "Fiction"),
    COMEDY(2, "Comedy"),
    DRAMA(3, "Drama"),
    HORROR(4, "Horror"),
    NON_FICTION(5, "Non Fiction"),
    REALISTIC(6, "Realistic"),
    ROMANTIC(7, "Romantic"),
    SATIRE(8, "Satire"),
    TRAGEDY(9, "Tragedy"),
    TRAGICOMEDY(10, "Tragicomedy"),
    FANTASY(11, "Fantasy"),
    MYTHOLOGY(12, "Mythology");

    private int _id;
    private String _description;

    GenreTypeCode(int id, String description) {
        _id = id;
        _description = description;
    }

    public String getId() {
        return String.valueOf(_id);
    }

    public static String getID(String description) {
        for (GenreTypeCode value : GenreTypeCode.values()) {
            if (value.getDescription().equals(description)) {
                return value.getId();
            }
        }
        return "0";
    }

    public String getDescription() {
        return _description;
    }
}
