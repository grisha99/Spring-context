package ru.grishenko.interf;

import ru.grishenko.Product;

import java.util.List;

public interface ProductRepository {

    Product getProductById(int id);

    List<Product> getProducts();
}
