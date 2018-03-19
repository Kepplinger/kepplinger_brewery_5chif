package at.kepplinger.brewery.business;

import at.kepplinger.brewery.business.facades.BeerFacade;
import at.kepplinger.brewery.business.facades.BreweryFacade;
import at.kepplinger.brewery.business.facades.StorageFacade;
import at.kepplinger.brewery.entity.Beer;
import at.kepplinger.brewery.entity.Brewery;
import at.kepplinger.brewery.entity.Storage;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class InitBean {

    @Inject
    private BreweryFacade breweryFacade;

    @Inject
    private BeerFacade beerFacade;

    @Inject
    private StorageFacade storageFacade;

    @PostConstruct
    public void init() {

        System.out.println("===============================================================");

        Brewery brewery1 = new Brewery("Leonding", 540);
        Brewery brewery2 = new Brewery("Linz", 200);

        Beer beer1 = new Beer("Leonding Märzen", "Märzen", 0.5, 0.99);
        Beer beer2 = new Beer("Weiße Freude", "Weizen", 0.5, 1.19);
        Beer beer3 = new Beer("Hopfenmeister", "Pils", 0.3, 0.89);

        Storage storage1 = new Storage(brewery1, beer1, "A34", "Kisten", 1000, 589);
        Storage storage2 = new Storage(brewery1, beer2, "F21", "Kisten", 1250, 679);
        Storage storage3 = new Storage(brewery1, beer3, "G17", "Fässer", 900, 389);

        Storage storage4 = new Storage(brewery2, beer3, "H11", "Fässer", 1900, 839);
        Storage storage5 = new Storage(brewery2, beer2, "X73", "Kisten", 300, 281);
        Storage storage6 = new Storage(brewery2, beer3, "M17", "Fässer", 100, 100);

        System.out.println("Inserting");

        breweryFacade.save(brewery1);
        breweryFacade.save(brewery2);

        beerFacade.save(beer1);
        beerFacade.save(beer2);
        beerFacade.save(beer3);

        storageFacade.save(storage1);
        storageFacade.save(storage2);
        storageFacade.save(storage3);
        storageFacade.save(storage4);
        storageFacade.save(storage5);
        storageFacade.save(storage6);

        System.out.println("===============================================================");

    }
}
