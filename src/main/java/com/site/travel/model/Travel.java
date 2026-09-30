package com.site.travel.model;

import java.util.ArrayList;
import java.util.List;

public class Travel {
    private List<Travel> travelList = new ArrayList<>();

    public List<Travel> getTravelList() {
        return travelList;
    }
    public void setTravelList(List<Travel> travelList){
        this.travelList = travelList;
    }
}