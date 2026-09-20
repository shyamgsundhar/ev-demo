package org.example.evdemo.controller;

import org.example.evdemo.model.EVData;
import org.example.evdemo.service.EVDataService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class EVStreamController {

    private final EVDataService evDataService;

    public EVStreamController(EVDataService evDataService) {
        this.evDataService = evDataService;
    }

    @GetMapping(
            value = "/api/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public SseEmitter stream() {

        SseEmitter emitter = new SseEmitter(0L);

        Thread.startVirtualThread(() -> {

            try {

                while (true) {

                    EVData data = evDataService.generateData();

                    emitter.send(
                            SseEmitter.event()
                                    .name("ev-data")
                                    .data(data)
                    );

                    Thread.sleep(1000);
                }

            } catch (Exception e) {

                emitter.complete();
            }
        });

        return emitter;
    }
}