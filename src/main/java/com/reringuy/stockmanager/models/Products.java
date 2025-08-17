package com.reringuy.stockmanager.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private String code;

    @Column(name = "descricao")
    private String description;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dateDelivered;

    @Column(name = "validade", nullable = false)
    private LocalDate expireDate;

    @Column(name = "quantidade",nullable = false)
    private int quantity;

    public Products() {
    }

    public Products(String code, String description, LocalDate dateDelivered, LocalDate expireDate, int quantity) {
        this.code = code;
        this.description = description;
        this.dateDelivered = dateDelivered;
        this.expireDate = expireDate;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String codigo) {
        this.code = codigo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descricao) {
        this.description = descricao;
    }

    public LocalDate getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(LocalDate dataEntrada) {
        this.dateDelivered = dataEntrada;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate validade) {
        this.expireDate = validade;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantidade) {
        this.quantity = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(id, products.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id=" + id +
                ", codigo='" + code + '\'' +
                ", descricao='" + description + '\'' +
                ", dataEntrada=" + dateDelivered +
                ", validade=" + expireDate +
                ", quantidade=" + quantity +
                '}';
    }
}
