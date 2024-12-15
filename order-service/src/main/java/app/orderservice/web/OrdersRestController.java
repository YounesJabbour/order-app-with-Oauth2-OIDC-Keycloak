package app.orderservice.web;

import app.orderservice.client.InventoryRestClient;
import app.orderservice.entity.Order;
import app.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<Order>> findAllOrders(){
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(o->{
            o.getProductItems().forEach(pi->{
                pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
            });
        });
        return ResponseEntity.ok(allOrders);
    }
    @GetMapping("/orders/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Order> findOrderById(@PathVariable String id){

        Order order =  orderRepository.findById(id).isPresent() ? orderRepository.findById(id).get() : null;
        if(order == null){
            return null;
        }
        order.getProductItems().forEach(pi->{
            pi.setProduct(inventoryRestClient.findProductById(pi.getProductId()));
        });
        return ResponseEntity.ok(order);
    }
}
