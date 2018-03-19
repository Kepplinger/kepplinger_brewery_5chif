package at.kepplinger.brewery.business.facades;

import at.kepplinger.brewery.entity.Beer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BeerFacade {

    @PersistenceContext
    private EntityManager em;

    public void save(Beer beer) {
        em.persist(beer);
    }

    public List<Beer> findAll() {
        return em.createQuery("select b from Beer b").getResultList();
    }
}
