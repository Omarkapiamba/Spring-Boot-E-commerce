package com.example.springbootecommerce.dto.dtoMapper;

import com.example.springbootecommerce.model.Order;
import com.example.springbootecommerce.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    @Override
    public OrderDTO apply(Order order) {


        return new OrderDTO(
                order.getId(),
                order.getUserId(),
                order.getEmail(),
                order.getOrderList(),
                order.getOrderDate(),
                order.getTotalAmount()
        );
    }
}
