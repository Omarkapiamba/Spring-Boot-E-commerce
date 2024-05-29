package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.dto.OrderDTO;
import com.example.springbootecommerce.exceptions.ResourceNotFoundException;
import com.example.springbootecommerce.service.impl.OrderServiceImpl;
import com.example.springbootecommerce.dto.OrderRequest;
import com.example.springbootecommerce.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{orderId}")
    public OrderDTO getOrderByOrderId(@PathVariable("orderId") Long id) {

        return orderService.getOrderByOrderId(id);
    }

    @GetMapping()
    public List<OrderDTO> getAllOrders() {

        return orderService.getAllOrders();
    }

    @GetMapping(path = "/email")
    public List<OrderDTO> getOrdersByEmail(@RequestParam String email) {

        return orderService.getOrdersByEmail(email);

    }

    @GetMapping(path = "/date")
    public List<OrderDTO> getOrdersByOrderDate(@RequestParam LocalDate date) {

        return orderService.getOrdersByOrderDate(date);
    }


    @GetMapping(path = "/category")
    public List<OrderDTO> getOrdersByProductCategory(@RequestParam ProductCategory category) {


        return orderService.getOrdersByProductCategory(category);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest orderRequest) {

        OrderDTO createdOrderDTO = orderService.createOrder(orderRequest);

        return new ResponseEntity<>(createdOrderDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderRequest orderRequest) {

        OrderDTO updatedOrderDTO = orderService.updateOrder(orderRequest);

        return new ResponseEntity<>(updatedOrderDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long id) {

        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(String.format("Order med id: %s är raderad", id), HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(String.format("Order med id: %s hittades inte", id), HttpStatus.NOT_FOUND);
        }

    }

}
