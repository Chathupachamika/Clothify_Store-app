package service;

import entity.Product;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product productDTO);
    boolean removeProduct(int productId);
    boolean updateProduct(Product productDTO);
    List<Product> getAllProducts();
    Product searchProduct(int productId);
}
