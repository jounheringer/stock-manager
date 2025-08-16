package com.reringuy.stockmanager.repositories;

import com.reringuy.stockmanager.models.Users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class UsersRepository implements Serializable {

    @Inject
    private EntityManager entityManager;

    public UsersRepository() {

    }

    public UsersRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Users> findAll() {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u", Users.class);
        return query.getResultList();
    }

    public List<Users> findByUsernameLike(String username) {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.username LIKE :username", Users.class);
        query.setParameter("username", "%" + username + "%");
        return query.getResultList();
    }

    public Users findById(long id) {
        return entityManager.find(Users.class, id);
    }

    public void save(Users user) {
        entityManager.persist(user);
    }

    public void update(Users user) {
        entityManager.merge(user);
    }

    public void deleteById(long id) {
        Users user = this.findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

}
