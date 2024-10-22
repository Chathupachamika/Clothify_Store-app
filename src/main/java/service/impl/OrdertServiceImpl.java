package service.impl;

import entity.Order;
import repository.OrderRepository;
import repository.impl.OrdrRepositoryImpl;
import service.OrderService;

import java.util.List;

public class OrdertServiceImpl implements OrderService {
    private OrderRepository orderRepository = new OrdrRepositoryImpl();
    @Override
    public List<Order> getAllOrder() {
        return orderRepository.getAllOrder();
    }

}
