package com.enums;

public enum EDbSqls {
    SELECT_USER_BY_LOGIN("SELECT * FROM TBL_USER WHERE LOGIN = ? LIMIT 1;"),
    SELECT_PASSWORD_BY_USER_ID("SELECT * FROM TBL_PASSWORD WHERE USER_ID_FK = ? ORDER BY PASSWORD_ID DESC LIMIT 1;"),
    SELECT_TOKEN_BY_USER_ID("SELECT * FROM TBL_TOKENS WHERE USER_ID_FK = ? ORDER BY TOKEN_ID DESC LIMIT 1;"),
    INSERT_TOKEN_INTO_DB("INSERT INTO TBL_TOKENS(TOKEN, EXPIRATION_DATE, USER_ID_FK) VALUES(?,?,?);");


    private String query;
    EDbSqls(String query){
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
