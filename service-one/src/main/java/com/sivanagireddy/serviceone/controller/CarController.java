package com.sivanagireddy.serviceone.controller;

import com.sivanagireddy.serviceone.domain.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Value("${car.greeting}")
    private String greeting;

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new Car(id,"skoda", "octavia"));
    }

    @GetMapping("/welcome")
    public ResponseEntity<Map<String, String>> greet() {
        return ResponseEntity.ok(Collections.singletonMap("greeting", greeting));
    }
}
