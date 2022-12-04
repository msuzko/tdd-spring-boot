package com.mec.tdd.controller;

import com.mec.tdd.exception.CarNotFoundException;
import com.mec.tdd.model.Car;
import com.mec.tdd.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    @GetMapping("/cars/{name}")
    public Car getCar(@PathVariable String name) {
        return carService.getCarDetails(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String carNotFoundException(CarNotFoundException ex) {
        return "Данный автомобиль не обнаружен";
    }

}
