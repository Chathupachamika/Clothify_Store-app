package service;

import entity.Product;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product productDTO);
    boolean removeProduct(int productId);
    boolean updateProduct(Product productDTO);
    List<Product> getAllProducts();
    Product searchProduct(int productId);

    default Product getProductById(int productId) {
        // Fetch the product from the database or list by its ID
        Session session = HibernateUtil.getSession();
        Product product = session.get(Product.class, productId);
        session.close();
        return product;
    }

     default void updateProductStock(Product product) {
        // Update the product's stock in the database
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

}
