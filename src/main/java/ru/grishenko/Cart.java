package ru.grishenko;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.grishenko.interf.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Scope(value = "prototype")
public class Cart {

    private List<Product> products;

    @Autowired
    private ProductRepository pr;

    private void add(Product item) {
        products.add(item);
    }

    public void addById(int id) {
        Product prd = pr.getProductById(id);
        if (prd == null) {
            throw new NoSuchElementException("Product by ID = " + id + " not found");
        }
        add(pr.getProductById(id));
    }

    private boolean delete(Product item) {
        return products.remove(item);
    }

    public boolean deleteById(int id) {
        Product prd = pr.getProductById(id);
        if (prd == null) {
            throw new NoSuchElementException("Product by ID = " + id + " not found");
        }
        return delete(pr.getProductById(id));
    }

    public void clear() {
        products.clear();
    }

    @PostConstruct
    public void initCart() {
        products = new LinkedList<>();
    }

    @Override
    public String toString() {
        if (products.size() == 0) {
            return "[Cart is Empty]";
        }

        StringBuilder sb = new StringBuilder();
        for (Product item : products) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
