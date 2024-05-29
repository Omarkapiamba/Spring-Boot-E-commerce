package com.example.springbootecommerce.service.impl;

import com.example.springbootecommerce.dto.OrderDTO;
import com.example.springbootecommerce.dto.OrderRequest;
import com.example.springbootecommerce.dto.dtoMapper.OrderDTOMapper;
import com.example.springbootecommerce.exceptions.ResourceNotFoundException;
import com.example.springbootecommerce.repository.OrderRepository;
import com.example.springbootecommerce.model.Order;
import com.example.springbootecommerce.model.ProductCategory;
import com.example.springbootecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDTOMapper orderDTOMapper) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
    }

    @Override
    public OrderDTO getOrderByOrderId(Long id) {

        if (orderRepository.existsById(id)) {

            return orderRepository.findById(id)
                    .map(orderDTOMapper)
                    .get();

        } else {
            throw new ResourceNotFoundException("Order", "id", id);
        }

    }

    @Override
    public List<OrderDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(orderDTOMapper)
                .toList();
    }

    @Override
    public List<OrderDTO> getOrdersByEmail(String email) {

        return orderRepository.findAllByEmail(email)
                .stream()
                .map(orderDTOMapper)
                .toList();
    }

    @Override
    public List<OrderDTO> getOrdersByOrderDate(LocalDate date) {

        return orderRepository.findAllByOrderDate(date)
                .stream()
                .map(orderDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByProductCategory(ProductCategory category) {

        List<Order> orderList = orderRepository.findAll()
                .stream()
                .filter(order -> order.getOrderList()
                        .stream()
                        .anyMatch(product -> product.getCategory() == category))
                .toList();

        List<OrderDTO> orderDTOList = orderList
                .stream()
                .map(orderDTOMapper)
                .toList();


        return orderDTOList;
    }

    @Override
    public OrderDTO createOrder(OrderRequest orderRequest) {

        Double totalAmount = 0.0;

        for (int i = 0; i < orderRequest.orderList().size(); i++) {
            totalAmount += orderRequest.orderList().get(i).getPrice();
        }

        Order createdOrder = orderRequestToOrder(orderRequest);

        createdOrder.setTotalAmount(totalAmount);
        createdOrder.setOrderDate(LocalDate.now());

        orderRepository.save(createdOrder);

        return orderDTOMapper.apply(createdOrder);

    }

    @Override
    public OrderDTO updateOrder(OrderRequest orderRequest) {


        Order updateOrder = orderRepository.findById(orderRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderRequest.id()));

        updateOrder.setEmail(orderRequest.email());
        updateOrder.setUserId(orderRequest.userId());
        updateOrder.setTotalAmount(orderRequest.totalAmount());
        updateOrder.setOrderList(orderRequest.orderList());
        updateOrder.setOrderDate(orderRequest.orderDate());

        orderRepository.save(updateOrder);

        return orderDTOMapper.apply(updateOrder);

    }

    @Override
    public void deleteOrder(Long id) {

        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));

        orderRepository.deleteById(id);

    }

    public Order orderRequestToOrder(OrderRequest orderRequest) {

        return new Order(
                orderRequest.id(),
                orderRequest.userId(),
                orderRequest.email(),
                orderRequest.orderList(),
                orderRequest.orderDate(),
                orderRequest.totalAmount()
        );
    }

}
