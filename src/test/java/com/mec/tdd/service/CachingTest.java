package com.mec.tdd.service;

import com.mec.tdd.config.CacheConfig;
import com.mec.tdd.model.Car;
import com.mec.tdd.service.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {CarService.class, CaffeineCacheManager.class})
@Import(CacheConfig.class)
@AutoConfigureTestDatabase
class CachingTest {

    @Autowired
    private CarService carService;
    @MockBean
    private CarRepository carRepository;

    @Test
    void getCar() {
        given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));

        carService.getCarDetails("prius");
        carService.getCarDetails("prius");

        verify(carRepository, times(1)).findByName("prius");
    }
}
