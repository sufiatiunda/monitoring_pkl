package com.example.monitoring_pkl.model;

public class ResponseLogin {
    boolean status;
    String massage;
    DataLogin data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public DataLogin getData() {
        return data;
    }

    public void setData(DataLogin data) {
        this.data = data;
    }
}
