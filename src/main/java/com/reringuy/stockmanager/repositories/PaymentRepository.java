package com.reringuy.stockmanager.repositories;

import com.reringuy.stockmanager.models.Payment;
import com.reringuy.stockmanager.utils.Pagination;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class PaymentRepository {
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

    public Pagination<Payment> paginate(String param, int currentPage, int pageSize) {
        int normalizedPageSize = pageSize <= 0 ? 10 : pageSize;

        Long count = entityManager
                .createQuery(
                        "SELECT COUNT(p) FROM Payment p "
                                + (param != null ? "WHERE p.product.codigo LIKE :param" : ""), Long.class
                )
                .setParameter("param", "%" + param + "%")
                .getSingleResult();
        int totalItems = count == null ? 0 : count.intValue();

        int totalPages = totalItems == 0 ? 0 : (int) Math.ceil((double) totalItems / normalizedPageSize);

        int normalizedPage = currentPage <= 0 ? 1 : currentPage;
        if (totalPages > 0 && normalizedPage > totalPages) {
            normalizedPage = totalPages;
        }

        if (totalItems == 0) {
            return new Pagination<>(Collections.emptyList(), normalizedPageSize, 1, 0, 0);
        }

        int firstResult = (normalizedPage - 1) * normalizedPageSize;

        List<Payment> items = entityManager.createQuery(
                "SELECT p FROM Payment p"
                        + (param != null ? "WHERE p.product.codigo LIKE :param" : "")
                        + " ORDER BY p.id", Payment.class)
                .setParameter("param", "%" + param + "%")
                .setFirstResult(firstResult)
                .setMaxResults(normalizedPageSize)
                .getResultList();

        return new Pagination<>(items, normalizedPageSize, normalizedPage, totalPages, totalItems);
    }
}
