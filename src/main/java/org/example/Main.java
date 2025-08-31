package org.example;

import Interfaces.HotelServiceInterface;
import Repository.HotelRepository;
import Service.HotelServiceImpl;
import models.Hotel;

public class Main {
    public static void main(String[] args) {
        HotelRepository hotelRepository = new HotelRepository();
        HotelServiceInterface hotelService = new HotelServiceImpl(hotelRepository);
        
    }
}