package com.okayo.Bill.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity(name="bill")
public class Bill  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;
    private String emittedBy, client;
    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "bill_products",
            joinColumns =  @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();


    public Bill() {
    }

    public Bill(String emittedBy, String client) {
        this.emittedBy = emittedBy;
        this.client = client;
        this.date = LocalDate.now();
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getEmittedBy() {
        return emittedBy;
    }

    public void setEmittedBy(String emittedBy) {
        this.emittedBy = emittedBy;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        return Objects.equals(billId, bill.billId) && Objects.equals(emittedBy, bill.emittedBy) && Objects.equals(client, bill.client) && Objects.equals(date, bill.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, emittedBy, client, date);
    }
}
