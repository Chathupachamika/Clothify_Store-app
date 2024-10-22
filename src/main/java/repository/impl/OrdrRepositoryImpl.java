package repository.impl;

import db.DBConnection;
import entity.Order;
import entity.Product;
import org.hibernate.Session;
import repository.OrderRepository;
import util.HibernateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdrRepositoryImpl implements OrderRepository {
    private Connection connection;
    public OrdrRepositoryImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addOrder(Order order) {
        return false;
    }

    @Override
    public boolean removeOrder(int orderId) {
        return false;
    }

    @Override
    public List<Order> getAllOrder() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Order> products = session.createQuery("from Order", Order.class).list();
        session.getTransaction().commit();
        session.close();
        return products;

//        List<Order> products = new ArrayList<>();
//        String query = "SELECT * FROM orders";
//        try (Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery(query)) {
//            while (rs.next()) {
//                Order order = new Order(
//                        rs.getInt("order_id"),
//                        rs.getInt("employee_id"),
//                        rs.getDouble("total_cost"),
//                        rs.getString("payment_type"),
//                        rs.getDate("order_date").toLocalDate(),
//                        rs.getString("customer_email"),
//                        rs.getString("customer_name"),
//                        rs.getString("phone_number")
//                );
//                products.add(order);
//                System.out.println(order);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }return products;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }
}