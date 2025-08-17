package com.reringuy.stockmanager.controller;

import com.reringuy.stockmanager.models.Products;
import com.reringuy.stockmanager.services.ProductsService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@ViewScoped
public class ProductController implements Serializable {
    private static final long serialVersionUID = 1L;
    private Products products;
    private LocalDate todayDate;
    private List<Products> productsList;

    @Inject
    private ProductsService productsService;

    @PostConstruct
    public void init() {
        this.products = new Products();
        this.todayDate = LocalDate.now();
        this.productsList = this.productsService.GetAllProducts();
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public Products getProducts() {
        return products;
    }

    public void saveProduct() {
        try {
            this.productsService.SaveProduct(this.products);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Produto salvo com sucesso."));
            this.products = new Products();
            this.productsList = this.productsService.GetAllProducts();
        } catch (RuntimeException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar", ex.getMessage()));
        }
    }
}
