package DAOs;

import DataBase.DataBaseConnection;
import models.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    //region Fields
    private DataBaseConnection dbConnection;
    //endregion

    //region Constructors
    public HotelDAO() {
        this.dbConnection = new DataBaseConnection();
    }
    //endregion

    //region Methods
    public boolean createHotel(Hotel hotel) {
        String insertSqlQuery = "INSERT INTO Hotel (hotel_id, name, location) VALUES (?, ?, ?)";
        Connection connection = null;

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSqlQuery);

            preparedStatement.setInt(1, hotel.getHotel_id());
            preparedStatement.setString(2, hotel.getName());
            preparedStatement.setString(3, hotel.getLocation());

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error creating hotel: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public Hotel getHotelById(Integer hotelId) {
        String selectSqlQuery = "SELECT * FROM Hotel WHERE hotel_id = ?";
        Connection connection = null;
        Hotel hotel = null;

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);
            preparedStatement.setInt(1, hotelId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                hotel = new Hotel();
                hotel.setHotel_id(resultSet.getInt("hotel_id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setLocation(resultSet.getString("location"));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println("Error getting hotel by ID: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return hotel;
    }

    public List<Hotel> getAllHotels() {
        String selectSqlQuery = "SELECT * FROM Hotel";
        Connection connection = null;
        List<Hotel> hotels = new ArrayList<>();

        try {
            connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSqlQuery);

            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotel_id(resultSet.getInt("hotel_id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setLocation(resultSet.getString("location"));

                hotels.add(hotel);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error getting all hotels: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return hotels;
    }

    public boolean updateHotel(Hotel hotel) {
        String updateSqlQuery = "UPDATE Hotel SET name = ?, location = ? WHERE hotel_id = ?";
        Connection connection = null;

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSqlQuery);

            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getLocation());
            preparedStatement.setInt(3, hotel.getHotel_id());

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating hotel: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public boolean deleteHotel(Integer hotelId) {
        String deleteSqlQuery = "DELETE FROM Hotel WHERE hotel_id = ?";
        Connection connection = null;

        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSqlQuery);
            preparedStatement.setInt(1, hotelId);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting hotel: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
    //endregion


}
