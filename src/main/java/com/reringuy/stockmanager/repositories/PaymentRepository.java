package com.reringuy.stockmanager.repositories;

import com.reringuy.stockmanager.models.Payment;
import com.reringuy.stockmanager.models.PaymentDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class PaymentRepository {
    @Inject
    private EntityManager entityManager;

    public PaymentRepository() {
    }

    public PaymentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Payment> findAll() {
        TypedQuery<Payment> query = entityManager.createQuery("SELECT p FROM Payment p", Payment.class);
        return query.getResultList();
    }

    public Payment findById(long id) {
        return entityManager.find(Payment.class, id);
    }

    public void save(Payment payment) {
        entityManager.persist(payment);
    }

    public void update(Payment payment) {
        entityManager.merge(payment);
    }

    public void deleteById(long id) {
        Payment payment = this.findById(id);
        if (payment != null) {
            entityManager.remove(payment);
        }
    }

    public List<PaymentDetails> paginate(String param) {
        boolean hasParam = param != null && !param.trim().isEmpty();
        String likeParam = hasParam ? "%" + param.trim().toLowerCase() + "%" : null;

        String itemsJpql = "SELECT NEW com.reringuy.stockmanager.models.PaymentDetails(p, u, prod) FROM Payment p " +
                "LEFT JOIN p.product prod " +
                "LEFT JOIN p.user u " +
                (hasParam ? "WHERE LOWER(prod.code) LIKE :param " : "") +
                "ORDER BY p.id";

        TypedQuery<PaymentDetails> itemsQuery = entityManager.createQuery(itemsJpql, PaymentDetails.class);
        if (hasParam) {
            itemsQuery.setParameter("param", likeParam);
        }

        return itemsQuery.getResultList();
    }
}
