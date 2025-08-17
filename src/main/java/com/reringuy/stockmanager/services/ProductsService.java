package com.reringuy.stockmanager.services;

import com.reringuy.stockmanager.models.Products;
import com.reringuy.stockmanager.repositories.ProductsRepository;
import com.reringuy.stockmanager.utils.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@RequestScoped
public class ProductsService implements Serializable {
    @Inject
    private ProductsRepository productsRepository;

    @Transactional
    public void SaveProduct(Products products) throws RuntimeException {
        ValidateProduct(products, false);

        products.setDateDelivered(LocalDate.now());
        productsRepository.save(products);
    }

    @Transactional
    public void UpdateProduct(Products products) throws RuntimeException {
        ValidateProduct(products, true);

        productsRepository.update(products);
    }

    @Transactional
    public void DeleteProduct(long id) {
        productsRepository.deleteById(id);
    }

    public List<Products> GetAllProducts() {
        return productsRepository.findAll();
    }

    public Products GetProductById(long id) {
        return productsRepository.findById(id);
    }

    private void ValidateProduct(Products products, boolean isUpdate) throws RuntimeException {
        if (products.getExpireDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Produto expirado.");
        }

        if (products.getQuantity() <= 0) {
            throw new RuntimeException("Quantidade invalida.");
        }

        Products existing = productsRepository.findByCodigo(products.getCode());
        if (existing != null) {
            if (!isUpdate) {
                throw new RuntimeException("Produto ja cadastrado.");
            } else {
                Long currentId = products.getId();
                Long existingId = existing.getId();
                if (!existingId.equals(currentId)) {
                    throw new RuntimeException("Produto ja cadastrado.");
                }
            }
        }
    }
}
