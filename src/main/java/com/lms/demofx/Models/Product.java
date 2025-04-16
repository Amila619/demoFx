package com.lms.demofx.Models;

public class Product {
    private String product_name;
    private int product_quantity;
    private double product_price;

    public Product(String product_name, int product_quantity, double product_price) {
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }


}
