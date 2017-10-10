package com.dto;

import java.io.Serializable;
import java.util.Date;

public class TokenDTO implements Serializable {
    private static final long serialVersionUID = -7447591702834574556L;
    private long tokenId;
    private String token;
    private String expirationDate;
    private long userIdFk;

    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(long userIdFk) {
        this.userIdFk = userIdFk;
    }
}
