package com.revolut.resources;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

public class BaseResource {
    private EntityManager entityManager;

    public BaseResource(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PreDestroy
    public void release() {
        if (entityManager != null)
            entityManager.close();
    }
}
