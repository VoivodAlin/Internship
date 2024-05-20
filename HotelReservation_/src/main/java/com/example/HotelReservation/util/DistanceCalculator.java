package com.example.HotelReservation.util;

public class DistanceCalculator {

    private static final int EARTH_RADIUS = 6371;

    public static double calculateDistance(double startLat, double startLong,
                                           double endLat, double endLong) {

        double dLat = Math.toRadians(endLat - startLat);
        double dLong = Math.toRadians(endLong - startLong);

        double startLatRad = Math.toRadians(startLat);
        double endLatRad = Math.toRadians(endLat);

        double a = haversine(dLat) + Math.cos(startLatRad) * Math.cos(endLatRad) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // Distance in kilometers
    }

    private static double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
