package org.example.evdemo.model;

public record EVData(
        double speed,
        int battery,
        double temperature,
        double latitude,
        double longitude,
        long timestamp
) {
}