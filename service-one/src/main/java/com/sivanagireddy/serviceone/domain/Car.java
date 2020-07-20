package com.sivanagireddy.serviceone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Car {
    private Long id;
    private String make;
    private String model;
}
