package com.reringuy.stockmanager.services;

import com.reringuy.stockmanager.models.Payment;
import com.reringuy.stockmanager.models.Products;
import com.reringuy.stockmanager.models.Users;
import com.reringuy.stockmanager.repositories.PaymentRepository;
import com.reringuy.stockmanager.utils.Pagination;
import com.reringuy.stockmanager.utils.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@RequestScoped
public class PaymentService implements Serializable {
    @Inject
    private PaymentRepository paymentRepository;
    @Inject
    private ProductsService productsService;

    @Transactional
    public void SetPayment(Users users, Products products, int quantity) throws RuntimeException {
        if (quantity <= 0 || quantity > products.getQuantidade()) {
            throw new RuntimeException("Quantidade invalida.");
        }
        products.setQuantidade(products.getQuantidade() - quantity);
        Payment newPayment = new Payment(quantity, products, users);

        productsService.UpdateProduct(products);
        paymentRepository.save(newPayment);
    }

    @Transactional
    public Pagination<Payment> GetPaymentsList(String param, int currentPage, int pageSize) {
        return paymentRepository.paginate(param, currentPage, pageSize);
    }
}
