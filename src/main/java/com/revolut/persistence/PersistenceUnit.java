package com.revolut.persistence;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class PersistenceUnit {
    private static final String PERSISTENCE_UNIT = "accounts";

    private static PersistenceUnit ourInstance;
    private EntityManagerFactory entityManagerFactory;
    private String unit;

    private PersistenceUnit(String unit) {
        this.unit = unit;
    }

    public PersistenceUnit() {
        this(PERSISTENCE_UNIT);
        ourInstance = this;
    }

    public static PersistenceUnit getInstance() {
        if (ourInstance == null) {
            return new PersistenceUnit();
        }
        return ourInstance;
    }

    public static EntityManager getEntityManager() {
        return getInstance().createEntityManager();
    }

    public EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public synchronized EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(unit);
        }
        return entityManagerFactory;
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }
}
