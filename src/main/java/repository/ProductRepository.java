package repository;

import entity.Product;

import java.util.List;

public interface ProductRepository {
    boolean addProduct(Product productDTO);
    boolean removeProduct(int productId);
    List<Product> getAllProducts();
    boolean updateProduct(Product productDTO);
    Product searchProduct(int productId);
}
