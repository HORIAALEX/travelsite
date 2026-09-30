package com.site.travel.dao;

import com.site.travel.model.TravelInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
}
