package entity;
import jakarta.persistence.*;

@Entity
@Table(name = "order_list")
public class OrderList {

    @Id
    private int orderId;

    private int productId;
    private int quantity;
    private double price;
    private double discount;
    private String productName;
    private double totalAmount;

    public OrderList() {
    }


    public OrderList(int orderid, int productid, int qty, double price, double discount, String productName, double total) {
        this.orderId = orderid;
        this.productId = productid;
        this.quantity = qty;
        this.price = price;
        this.discount = discount;
        this.productName = productName;
        this.totalAmount = calculateTotal();
    }

    public double getTotalAmount() {
        return calculateTotal();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
        this.totalAmount = calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalAmount = calculateTotal();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.totalAmount = calculateTotal();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        this.totalAmount = calculateTotal();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private double calculateTotal() {
        return (price * quantity) * (1 - discount / 100);
    }
}
