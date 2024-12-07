package app.inventoryservice;

import app.inventoryservice.entity.Product;
import app.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            List<Product> products = Stream.of(
                    Product.builder().id("P01").name("Product 1").price(1000).build(),
                    Product.builder().id("P02").name("Product 2").price(2000).build(),
                    Product.builder().id("P03").name("Product 3").price(3000).build()
            ).collect(Collectors.toList());
            productRepository.saveAll(products);
        };
    }
}
