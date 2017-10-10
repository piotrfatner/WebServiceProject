package com.security;


import sun.util.calendar.BaseCalendar;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;

public class Security {
    public static Security securityInstance = null;
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";

    private Security(){}

    public static Security getInstance(){
        if(securityInstance == null){
            securityInstance = new Security();
        }
        return securityInstance;
    }

    public String md5(String txt) {
        return getHash(txt);
    }

    public static String generateToken(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
    }

    private String getHash(String txt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            //error action
        }
        return null;
    }

    public Timestamp getTimeStamp(String date)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            Date parsedDate = dateFormat.parse(date);
            return new java.sql.Timestamp(parsedDate.getTime());

        }catch (ParseException e){
            e.getStackTrace();
        }
        return new Timestamp(System.currentTimeMillis());
    }

    public String getExpirationDate(){
        java.sql.Date mySqlDate = java.sql.Date.valueOf(LocalDate.now());
        LocalDate ld =  mySqlDate.toLocalDate();
//Adding a true month is built-in.

        LocalDate monthLater = ld.plusMonths( 1 );
//Convert back to java.sql for storage in the database.

        java.sql.Date sqlDate = java.sql.Date.valueOf( monthLater );
        return sqlDate.toString();
    }
}
