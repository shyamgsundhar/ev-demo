package org.example.evdemo.service;

import org.example.evdemo.model.EVData;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EVDataService {

    private final Random random = new Random();

    public EVData generateData() {

        double speed = 20 + random.nextDouble() * 60;

        int battery = 20 + random.nextInt(81);

        double temperature = 30 + random.nextDouble() * 15;

        double latitude =
                10.7905 + (random.nextDouble() - 0.5) * 0.01;

        double longitude =
                78.7047 + (random.nextDouble() - 0.5) * 0.01;

        return new EVData(
                Math.round(speed * 100.0) / 100.0,
                battery,
                Math.round(temperature * 100.0) / 100.0,
                Math.round(latitude * 100000.0) / 100000.0,
                Math.round(longitude * 100000.0) / 100000.0,
                System.currentTimeMillis()
        );
    }
}