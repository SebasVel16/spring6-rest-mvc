package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Beer;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BeerService {

    Beer saveBeer(Beer beer);
    Beer getById(UUID id);
    List<Beer> listBeers();

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    void patchById(UUID beerId, Beer beer);
}
