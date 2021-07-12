package com.oysterkode.laundry.Models;

import java.io.Serializable;

public class StudentPRN implements Serializable
{
    String Date , Total , StudentPRN;
    String pant , shirt ,bedsheet ,towel ,blanket;
    String PillowCover;

    public StudentPRN() {}

    public String getPillowCover() {
        return PillowCover;
    }

    public void setPillowCover(String pillowCover) {
        PillowCover = pillowCover;
    }

    public StudentPRN(String date, String total, String studentPRN, String pant, String shirt, String bedsheet, String towel, String blanket, String pillowCover) {
        this.Date = date;
        this.Total = total;
        this.StudentPRN = studentPRN;
        this.pant = pant;
        this.shirt = shirt;
        this.bedsheet = bedsheet;
        this.towel = towel;
        this.blanket = blanket;
        this.PillowCover = pillowCover;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getStudentPRN() {
        return StudentPRN;
    }

    public void setStudentPRN(String studentPRN) {
        StudentPRN = studentPRN;
    }

    public String getPant() {
        return pant;
    }

    public void setPant(String pant) {
        this.pant = pant;
    }

    public String getShirt() {
        return shirt;
    }

    public void setShirt(String shirt) {
        this.shirt = shirt;
    }

    public String getBedsheet() {
        return bedsheet;
    }

    public void setBedsheet(String bedsheet) {
        this.bedsheet = bedsheet;
    }

    public String getTowel() {
        return towel;
    }

    public void setTowel(String towel) {
        this.towel = towel;
    }

    public String getBlanket() {
        return blanket;
    }

    public void setBlanket(String blanket) {
        this.blanket = blanket;
    }
}
