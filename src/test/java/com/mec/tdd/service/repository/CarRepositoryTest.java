package com.mec.tdd.service.repository;

import com.mec.tdd.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CarRepositoryTest {

    @Autowired
    private CarRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByName_returnCarDetails() {

        var savedCar = entityManager.persistFlushFind(new Car("prius", "hybrid"));
        var car = repository.findByName("prius");

        assertThat(car).isEqualTo(savedCar);
    }
}