package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.OrderDTO;
import com.example.springbootecommerce.dto.OrderRequest;
import com.example.springbootecommerce.model.ProductCategory;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {


    OrderDTO getOrderByOrderId(Long id);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByEmail(String email);

    List<OrderDTO> getOrdersByOrderDate(LocalDate date);

    List<OrderDTO> getOrdersByProductCategory(ProductCategory category);

    OrderDTO createOrder(OrderRequest orderRequest);

    OrderDTO updateOrder(OrderRequest orderRequest);

    void deleteOrder(Long id);

}
