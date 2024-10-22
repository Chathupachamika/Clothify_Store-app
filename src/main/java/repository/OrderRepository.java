package repository;

import entity.Order;

import java.util.List;

public interface OrderRepository {
    boolean addOrder(Order order);
    boolean removeOrder(int orderId);
    List<Order> getAllOrder();
    boolean updateOrder(Order order);
}
