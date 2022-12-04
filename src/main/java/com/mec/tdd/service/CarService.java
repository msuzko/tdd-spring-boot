package com.mec.tdd.service;

import com.mec.tdd.exception.CarNotFoundException;
import com.mec.tdd.model.Car;
import com.mec.tdd.service.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.mec.tdd.config.CacheConfig.CARS;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    @Cacheable(CARS)
    public Car getCarDetails(String name) {
        Car car = carRepository.findByName(name);
        if (car == null) {
            throw new CarNotFoundException();
        }
        return car;
    }
}
