package com.reringuy.stockmanager.controller;

import com.reringuy.stockmanager.models.Products;
import com.reringuy.stockmanager.services.ProductsService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;

@Named
@ViewScoped
public class ProductController implements Serializable {
    private static final long serialVersionUID = 1L;
    private Products products;
    private LocalDate todayDate;

    @Inject
    private ProductsService productsService;

    @PostConstruct
    public void init() {
        this.products = new Products();
        this.todayDate = LocalDate.now();
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public Products getProducts() {
        return products;
    }

    public void saveProduct() {
        this.productsService.SaveProduct(this.products);
        this.products = new Products();
    }
}
