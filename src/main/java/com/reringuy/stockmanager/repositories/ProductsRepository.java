package com.reringuy.stockmanager.repositories;

import com.reringuy.stockmanager.models.Products;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@RequestScoped
public class ProductsRepository {

    @Inject
    private EntityManager entityManager;

    public ProductsRepository() {
    }

    public ProductsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Products> findAll() {
        return entityManager.createQuery("SELECT p FROM Products p", Products.class).getResultList();
    }

    public Products findById(long id) {
        return entityManager.find(Products.class, id);
    }

    public Products findByCodigo(String codigo) {
        List<Products> results = entityManager
                .createQuery("SELECT p FROM Products p WHERE p.code = :codigo", Products.class)
                .setParameter("codigo", codigo)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public Products findByCodigoLike(String codigo) {
        return entityManager
                .createQuery("SELECT p FROM Products p WHERE p.code LIKE :codigo", Products.class)
                .setParameter("codigo", "%" + codigo + "%").getSingleResult();
    }

    public void save(Products product) {
        entityManager.persist(product);
    }

    public void update(Products product) {
        entityManager.merge(product);
    }

    public void deleteById(long id) {
        Products product = this.findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}
