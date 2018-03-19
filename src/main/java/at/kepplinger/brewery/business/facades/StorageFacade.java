package at.kepplinger.brewery.business.facades;

import at.kepplinger.brewery.entity.Storage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StorageFacade {

    @PersistenceContext
    private EntityManager em;

    public void save(Storage storage) {
        em.persist(storage);
    }

    public List<Storage> findAll() {
        return em.createQuery("select b from Storage b").getResultList();
    }
}