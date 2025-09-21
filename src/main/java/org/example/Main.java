package org.example;

import DataBase.Service.HotelDatabaseService;
import Enums.ReservationStatus;
import Service.HotelManagementSystem;
import models.Guest;
import models.Hotel;
import models.Reservation;
import models.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static HotelManagementSystem hotelSystem;
    private static HotelDatabaseService hotelDbService;
    private static Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        hotelSystem = new HotelManagementSystem();
        hotelDbService = new HotelDatabaseService();
        scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("    WELCOME TO HILTON HOTEL CHAIN MANAGEMENT");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    hotelManagementMenu();
                    break;
                case 2:
                    guestManagementMenu();
                    break;
                case 3:
                    roomManagementMenu();
                    break;
                case 4:
                    reservationManagementMenu();
                    break;
                case 5:
                    databaseOperationsMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using Hilton Hotel Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=================== MAIN MENU ===================");
        System.out.println("1. Hotel Management");
        System.out.println("2. Guest Management");
        System.out.println("3. Room Management");
        System.out.println("4. Reservation Management");
        System.out.println("5. Database Operations");
        System.out.println("0. Exit");
        System.out.println("=================================================");
    }

    private static void hotelManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n============== HOTEL MANAGEMENT ==============");
            System.out.println("1. Add New Hotel");
            System.out.println("2. View Hotel Details");
            System.out.println("3. Display All Hotels (from database)");
            System.out.println("0. Back to Main Menu");
            System.out.println("==============================================");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    addHotel();
                    break;
                case 2:
                    viewHotelDetails();
                    break;
                case 3:
                    displayAllHotelsFromDatabase();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addHotel() {
        System.out.println("\n--- Add New Hotel ---");
        Integer hotelId = getIntInput("Enter Hotel ID: ");
        System.out.print("Enter Hotel Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Hotel Location: ");
        String location = scanner.nextLine();

        Hotel hotel = new Hotel();
        hotel.setHotel_id(hotelId);
        hotel.setName(name);
        hotel.setLocation(location);

        hotelSystem.addHotel(hotel);

        if (hotelDbService.createHotel(hotel)) {
            System.out.println("Hotel successfully added to database as well!");
        } else {
            System.out.println("Failed to add hotel to database.");
        }
    }

    private static void viewHotelDetails() {
        Integer hotelId = getIntInput("Enter Hotel ID to view: ");
        hotelSystem.displayHotelDetails(hotelId);
    }

    private static void displayAllHotelsFromDatabase() {
        System.out.println("\n--- All Hotels from Database ---");
        List<Hotel> hotels = hotelDbService.getHotels();
        if (hotels.isEmpty()) {
            System.out.println("No hotels found in database.");
        } else {
            System.out.printf("%-10s %-30s %-30s%n", "Hotel ID", "Name", "Location");
            System.out.println("-----------------------------------------------------------------------");
            for (Hotel hotel : hotels) {
                System.out.printf("%-10s %-30s %-30s%n",
                        hotel.getHotel_id(),
                        hotel.getName(),
                        hotel.getLocation());
            }
        }
    }

    private static void guestManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n============== GUEST MANAGEMENT ==============");
            System.out.println("1. Add New Guest");
            System.out.println("2. View Guest by ID");
            System.out.println("3. Display All Guests");
            System.out.println("0. Back to Main Menu");
            System.out.println("==============================================");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    addGuest();
                    break;
                case 2:
                    viewGuestById();
                    break;
                case 3:
                    displayAllGuests();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addGuest() {
        System.out.println("\n--- Add New Guest ---");
        Integer guestId = getIntInput("Enter Guest ID: ");
        System.out.print("Enter Guest Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Guest Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        Integer hotelId = getIntInput("Enter Hotel ID: ");

        Guest guest = new Guest(guestId, email, name, phoneNumber, hotelId);
        hotelSystem.addGuest(guest);
    }

    private static void viewGuestById() {
        Integer guestId = getIntInput("Enter Guest ID to view: ");
        Guest guest = hotelSystem.getGuestById(guestId);
        if (guest != null) {
            System.out.println("\n--- Guest Details ---");
            System.out.println("Guest ID: " + guest.getGuest_id());
            System.out.println("Name: " + guest.getName());
            System.out.println("Email: " + guest.getEmail());
            System.out.println("Phone: " + guest.getPhone_number());
            System.out.println("Hotel ID: " + guest.getHotel_id());
        } else {
            System.out.println("Guest not found.");
        }
    }

    private static void displayAllGuests() {
        hotelSystem.displayAllGuests();
    }

    private static void roomManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=============== ROOM MANAGEMENT ===============");
            System.out.println("1. Add New Room");
            System.out.println("2. View Room Details");
            System.out.println("3. Display All Rooms");
            System.out.println("4. View Rooms for Specific Hotel");
            System.out.println("0. Back to Main Menu");
            System.out.println("===============================================");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    viewRoomDetails();
                    break;
                case 3:
                    displayAllRooms();
                    break;
                case 4:
                    viewRoomsForHotel();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addRoom() {
        System.out.println("\n--- Add New Room ---");
        System.out.print("Enter Room Number: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Enter Room Type (Single/Double/Suite): ");
        String type = scanner.nextLine();
        boolean isAvailable = getBooleanInput("Is room available? (true/false): ");
        Integer hotelId = getIntInput("Enter Hotel ID: ");

        Room room = new Room(roomNumber, type, isAvailable, hotelId);
        hotelSystem.addRoom(room);
    }

    private static void viewRoomDetails() {
        System.out.print("Enter Room Number to view: ");
        String roomNumber = scanner.nextLine();
        hotelSystem.displayRoomDetails(roomNumber);
    }

    private static void displayAllRooms() {
        System.out.println("\n--- All Rooms ---");
        List<Room> rooms = hotelSystem.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            System.out.printf("%-15s %-15s %-12s %-10s%n", "Room Number", "Type", "Available", "Hotel ID");
            System.out.println("---------------------------------------------------------------");
            for (Room room : rooms) {
                System.out.printf("%-15s %-15s %-12s %-10s%n",
                        room.getRoom_number(),
                        room.getType(),
                        room.isIs_available() ? "Yes" : "No",
                        room.getHotel_id());
            }
        }
    }

    private static void viewRoomsForHotel() {
        Integer hotelId = getIntInput("Enter Hotel ID to view rooms: ");
        List<Room> rooms = hotelSystem.getRoomsForHotel(hotelId);
        if (rooms == null || rooms.isEmpty()) {
            System.out.println("No rooms found for this hotel.");
        } else {
            System.out.printf("%-15s %-15s %-12s%n", "Room Number", "Type", "Available");
            System.out.println("----------------------------------------------");
            for (Room room : rooms) {
                System.out.printf("%-15s %-15s %-12s%n",
                        room.getRoom_number(),
                        room.getType(),
                        room.isIs_available() ? "Yes" : "No");
            }
        }
    }

    private static void reservationManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=========== RESERVATION MANAGEMENT ===========");
            System.out.println("1. Make New Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservation for Hotel");
            System.out.println("0. Back to Main Menu");
            System.out.println("==============================================");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    viewReservationForHotel();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makeReservation() {
        System.out.println("\n--- Make New Reservation ---");
        Integer reservationId = getIntInput("Enter Reservation ID: ");
        Integer guestId = getIntInput("Enter Guest ID: ");
        System.out.print("Enter Room Number: ");
        String roomNumber = scanner.nextLine();
        Integer hotelId = getIntInput("Enter Hotel ID: ");

        LocalDate checkInDate = getDateInput("Enter Check-in Date (yyyy-MM-dd): ");
        LocalDate checkOutDate = getDateInput("Enter Check-out Date (yyyy-MM-dd): ");

        Reservation reservation = new Reservation(
                reservationId,
                guestId,
                roomNumber,
                ReservationStatus.BOOKED,
                checkInDate,
                checkOutDate,
                hotelId
        );

        hotelSystem.makeReservationForRoom(reservation, roomNumber);
    }

    private static void cancelReservation() {
        Integer reservationId = getIntInput("Enter Reservation ID to cancel: ");
        hotelSystem.cancelReservation(reservationId);
    }

    private static void viewReservationForHotel() {
        Integer hotelId = getIntInput("Enter Hotel ID to view reservation: ");
        Reservation reservation = hotelSystem.getReservationForHotel(hotelId);
        if (reservation != null) {
            System.out.println("\n--- Reservation Details ---");
            System.out.println("Reservation ID: " + reservation.getReservation_id());
            System.out.println("Guest ID: " + reservation.getGuest_id());
            System.out.println("Room Number: " + reservation.getRoom_number());
            System.out.println("Check-in Date: " + reservation.getCheckInDate());
            System.out.println("Check-out Date: " + reservation.getCheckOutDate());
            System.out.println("Status: " + reservation.getReservation_status());
            System.out.println("Hotel ID: " + reservation.getHotel_id());
        } else {
            System.out.println("No reservation found for this hotel.");
        }
    }

    private static void databaseOperationsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n============ DATABASE OPERATIONS ============");
            System.out.println("1. Create Hotel in Database");
            System.out.println("2. Get Hotel from Database");
            System.out.println("3. Update Hotel in Database");
            System.out.println("4. Delete Hotel from Database");
            System.out.println("5. List All Hotels from Database");
            System.out.println("0. Back to Main Menu");
            System.out.println("=============================================");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    createHotelInDatabase();
                    break;
                case 2:
                    getHotelFromDatabase();
                    break;
                case 3:
                    updateHotelInDatabase();
                    break;
                case 4:
                    deleteHotelFromDatabase();
                    break;
                case 5:
                    displayAllHotelsFromDatabase();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createHotelInDatabase() {
        System.out.println("\n--- Create Hotel in Database ---");
        Integer hotelId = getIntInput("Enter Hotel ID: ");
        System.out.print("Enter Hotel Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Hotel Location: ");
        String location = scanner.nextLine();

        Hotel hotel = new Hotel();
        hotel.setHotel_id(hotelId);
        hotel.setName(name);
        hotel.setLocation(location);

        if (hotelDbService.createHotel(hotel)) {
            System.out.println("Hotel created successfully in database!");
        } else {
            System.out.println("Failed to create hotel in database.");
        }
    }

    private static void getHotelFromDatabase() {
        Integer hotelId = getIntInput("Enter Hotel ID to retrieve: ");
        Hotel hotel = hotelDbService.getHotel(hotelId);
        if (hotel != null) {
            System.out.println("\n--- Hotel Details from Database ---");
            System.out.println("Hotel ID: " + hotel.getHotel_id());
            System.out.println("Name: " + hotel.getName());
            System.out.println("Location: " + hotel.getLocation());
        } else {
            System.out.println("Hotel not found in database.");
        }
    }

    private static void updateHotelInDatabase() {
        System.out.println("\n--- Update Hotel in Database ---");
        Integer hotelId = getIntInput("Enter Hotel ID to update: ");

        Hotel existingHotel = hotelDbService.getHotel(hotelId);
        if (existingHotel == null) {
            System.out.println("Hotel not found in database.");
            return;
        }

        System.out.print("Enter new Hotel Name (current: " + existingHotel.getName() + "): ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            name = existingHotel.getName();
        }

        System.out.print("Enter new Hotel Location (current: " + existingHotel.getLocation() + "): ");
        String location = scanner.nextLine();
        if (location.trim().isEmpty()) {
            location = existingHotel.getLocation();
        }

        Hotel updatedHotel = new Hotel();
        updatedHotel.setHotel_id(hotelId);
        updatedHotel.setName(name);
        updatedHotel.setLocation(location);

        if (hotelDbService.updateHotel(updatedHotel)) {
            System.out.println("Hotel updated successfully in database!");
        } else {
            System.out.println("Failed to update hotel in database.");
        }
    }

    private static void deleteHotelFromDatabase() {
        Integer hotelId = getIntInput("Enter Hotel ID to delete: ");
        System.out.print("Are you sure you want to delete this hotel? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (hotelDbService.deleteHotel(hotelId)) {
                System.out.println("Hotel deleted successfully from database!");
            } else {
                System.out.println("Failed to delete hotel from database.");
            }
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.nextLine(); // consume invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return input;
    }

    private static boolean getBooleanInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.print("Invalid input. Please enter true/false or yes/no: ");
            }
        }
    }

    private static LocalDate getDateInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                String dateStr = scanner.nextLine();
                return LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.print("Invalid date format. Please use yyyy-MM-dd: ");
            }
        }
    }
}