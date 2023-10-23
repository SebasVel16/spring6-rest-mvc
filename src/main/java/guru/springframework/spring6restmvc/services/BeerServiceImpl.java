package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Beer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID,Beer> beers;

    public BeerServiceImpl() {
        this.beers =  new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Shionne")
                .upc("upc")
                .quantityOnHand(1)
                .price(new BigDecimal("12.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Kisara")
                .upc("upc")
                .quantityOnHand(1)
                .price(new BigDecimal("12.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Rinwell")
                .upc("upc")
                .quantityOnHand(1)
                .price(new BigDecimal("12.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        beers.put(beer1.getId(),beer1);
        beers.put(beer2.getId(),beer2);
        beers.put(beer3.getId(),beer3);
    }

    @Override
    public Beer saveBeer(Beer beer) {
        Beer newBeer = Beer.builder()
                .id(UUID.randomUUID())
                .upc(beer.getUpc())
                .beerName(beer.getBeerName())
                .price(beer.getPrice())
                .createdDate(LocalDateTime.now())
                .quantityOnHand(beer.getQuantityOnHand())
                .version(beer.getVersion())
                .updateDate(LocalDateTime.now())
                .build();
        beers.put(newBeer.getId(),newBeer);

        return newBeer;
    }

    @Override
    public Beer getById(UUID id) {
        return beers.get(id);
    }

    @Override
    public List<Beer> listBeers() {
        List<Beer> beerList = new ArrayList<>(beers.values());
        return beerList;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        beer.setId(beerId);
        beer.setUpdateDate(LocalDateTime.now());
        beer.setVersion(beers.get(beerId).getVersion() + 1);
        beer.setCreatedDate(beers.get(beerId).getCreatedDate());
        beers.replace(beerId, beer);
    }

    @Override
    public void deleteById(UUID beerId) {
        beers.remove(beerId);
    }

    @Override
    public void patchById(UUID beerId, Beer beer) {
        Beer existing = beers.get(beerId);

        if(StringUtils.hasText(beer.getBeerName())){

        }
    }
}
