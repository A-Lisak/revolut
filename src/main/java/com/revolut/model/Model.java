package com.revolut.model;

import com.revolut.persistence.PersistenceUnit;

import javax.persistence.EntityManager;

public class Model {
    public void save(EntityManager em) {
        em.getTransaction().begin();
        if (em.contains(this)) {
            em.merge(this);
        } else {
            em.persist(this);
        }
        em.getTransaction().commit();
    }

    public void save() {
        EntityManager em = PersistenceUnit.getInstance().createEntityManager();
        try {
            save(em);
        } finally {
            em.close();
        }
    }
}
