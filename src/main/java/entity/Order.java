package entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
@Table(name = "customer_orders")
public class Order {

    @Id
    private int orderId;

    private Integer employeeId;
    private double totalCost;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    private String paymentType;
    private LocalDate orderDate;
    private String customerEmail;
    private String customerName;
    private String phoneNumber;

    public Order() {}

    public Order(int orderId, Integer employeeId, double totalCost, String paymentType,
                 LocalDate orderDate, String customerEmail, String customerName, String phoneNumber) {
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.totalCost = totalCost;
        this.paymentType = paymentType;
        this.orderDate = orderDate;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }
}