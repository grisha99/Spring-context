package ru.grishenko;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.grishenko.interf.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("productRepo")
@Primary
public class MemoryProductRepositoryImpl implements ProductRepository {

    List<Product> products;

    public Product getProductById(int id) {
        for (Product item : products) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @PostConstruct
    private void initRepository() {
        products = new ArrayList<Product>();
        for (int i = 0; i < 5; i++) {
            products.add(new Product("Product_" + i, 30.40 + (30.40 * i)));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product item : products) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
