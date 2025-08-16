package com.reringuy.stockmanager.services;

import com.reringuy.stockmanager.models.Products;
import com.reringuy.stockmanager.repositories.ProductsRepository;
import com.reringuy.stockmanager.utils.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDate;

@RequestScoped
public class ProductsService implements Serializable {
    @Inject
    private ProductsRepository productsRepository;

    @Transactional
    public void SaveProduct(Products products) throws RuntimeException {
        ValidateProduct(products);

        products.setDataEntrada(LocalDate.now());
        productsRepository.save(products);
    }

    @Transactional
    public void UpdateProduct(Products products) throws RuntimeException {
        ValidateProduct(products);

        productsRepository.update(products);
    }

    @Transactional
    public void DeleteProduct(long id) {
        productsRepository.deleteById(id);
    }

    private void ValidateProduct(Products products) throws RuntimeException {
        if (products.getValidade().isBefore(LocalDate.now())) {
            throw new RuntimeException("Produto expirado.");
        }

        if (products.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade invalida.");
        }

        if (productsRepository.findByCodigo(products.getCodigo()) != null) {
            throw new RuntimeException("Produto ja cadastrado.");
        }
    }
}
