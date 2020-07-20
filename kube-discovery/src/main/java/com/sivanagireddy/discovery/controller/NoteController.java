package com.sivanagireddy.discovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kube")
public class NoteController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/welcomemsg")
    private ResponseEntity<Map> greet(){
        String welcomeUrl = "http://service-one/cars/welcome";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(welcomeUrl, Map.class);
        return response;
    }

    @GetMapping("/services")
    private ResponseEntity<List<String>> getAvailableServices() {
        List<String> services = discoveryClient.getServices();

        return ResponseEntity.ok(services);
    }
}
