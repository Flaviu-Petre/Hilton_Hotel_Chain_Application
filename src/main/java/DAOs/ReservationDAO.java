package DAOs;

import DataBase.DataBaseConnection;
import models.Reservation;
import Enums.ReservationStatus;

import java.sql.*;
import java.util.List;

public class ReservationDAO {
    //region Fields
    private DataBaseConnection dbConnection;
    //endregion

    //region Constructors
    public ReservationDAO() {
        this.dbConnection = new DataBaseConnection();
    }
    //endregion

    //region Methods
    public boolean createReservation(Reservation reservation){
        String insertSqlQuery = "INSERT INTO reservations (reservation_id, guest_id, hotel_id, check_in_date, check_out_date, reservation_status) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSqlQuery);

            preparedStatement.setInt(1, reservation.getReservation_id());
            preparedStatement.setInt(2, reservation.getGuest_id());
            preparedStatement.setInt(3, reservation.getHotel_id());
            preparedStatement.setDate(4, Date.valueOf(reservation.getCheckInDate()));
            preparedStatement.setDate(5, Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setString(6, reservation.getReservation_status().toString());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Error creating Reservation: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public Reservation getReservationById(int reservationId) {
        String selectSqlQuery = "SELECT * FROM reservations WHERE reservation_id = ?";
        Connection connection = null;
        Reservation reservation = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);
            preparedStatement.setInt(1, reservationId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                reservation = new Reservation();
                reservation.setReservation_id(resultSet.getInt("reservation_id"));
                reservation.setGuest_id(resultSet.getInt("guest_id"));
                reservation.setHotel_id(resultSet.getInt("hotel_id"));
                reservation.setCheckInDate(resultSet.getDate("check_in_date").toLocalDate());
                reservation.setCheckOutDate(resultSet.getDate("check_out_date").toLocalDate());
                reservation.setReservation_status(ReservationStatus.valueOf(resultSet.getString("reservation_status")));
            }

            resultSet.close();
            preparedStatement.close();

        }catch (Exception e) {
            System.err.println("Error getting reservation by ID: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return reservation;
    }

    public List<Reservation> getAllReservations() {
        String selectSqlQuery = "SELECT * FROM reservations";
        Connection connection = null;
        List<Reservation> reservations = new java.util.ArrayList<>();

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setReservation_id(resultSet.getInt("reservation_id"));
                reservation.setGuest_id(resultSet.getInt("guest_id"));
                reservation.setHotel_id(resultSet.getInt("hotel_id"));
                reservation.setCheckInDate(resultSet.getDate("check_in_date").toLocalDate());
                reservation.setCheckOutDate(resultSet.getDate("check_out_date").toLocalDate());
                reservation.setReservation_status(ReservationStatus.valueOf(resultSet.getString("reservation_status")));
                reservations.add(reservation);
            }

            resultSet.close();
            preparedStatement.close();

        }catch (Exception e) {
            System.err.println("Error getting all reservations: " + e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
        }

        return reservations;
    }

    public boolean updateReservation(Reservation reservation) {
        String updateSqlQuery = "UPDATE reservations SET guest_id = ?, hotel_id = ?, check_in_date = ?, check_out_date = ?, reservation_status = ? WHERE reservation_id = ?";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSqlQuery);

            preparedStatement.setInt(1, reservation.getGuest_id());
            preparedStatement.setInt(2, reservation.getHotel_id());
            preparedStatement.setDate(3, Date.valueOf(reservation.getCheckInDate()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getCheckOutDate()));
            preparedStatement.setString(5, reservation.getReservation_status().toString());
            preparedStatement.setInt(6, reservation.getReservation_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Error updating Reservation: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public boolean deleteReservation(int reservationId) {
        String deleteSqlQuery = "DELETE FROM reservations WHERE reservation_id = ?";
        Connection connection = null;

        try{
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSqlQuery);
            preparedStatement.setInt(1, reservationId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Error deleting Reservation: " + e.getMessage());
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
    //endregion
}
