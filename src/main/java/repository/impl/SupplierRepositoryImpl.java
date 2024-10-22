package repository.impl;

import db.DBConnection;
import entity.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.SupplierRepository;
import util.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {

    private final Connection connection;

    public SupplierRepositoryImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish a database connection.");
        }
    }

    @Override
    public boolean addSupplier(Supplier supplierDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(supplierDTO);
        session.getTransaction().commit();
        session.close();
//        String query = "INSERT INTO suppliers (supplier_id, name, company, email, item) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, supplierDTO.getId());
//            stmt.setString(2, supplierDTO.getName());
//            stmt.setString(3, supplierDTO.getCompany());
//            stmt.setString(4, supplierDTO.getEmail());
//            stmt.setString(5, supplierDTO.getSuppliedItem());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        return false;
    }

    @Override
    public boolean updateSupplier(Supplier supplierDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            session.update(supplierDTO);
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
//        String query = "UPDATE suppliers SET name = ?, email = ?, company = ? WHERE supplier_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, supplierDTO.getName());
//            stmt.setString(2, supplierDTO.getEmail());
//            stmt.setString(3, supplierDTO.getCompany());
//            stmt.setInt(4, supplierDTO.getId());
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    @Override
    public boolean removeSupplier(int supplierId) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//
//            // Load the product by ID
//            Supplier product = session.get(Supplier.class, supplierId);
//            if (product != null) {
//                session.delete(product); // Delete the product
//                transaction.commit(); // Commit the transaction
//                return true; // Product was deleted
//            } else {
//                return false; // Product not found
//            }
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback(); // Rollback transaction if something went wrong
//            }
//            e.printStackTrace(); // Consider logging the exception for debugging purposes
//            return false; // Operation failed
//        }
        String query = "DELETE FROM suppliers WHERE supplier_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, supplierId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Supplier searchSupplier(int supplierId) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Supplier product = session.get(Supplier.class, supplierId);
        session.getTransaction().commit();
        session.close();
        return product;
//        String query = "SELECT * FROM suppliers WHERE supplier_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, supplierId);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return new Supplier(
//                        rs.getInt("supplier_id"),
//                        rs.getString("name"),
//                        rs.getString("company"),
//                        rs.getString("email"),
//                        rs.getString("item")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public List<Supplier> getAllSupplier() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Supplier> suppliers = session.createQuery("from Supplier", Supplier.class).list();
        session.getTransaction().commit();
        session.close();
        return suppliers;
//        String query = "SELECT * FROM suppliers";
//        List<Supplier> supplierDTOS = new ArrayList<>();
//        try (PreparedStatement stmt = connection.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                supplierDTOS.add(new Supplier(
//                        rs.getInt("supplier_id"),
//                        rs.getString("name"),
//                        rs.getString("company"),
//                        rs.getString("email"),
//                        rs.getString("item")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return supplierDTOS;
    }
}
