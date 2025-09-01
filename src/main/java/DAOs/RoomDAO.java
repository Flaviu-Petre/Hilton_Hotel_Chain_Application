package DAOs;

import DataBase.DataBaseConnection;
import models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    //region Fields
    private DataBaseConnection dbConnection;
    //endregion

    //region Constructors
    public RoomDAO() {
        this.dbConnection = new DataBaseConnection();
    }
    //endregion

    //region Methods
    public boolean createRoom(Room room) {
        String insertSqlQuery = "INSERT INTO rooms (room_number, type, is_available, hotel_id) VALUES (?, ?, ?, ?)";

        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSqlQuery);

            preparedStatement.setString(1, room.getRoom_number());
            preparedStatement.setString(2, room.getType());
            preparedStatement.setBoolean(3, room.isIs_available());
            preparedStatement.setInt(4, room.getHotel_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error creating room: " + e.getMessage());
            return false;
        }
        finally {
            dbConnection.closeConnection(connection);
        }
    }

    public Room getRoom(int room_id) {
        String selectSqlQuery = "SELECT * FROM rooms WHERE room_id = ?";
        Connection connection = null;
        Room room = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);
            preparedStatement.setInt(1, room_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room();
                room.setRoom_number(resultSet.getString("room_number"));
                room.setType(resultSet.getString("type"));
                room.setIs_available(resultSet.getBoolean("is_available"));
                room.setHotel_id(resultSet.getInt("hotel_id"));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println("Error getting room by ID: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return room;
    }

    public List<Room> getAllRooms() {
        String selectSqlQuery = "SELECT * FROM rooms";
        Connection connection = null;
        List<Room> rooms = new ArrayList<>();

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoom_number(resultSet.getString("room_number"));
                room.setType(resultSet.getString("type"));
                room.setIs_available(resultSet.getBoolean("is_available"));
                room.setHotel_id(resultSet.getInt("hotel_id"));
                rooms.add(room);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println("Error getting all rooms: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return rooms;
    }

    public boolean updateRoom(Room room) {
        String updateSqlQuery = "UPDATE rooms SET type = ?, is_available = ?, hotel_id = ? WHERE room_number = ?";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSqlQuery);

            preparedStatement.setString(1, room.getType());
            preparedStatement.setBoolean(2, room.isIs_available());
            preparedStatement.setInt(3, room.getHotel_id());
            preparedStatement.setString(4, room.getRoom_number());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating room: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public boolean deleteRoom(int room_id) {
        String deleteSqlQuery = "DELETE FROM rooms WHERE room_id = ?";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSqlQuery);
            preparedStatement.setInt(1, room_id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting room: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
    //endregion
}
