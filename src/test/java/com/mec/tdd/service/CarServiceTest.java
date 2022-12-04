package com.mec.tdd.service;

import com.mec.tdd.exception.CarNotFoundException;
import com.mec.tdd.model.Car;
import com.mec.tdd.service.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        carService = new CarService(carRepository);
    }

    @Test
    void getCarDetails_returnsCarInfo() {
        given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));

        Car car = carService.getCarDetails("prius");

        assertThat(car.getName()).isEqualTo("prius");
        assertThat(car.getType()).isEqualTo("hybrid");
    }

    @Test
    void getCarDetails_whenCarNotFound() {
        given(carRepository.findByName("prius")).willReturn(null);

        assertThrows(CarNotFoundException.class, ()->carService.getCarDetails("prius"));
    }
}