package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.Order;
import entity.OrderList;
import entity.Product;
import org.hibernate.Session;
import service.OrderListService;
import service.OrderService;
import service.OrderTableService;
import service.ProductService;
import service.impl.OrderListServiceImpl;
import service.impl.OrderTableServiceImpl;
import service.impl.OrdertServiceImpl;
import service.impl.ProductServiceImpl;
import util.HibernateUtil;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private JFXComboBox<?> cmbPaymentType;
    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colCreatedAt;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colImage;

    @FXML
    private TableColumn<?, ?> colPrice;
    @FXML
    private TableColumn<?, ?> colPrice2;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductIddata;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colProductNamedata;

    @FXML
    private TableColumn<?, ?> colQuantity;
    @FXML
    private TableColumn<?, ?> colQuantity2;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private JFXTextField txtEmployeeId;
    @FXML
    private TableView<OrderList> orderTable;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private DatePicker txtDate;

    @FXML
    private JFXTextField txtcustomerEmail;

    @FXML
    private JFXTextField txtcustomerName;

    @FXML
    private JFXTextField txtcustomerPhone;

    @FXML
    private JFXTextField txtdiscount;

    @FXML
    private JFXTextField txtorderId;

    @FXML
    private JFXTextField txtprice;

    @FXML
    private JFXTextField txtproductId;

    @FXML
    private JFXTextField txtproductName;

    @FXML
    private JFXTextField txtquantity;

    @FXML
    private JFXTextField txttotalAmount;

    private final OrderService orderService = new OrdertServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final OrderListService orderListService = new OrderListServiceImpl();
    private ObservableList<OrderList> orderData = FXCollections.observableArrayList();
    private final OrderTableService orderTableEka2 = new OrderTableServiceImpl();
    private ObservableList<Order> orderData2 = FXCollections.observableArrayList();

    @FXML
    void btnAddToOrder(ActionEvent event) {
        try {
            if (txtproductId.getText().isEmpty() || txtquantity.getText().isEmpty() ||
                    txtprice.getText().isEmpty() || txtdiscount.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled.");
                return;
            }
            int orderid = Integer.parseInt(txtorderId.getText());
            int productid = Integer.parseInt(txtproductId.getText());
            int qty = Integer.parseInt(txtquantity.getText());
            double price = Double.parseDouble(txtprice.getText());
            double discount = Double.parseDouble(txtdiscount.getText());
            double total = (price * qty) * (1 - discount / 100);
            String productName = txtproductName.getText();
            OrderList orderlist = new OrderList(orderid, productid, qty, price, discount, productName, total);
            orderData.add(orderlist);
            orderListService.addOrder(orderlist);
            loadOrderTable();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Order added successfully!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for ID, Price, Quantity, and Discount.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void btnCalculate(ActionEvent event) {
        double finalTotal = 0;
        for (OrderList order : orderData) {
            finalTotal += order.getTotalAmount();
        }txttotalAmount.setText(String.valueOf(finalTotal));
    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {
        try {
            if (txtcustomerName.getText().isEmpty() || txtcustomerEmail.getText().isEmpty() ||
                    cmbPaymentType.getValue() == null) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled.");
                return;
            }
            Integer orderId = Integer.valueOf(txtorderId.getText());
            Integer productId = Integer.valueOf(txtproductId.getText());
            double totalCost = Double.parseDouble(txttotalAmount.getText());
            String customerName = txtcustomerName.getText();
            String customerEmail = txtcustomerEmail.getText();
            String customerPhone = txtcustomerPhone.getText();
            String paymentType = String.valueOf(cmbPaymentType.getValue());
            LocalDate orderDate = txtDate.getValue();

            Order orderTableEka = new Order(orderId, productId, totalCost, paymentType, orderDate, customerEmail, customerName, customerPhone);
            orderTableEka.setOrderId(orderId);
            orderTableEka.setEmployeeId(productId);
            orderTableEka.setTotalCost(totalCost);
            orderTableEka.setPaymentType(paymentType);
            orderTableEka.setOrderDate(orderDate);
            orderTableEka.setCustomerEmail(customerEmail);
            orderTableEka.setCustomerName(customerName);
            orderTableEka.setPhoneNumber(customerPhone);
            orderData2.add(orderTableEka);
            orderTableEka2.addOrder(orderTableEka);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Order added successfully!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }showAlert(Alert.AlertType.INFORMATION, "Order Placed", "Your order has been placed successfully.");
    }

    @FXML
    void btnReload(ActionEvent event) {
        orderData.clear();
        orderTable.getItems().clear();
        txttotalAmount.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductIddata.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductNamedata.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("discount"));
        loadTable();
        loadOrderTable();
        setTextToValues2();
        productTable.getSelectionModel().selectedItemProperty().addListener(((ObservableValue<? extends Product> observable, Product oldValue, Product newValue) -> {
            if (newValue != null) {
                setTextToValues(newValue);
            }
        }));

    }
    private void setTextToValues2() {
        int lastOrderId = getLastOrderId();
        if (lastOrderId != -1) {
            txtorderId.setText(String.valueOf(lastOrderId));
        } else {
            txtorderId.setText("No orders found");
        }
    }

    private void setTextToValues(Product newValue){
        txtproductId.setText(String.valueOf(newValue.getProductId()));
        txtproductName.setText(newValue.getProductName());
        txtprice.setText(String.valueOf(newValue.getPrice()));
        txtquantity.setText(String.valueOf(newValue.getQuantity()));
        txtdiscount.setText(newValue.getDiscount());
    }

    private void loadOrderTable() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQuantity2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice2.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        orderTable.setItems(orderData);
    }
    private void loadTable() {
        List<Product> allProducts = productService.getAllProducts();
        ObservableList<Product> observableProducts = FXCollections.observableArrayList(allProducts);
        productTable.setItems(observableProducts);
        List<Order> allOrder = orderService.getAllOrder();
        ObservableList<Order> observableOrders = FXCollections.observableArrayList(allOrder);

    }
    public int getLastOrderId() {
        int lastOrderId = -1;
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT MAX(o.orderId) FROM Order o";
            Integer result = (Integer) session.createQuery(hql).uniqueResult();
            lastOrderId = (result != null ? result + 1 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastOrderId;
    }

//    private Connection connection;
//
//    public OrderController() {
//        try {this.connection = DBConnection.getInstance().getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}