package com.site.travel.dao;

import com.site.travel.model.TravelInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TravelDAO {
    private final List<TravelInfo> travelInfo = new ArrayList<>();
    public TravelInfo getTravel(ResultSet resultSet) throws SQLException {
        TravelInfo travel = new TravelInfo();

        travel.setId(resultSet.getString("id"));
        travel.setName(resultSet.getString("name"));
        travel.setDatetime(resultSet.getDate("datetime"));
        travel.setAmount(resultSet.getFloat("amount"));
        travel.setCurrency(resultSet.getString("currency"));
        travel.setDescription(resultSet.getString("description"));

        double x = resultSet.getDouble("x");
        double y = resultSet.getDouble("y");

        TravelInfo.PointRecord point = new TravelInfo.PointRecord(x,y);

        travel.setPoint(point);

        return travel;
        
    }
    public List<TravelInfo> getAllTravelInfo() {
        return travelInfo;
    }
    public Optional<TravelInfo> getTravelInfoById(String id) {
        return travelInfo.stream()
                .filter(travel -> travel.getId().equals(id))
                .findFirst();
    }
    public void addTravelInfo(TravelInfo travel){
        travelInfo.add(travel);
    }
    public void deleteTravelInfo(TravelInfo travel){
        travelInfo.remove(travel);
    }
    public void editName(String id, String name){
        for(TravelInfo travel : travelInfo){
            if(travel.getId().equals(id)){
                travel.setName(name);
                return;
            }
        }
    }
    public void editDateTime(String id, Date datetime){
        for(TravelInfo travel : travelInfo){
            if(travel.getId().equals(id)){
                travel.setDatetime(datetime);
                return;
            }
        }
    }
    public void editAmount(String id, Float amount){
        for(TravelInfo travel : travelInfo){
            if(travel.getId().equals(id)){
                travel.setAmount(amount);
                return;
            }
        }
    }
    public void editCurrency(String id, String currency){
        for(TravelInfo travel : travelInfo){
            if(travel.getId().equals(id)){
                travel.setCurrency(currency);
                return;
            }
        }
    }
    public void editTravelInfo(String id, String description){
        for(TravelInfo travel : travelInfo){
            if(travel.getId().equals(id)){
                travel.setDescription(description);
                return;
            }
        }
    }
}
