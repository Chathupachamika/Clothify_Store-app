package dto;

import java.time.LocalDate;

public class OrderDTO {
    private int orderId;
    private Integer employeeId;
    private double totalCost;
    private String paymentType;
    private LocalDate orderDate;
    private String customerEmail;
    private String customerName;
    private String phoneNumber;

    public OrderDTO(int orderId, Integer employeeId, double totalCost, String paymentType,
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

    public int getOrderId() {
        return orderId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", employeeId=" + employeeId +
                ", totalCost=" + totalCost +
                ", paymentType='" + paymentType + '\'' +
                ", orderDate=" + orderDate +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
