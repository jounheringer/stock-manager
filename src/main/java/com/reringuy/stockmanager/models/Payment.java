package com.reringuy.stockmanager.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pagamentos")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Users user;


    public Payment(int Quantity, Products product, Users user) {
        this.quantity = Quantity;
        this.product = product;
        this.user = user;
    }

    public Payment() {

    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(quantity, payment.quantity) && Objects.equals(product, payment.product) && Objects.equals(user, payment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product, user);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                ", user=" + user +
                '}';
    }
}
