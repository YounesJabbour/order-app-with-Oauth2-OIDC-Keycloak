package app.orderservice.web;

import app.orderservice.client.InventoryRestClient;
import app.orderservice.entity.Order;
import app.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrdersRestController {
    private final OrderRepository orderRepository;
    private final InventoryRestClient inventoryRestClient;


    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('USER')")
    public List<Order> findAllOrders(){
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(o->{
            o.getProductItems().forEach(pi->{
                pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
            });
        });
        return allOrders;
    }
    @GetMapping("/orders/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Order findOrderById(@PathVariable String id){
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(pi->{
            pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
        });
        return order;
    }

    @GetMapping("/auth")
    public Authentication auth(Authentication authentication){
        return authentication;
    }
}
