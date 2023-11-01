package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong(){
        assertThrows(ConstraintViolationException.class, () -> {
            Beer beer = beerRepository.save(Beer.builder().beerName("My Beer is my way to be happy and joyful in live," +
                            " I love all kind of beers and 232323242323232423232324232323242323232423232324")
                    .beerStyle(BeerStyle.IPA)
                    .upc("23232324")
                    .price(new BigDecimal("11.99")).build());
            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer(){
        Beer beer = beerRepository.save(Beer.builder().beerName("My Beer")
                .beerStyle(BeerStyle.IPA)
                .upc("23232324")
                .price(new BigDecimal("11.99")).build());

        beerRepository.flush();

        assertThat(beer).isNotNull();
        assertThat(beer.getId()).isNotNull();
    }
}