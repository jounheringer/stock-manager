package com.reringuy.stockmanager.services;

import com.reringuy.stockmanager.models.Payment;
import com.reringuy.stockmanager.models.PaymentDetails;
import com.reringuy.stockmanager.models.Products;
import com.reringuy.stockmanager.models.Users;
import com.reringuy.stockmanager.repositories.PaymentRepository;
import com.reringuy.stockmanager.utils.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class PaymentService implements Serializable {
    @Inject
    private PaymentRepository paymentRepository;
    @Inject
    private ProductsService productsService;

    @Transactional
    public void SetPayment(Users users, Products products, int quantity) throws RuntimeException {
        if (quantity <= 0 || quantity > products.getQuantity()) {
            throw new RuntimeException("Quantidade invalida.");
        }
        products.setQuantity(products.getQuantity() - quantity);
        Payment newPayment = new Payment(quantity, products, users);

        productsService.UpdateProduct(products);
        paymentRepository.save(newPayment);
    }

    @Transactional
    public List<PaymentDetails> GetPaymentsList(String param) {
        return paymentRepository.paginate(param);
    }
}
