package at.kepplinger.brewery.business.facades;

import at.kepplinger.brewery.entity.Brewery;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BreweryFacade {

    @PersistenceContext
    private EntityManager em;

    public void save(Brewery brewery) {
        em.persist(brewery);
    }

    public List<Brewery> findAll() {
        return em.createQuery("select b from Brewery b").getResultList();
    }
}
