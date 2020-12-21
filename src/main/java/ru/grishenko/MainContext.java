package ru.grishenko;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.grishenko.interf.ProductRepository;

import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainContext {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyAppConfig.class);

        ProductRepository pr = context.getBean("productRepo", ProductRepository.class);
        Cart cart = context.getBean("cart", Cart.class);

        System.out.println(pr);

        System.out.println("/add [id] - Add product by ID to cart");
        System.out.println("/del [id] - Delete product by ID from cart");
        System.out.println("/clear - Delete all product from cart");
        System.out.println("/show - Show products in cart");
        System.out.println("/exit - Exit from App");

        String cmd;

        while (true) {
            cmd = sc.nextLine();

            if (cmd.startsWith("/exit")) {
                break;
            }
            if (cmd.startsWith("/add ")) {
                try {
                    cart.addById(Integer.parseInt(cmd.split("\\s")[1]));
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (cmd.startsWith("/del ")) {
                try {
                    if (cart.deleteById(Integer.parseInt(cmd.split("\\s")[1]))) {
                        System.out.println("Product deleted");
                    } else {
                        System.out.println("Product by id=" + Integer.parseInt(cmd.split("\\s")[1]) + " not found in cart");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }

            }
            if (cmd.startsWith("/clear")) {
                cart.clear();
            }
            if (cmd.startsWith("/show")) {
                System.out.println(cart);
            }

            // для проверки условия "При каждом запросе корзины из контекста, должна создаваться новая корзина"
            if (cmd.startsWith("/get_cart")) {
                cart = context.getBean("cart", Cart.class);
            }

        }

        context.close();
        sc.close();
    }
}
