package repository.impl;

import db.DBConnection;
import entity.Product;
import org.hibernate.Session;
import repository.ProductRepository;
import util.HibernateUtil;
import java.sql.*;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
//    private Connection connection;
//    public ProductRepositoryImpl() {
//        try {
//            this.connection = DBConnection.getInstance().getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public boolean addProduct(Product product) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
        session.close();
//        String query = "INSERT INTO products (product_id, name, category, size, price, quantity, image, supplier_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query)) {
//            stmt.setInt(1, product.getProductId());
//            stmt.setString(2, product.getProductName());
//            stmt.setString(3, product.getCategory());
//            stmt.setString(4, product.getSize());
//            stmt.setDouble(5, product.getPrice());
//            stmt.setInt(6, product.getQuantity());
//            stmt.setString(7, product.getImage());
//            stmt.setInt(8, product.getSupplierId());
//            stmt.setDate(9, Date.valueOf(product.getCreatedAt()));
//            stmt.setDate(10, Date.valueOf(product.getUpdatedAt()));
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace(); // Consider using a logging framework here
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product", Product.class).list();
        session.getTransaction().commit();
        session.close();
        return products;

//        List<Product> products = new ArrayList<>();
//        String query = "SELECT * FROM products";
//        try (Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery(query)) {
//            while (rs.next()) {
//                Product employee = new Product(
//                        rs.getInt("product_id"),
//                        rs.getString("name"),
//                        rs.getString("category"),
//                        rs.getString("size"),
//                        rs.getDouble("price"),
//                        rs.getInt("quantity"),
//                        rs.getString("image"),
//                        rs.getInt("supplier_id"),
//                        rs.getDate("created_at").toLocalDate(),
//                        rs.getDate("updated_at").toLocalDate(),
//                        rs.getDouble("discount")
//                );
//                products.add(employee);
//                System.out.println(employee);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean removeProduct(int productId) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Product product = session.get(Product.class, productId);
//            if (product != null) {
//                session.delete(product);
//                transaction.commit();
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//            return false;

                    String query = "DELETE FROM products WHERE product_id = ?";

        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, productId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the exception for debugging purposes
            return false;
        }

    }


    @Override
    public boolean updateProduct(Product product) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            session.update(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
//        String query = "UPDATE products SET name = ?, category = ?, size = ?, price = ?, quantity = ?, image = ?, supplier_id = ?, created_at = ?, updated_at = ? WHERE product_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, product.getProductName());
//            stmt.setString(2, product.getCategory());
//            stmt.setString(3, product.getSize());
//            stmt.setDouble(4, product.getPrice());
//            stmt.setInt(5, product.getQuantity());
//            stmt.setString(6, product.getImage());
//            stmt.setInt(7, product.getSupplierId());
//            stmt.setDate(8, Date.valueOf(product.getCreatedAt()));
//            stmt.setDate(9, Date.valueOf(product.getUpdatedAt()));
//            stmt.setInt(10, product.getProductId());
//
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace(); // Consider logging the exception for debugging purposes
//            return false;
//        }
    }

    @Override
    public Product searchProduct(int productId) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        session.getTransaction().commit();
        session.close();
        return product;

//        String query = "SELECT * FROM products WHERE product_id = ?";
//        Product product = null;
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, productId);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    product = new Product(
//                            rs.getInt("product_id"),
//                            rs.getString("name"),
//                            rs.getString("category"),
//                            rs.getString("size"),
//                            rs.getDouble("price"),
//                            rs.getInt("quantity"),
//                            rs.getString("image"),
//                            rs.getInt("supplier_id"),
//                            rs.getDate("created_at").toLocalDate(),
//                            rs.getDate("updated_at").toLocalDate(),
//                            rs.getDouble("discount")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
