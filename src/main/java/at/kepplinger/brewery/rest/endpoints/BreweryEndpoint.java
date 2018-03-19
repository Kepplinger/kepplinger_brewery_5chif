package at.kepplinger.brewery.rest.endpoints;

import at.kepplinger.brewery.business.facades.BreweryFacade;
import at.kepplinger.brewery.entity.Brewery;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("brewery")
public class BreweryEndpoint {

    @Inject
    private BreweryFacade breweryFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(breweryFacade.findAll()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(@Valid Brewery brewery) {
        breweryFacade.save(brewery);
        return Response.created(null).build();
    }
}
