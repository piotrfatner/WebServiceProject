package com.dto;

import java.io.Serializable;

public class BookDTO implements Serializable {
    private static final long serialVersionUID = -744723402866274446L;
    private String dateOfHire;
    private String statusOfHire;
    private String title;
    private String author;
    private String iSBN;
    private String bookGenre;

    public String getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(String dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public String getStatusOfHire() {
        return statusOfHire;
    }

    public void setStatusOfHire(String statusOfHire) {
        this.statusOfHire = statusOfHire;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }
}
