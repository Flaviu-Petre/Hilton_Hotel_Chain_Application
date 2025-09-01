package DAOs;

import DataBase.DataBaseConnection;
import models.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class GuestDAO {
    //region Fields
    private DataBaseConnection dbConnection;
    //endregion

    //region Constructors
    public GuestDAO() {
        this.dbConnection = new DataBaseConnection();
    }
    //endregion

    //region Methods
    public boolean createGuest(Guest guest){
        String insertSqlQuery = "INSERT INTO guests (guest_id, name, email, phone_number, hotel_id) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSqlQuery);

            preparedStatement.setInt(1, guest.getGuest_id());
            preparedStatement.setString(2, guest.getName());
            preparedStatement.setString(3, guest.getEmail());
            preparedStatement.setString(4, guest.getPhone_number());
            preparedStatement.setInt(5, guest.getHotel_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Error creating guest: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public Guest getGuestById(Integer guest_id){
        String selectSqlQuery = "SELECT * FROM guests WHERE guest_id = ?";
        Connection connection = null;
        Guest guest = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);
            preparedStatement.setInt(1, guest_id);

            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                guest = new Guest(
                        resultSet.getInt("guest_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("hotel_id")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving guest: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return guest;
    }

    public List<Guest> getAllGuests(){
        String selectSqlQuery = "SELECT * FROM guests";
        Connection connection = null;
        List<Guest> guests = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Guest guest = new Guest(
                        resultSet.getInt("guest_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("hotel_id")
                );
                guests.add(guest);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving guests: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return guests;
    }

    public boolean updateGuest(Guest guest){
        String updateSqlQuery = "UPDATE guests SET name = ?, email = ?, phone_number = ?, hotel_id = ? WHERE guest_id = ?";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSqlQuery);

            preparedStatement.setString(1, guest.getName());
            preparedStatement.setString(2, guest.getEmail());
            preparedStatement.setString(3, guest.getPhone_number());
            preparedStatement.setInt(4, guest.getHotel_id());
            preparedStatement.setInt(5, guest.getGuest_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Error updating guest: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public boolean deleteGuest(Integer guest_id){
        String deleteSqlQuery = "DELETE FROM guests WHERE guest_id = ?";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSqlQuery);
            preparedStatement.setInt(1, guest_id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Error deleting guest: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
    //endregion
}
