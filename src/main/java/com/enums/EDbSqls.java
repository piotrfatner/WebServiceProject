package com.enums;

public enum EDbSqls {
    SELECT_TOKEN_FOR_USER("SELECT * FROM TBL_USER;");


    private String query;
    EDbSqls(String query){
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
