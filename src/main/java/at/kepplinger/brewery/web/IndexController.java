package at.kepplinger.brewery.web;

import at.kepplinger.brewery.business.facades.BeerFacade;
import at.kepplinger.brewery.business.facades.BreweryFacade;
import at.kepplinger.brewery.business.facades.StorageFacade;
import at.kepplinger.brewery.entity.Beer;
import at.kepplinger.brewery.entity.Brewery;
import at.kepplinger.brewery.entity.Storage;

import javax.enterprise.inject.Model;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Model
@RequestScoped
public class IndexController {

    @Inject
    private BeerFacade beerFacade;

    @Inject
    private BreweryFacade breweryFacade;

    @Inject
    private StorageFacade storageFacade;

    public IndexController() {
    }

    public List<Beer> getBeers() {
        return beerFacade.findAll();
    }

    public List<Brewery> getBreweries() {
        return breweryFacade.findAll();
    }

    public List<Storage> getStorages() {
        return storageFacade.findAll();
    }
}
