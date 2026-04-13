package com.bridglabz.repository;

import com.bridglabz.model.QuantityMeasurementEntity;
import com.bridglabz.exception.DatabaseException;
import com.bridglabz.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO measurements (value, unit, type) VALUES (?, ?, ?)";

        Connection conn = null;

        try {
            conn = ConnectionPool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, entity.getValue());
            stmt.setString(2, entity.getUnit());
            stmt.setString(3, entity.getType());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Error saving data", e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM measurements";

        Connection conn = null;

        try {
            conn = ConnectionPool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                QuantityMeasurementEntity e = new QuantityMeasurementEntity();
                e.setValue(rs.getDouble("value"));
                e.setUnit(rs.getString("unit"));
                e.setType(rs.getString("type"));

                list.add(e);
            }

        } catch (Exception e) {
            throw new DatabaseException("Error fetching data", e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }

        return list;
    }
}