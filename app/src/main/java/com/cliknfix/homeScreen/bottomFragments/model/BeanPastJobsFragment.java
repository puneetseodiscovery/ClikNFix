package com.cliknfix.homeScreen.bottomFragments.model;

public class BeanPastJobsFragment {

    String status,category,date;
    int techImg;

    public BeanPastJobsFragment(String status,String category,String date ,int techImg) {
        this.status = status;
        this.category = category;
        this.date = date;
        this.techImg = techImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTechImg() {
        return techImg;
    }

    public void setTechImg(int techImg) {
        this.techImg = techImg;
    }



}
