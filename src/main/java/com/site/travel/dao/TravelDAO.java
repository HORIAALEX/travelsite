package com.site.travel.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.site.travel.dbcon.DatabaseConnection;
import com.site.travel.model.TravelInfo;

public class TravelDAO {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    public TravelInfo getTravel(String id) throws SQLException {

        String sql = """
                SELECT id, name, datetime, amount, currency,
                       description, x, y
                FROM travel_info
                WHERE id = ?
                """;

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        return mapResultSetToTravel(resultSet);
                }
            }
        return null;
        }
    }
        private TravelInfo mapResultSetToTravel(ResultSet resultSet) throws SQLException {

            TravelInfo travel = new TravelInfo();

            travel.setId(resultSet.getString("id"));
            travel.setName(resultSet.getString("name"));
            travel.setDatetime(resultSet.getDate("datetime"));
            travel.setAmount(resultSet.getFloat("amount"));
            travel.setCurrency(resultSet.getString("currency"));
            travel.setDescription(resultSet.getString("description"));

            double x = resultSet.getDouble("x");
            double y = resultSet.getDouble("y");

            TravelInfo.PointRecord point =
                    new TravelInfo.PointRecord(x, y);

            travel.setPoint(point);

            return travel;
        }
    }