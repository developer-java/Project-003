package com.example.hp.dip.model;

public class Coment {
    private Long id;
    private Long sightId;
    private Long sightcId;
    private String message;
    private String date;

    public Long getSightcId() {
        return sightcId;
    }

    public void setSightcId(Long sightcId) {
        this.sightcId = sightcId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSightId() {
        return sightId;
    }

    public void setSightId(Long sightId) {
        this.sightId = sightId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
