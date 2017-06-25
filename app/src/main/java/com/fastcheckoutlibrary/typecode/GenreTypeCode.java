package com.fastcheckoutlibrary.typecode;

public enum GenreTypeCode {
    FICTION (1, "Fiction"),
    COMEDY (2, "Comedy"),
    DRAMA (3, "Drama"),
    HORROR (4, "Horror"),
    NON_FICTION (5, "Non Fiction"),
    REALISTIC (6, "Realistic"),
    ROMANTIC (7, "Romantic"),
    SATIRE (8, "Satire"),
    TRAGEDY (9, "Tragedy"),
    TRAGICOMEDY (10, "Tragicomedy"),
    FANTASY (11, "Fantasy"),
    MYTHODOLOGY (12, "Mythodology");

    private int _id;
    private String _description;

    GenreTypeCode(int id, String description) {
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
