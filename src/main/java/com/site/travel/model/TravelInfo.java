package com.site.travel.model;
import java.util.Date;

public final class TravelInfo {
    String id;
    String name;
    Date datetime;
    Float amount;
    String currency;
    String description;
    private PointRecord point;
    public TravelInfo(){}
    public TravelInfo(String id, String name, Date datetime, Float amount, String currency, String description, PointRecord point) {
        this.id = id;
        this.name = name;
        this.datetime = datetime;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.point = point;
    }
    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Date getDatetime(){
        return datetime;
    }
    public void setDatetime(Date datetime){
        this.datetime = datetime;
    }
    public Float getAmount(){
        return amount;
    }
    public void setAmount(Float amount){
        this.amount = amount;
    }
    public String getCurrency(){
        return currency;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public PointRecord getPoint() {
        return point;
    }
    public void setPoint(PointRecord point) {
        this.point = point;
    }
    public record PointRecord(double x, double y) {

    }
    @Override
    public String toString(){
        return "Travel id=[" + id
                + ", name=" + name
                + ", datetime=" + datetime
                + ", amount=" + amount
                + ", currency=" + currency
                + ", description=" + description
                + ", point=" + point
                + "]";
    }
}
