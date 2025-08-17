package com.reringuy.stockmanager.controller;

import com.reringuy.stockmanager.models.PaymentDetails;
import com.reringuy.stockmanager.models.Products;
import com.reringuy.stockmanager.models.Users;
import com.reringuy.stockmanager.services.PaymentService;
import com.reringuy.stockmanager.services.ProductsService;
import com.reringuy.stockmanager.services.UsersService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PaymentController implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PaymentDetails> payments;

    private int size = 10;
    private int page = 1;

    private List<Users> users;
    private List<Products> products;
    private Long selectedUserId;
    private Long selectedProductId;
    private Integer quantity;

    @Inject
    private PaymentService paymentService;
    @Inject
    private UsersService usersService;
    @Inject
    private ProductsService productsService;

    @PostConstruct
    public void init() {
        this.payments = paymentService.GetPaymentsList(null);
        this.users = usersService.GetAllUsers();
        this.products = productsService.GetAllProducts();
    }

    public void savePayment() {
        if (selectedUserId == null || selectedProductId == null || quantity == null) {
            throw new RuntimeException("Todos os campos são obrigatórios.");
        }
        Users user = usersService.GetUserById(selectedUserId);
        Products product = productsService.GetProductById(selectedProductId);
        try {
            paymentService.SetPayment(user, product, quantity);
            this.selectedUserId = null;
            this.selectedProductId = null;
            this.quantity = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pagamento salvo com sucesso."));
        } catch (RuntimeException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar", ex.getMessage()));
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<PaymentDetails> getPayments() {
        return payments;
    }

    public List<Users> getUsers() {
        return users;
    }

    public List<Products> getProducts() {
        if (products != null) {
            products.forEach(product -> {
                if (product.getDescription() == null) {
                    product.setDescription("N/A");
                }
            });
        }
        return products;
    }

    public Long getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(Long selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public Long getSelectedProductId() {
        return selectedProductId;
    }

    public void setSelectedProductId(Long selectedProductId) {
        this.selectedProductId = selectedProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
