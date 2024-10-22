package service.impl;

import db.DBConnection;
import entity.OrderList;
import org.hibernate.Session;
import service.OrderListService;
import util.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderListServiceImpl implements OrderListService {
    private Connection connection;
    public OrderListServiceImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addOrder(OrderList orderlist) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(orderlist);
        session.getTransaction().commit();
        session.close();
        return false;
//        try (Connection connection = DBConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement("INSERT INTO order_details (order_id, product_id, quantity, price, discount, product_name, total) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
//            statement.setInt(1, orderlist.getOrderId());
//            statement.setInt(2, orderlist.getProductId());
//            statement.setInt(3, orderlist.getQuantity());
//            statement.setDouble(4, orderlist.getPrice());
//            statement.setDouble(5, orderlist.getDiscount());
//            statement.setString(6, orderlist.getProductName());
//            statement.setDouble(7, orderlist.getTotalAmount());
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
    }
}
