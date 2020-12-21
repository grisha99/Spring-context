package ru.grishenko;

public class Product {

    private static int initID = 0;

    private int id;
    private String title;
    private double cost;

    {
        initID++;
    }

    public Product(String title, double cost) {
        this.id = initID;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Product: [id=%d; title=%s; cost=%.2f]", id, title, cost);
    }
}
