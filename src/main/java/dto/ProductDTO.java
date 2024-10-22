package dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Data
public class ProductDTO {
    private int productId;
    private String productName;
    private String category;
    private String size;
    private double price;
    private int quantity;
    private String image;
    private int supplierId;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public String getDiscount() {
        return String.valueOf(discount);
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    private double discount;

    public ProductDTO(int id, String productName, String category, String size, double price, int quantity, String image, int supplierId, LocalDate createdAt, LocalDate updatedAt, double discount) {
        this.productId = id;
        this.productName = productName;
        this.category = category;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.supplierId = supplierId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.discount = discount;
    }

    public ProductDTO() {

    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }
}
