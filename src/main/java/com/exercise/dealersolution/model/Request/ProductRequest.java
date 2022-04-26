package com.exercise.dealersolution.model.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {

    private long id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("deadline")
    private String deadline;

    public ProductRequest(long id, String description, String status, Double price, Integer quantity, String deadline) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
