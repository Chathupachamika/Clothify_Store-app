package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.Supplier;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.SupplierService;
import service.impl.SupplierServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colItem;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<Supplier> productTable;

    @FXML
    private JFXTextField txtGetSearchNameorEmail;

    @FXML
    private JFXTextField txtProductId1;

    @FXML
    private JFXTextField txtcompanyField;

    @FXML
    private JFXTextField txtemailField;


    @FXML
    private JFXComboBox<?> cmbCategory;

    @FXML
    private JFXTextField txtsupplierIdField;

    @FXML
    private JFXTextField txtsupplierNameField;

    private final SupplierService supplierService = new SupplierServiceImpl();
    private ObservableList<Supplier> supplierDTOList = FXCollections.observableArrayList();
    @FXML
    void btnAddSupplier(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtsupplierIdField.getText());
            String name = txtsupplierNameField.getText();
            String company = txtcompanyField.getText();
            String email = txtemailField.getText();
            String item = String.valueOf(cmbCategory.getValue());
            Supplier supplier = new Supplier(id, name, company, email, item);
            if (supplierService.addSupplier(supplier)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier added successfully!");
                System.out.println(supplier);
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add supplier. Please try again.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for ID, Price, Quantity, and Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier added successfully!");
        }
    }

    @FXML
    void btnReload(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnRemoveSupplier(ActionEvent event) {
        try {
            int supplieId = Integer.parseInt(txtProductId1.getText());
            if (supplierService.removeSupplier(supplieId)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier removed successfully.");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to remove supplier.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid number for Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    void btnSearchSupplier(ActionEvent event) {
        try {
            int productId = Integer.parseInt(txtGetSearchNameorEmail.getText());
            Supplier searchedProduct = supplierService.searchSupplier(productId);

            if (searchedProduct != null) {
                ObservableList<Supplier> observableProducts = FXCollections.observableArrayList(searchedProduct);
                productTable.setItems(observableProducts);
            } else {
                showAlert(Alert.AlertType.WARNING, "No Results", "No supplier found with the given supplier ID.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid number for Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    void btnUpdateSupplier(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtsupplierIdField.getText());
            String name = txtsupplierNameField.getText();
            String company = txtcompanyField.getText();
            String email = txtemailField.getText();
            String item = String.valueOf(cmbCategory.getValue());
            Supplier supplierDTO = new Supplier(id, name, company, email, item);
            if (supplierService.updateSupplier(supplierDTO)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Supplier updated successfully!");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to supplier product.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for ID, Price, Quantity, and Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("suppliedItem"));
        loadTable();
        productTable.getSelectionModel().selectedItemProperty().addListener(((ObservableValue<? extends Supplier> observable, Supplier oldValue, Supplier newValue) -> {
            if (newValue != null) {
                setTextToValues(newValue);
            }
        }));
    }
    private void setTextToValues(Supplier newValue){
        txtsupplierIdField.setText(String.valueOf(newValue.getId()));
        txtsupplierNameField.setText(newValue.getName());
        txtcompanyField.setText(String.valueOf(newValue.getCompany()));
        txtemailField.setText(String.valueOf(newValue.getEmail()));
    }
    private void loadTable() {
        List<Supplier> allProducts = supplierService.getAllSupplier();
        ObservableList<Supplier> observableSupplierDTO = FXCollections.observableArrayList(allProducts);
        System.out.println(allProducts);
        productTable.setItems(observableSupplierDTO);
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-background-color: #FFFAF0; -fx-font-family: 'Verdana'; " +
                "-fx-font-size: 14px; -fx-text-fill: #333;");
        alert.getDialogPane().lookupButton(ButtonType.OK).setStyle("-fx-background-color: #033E3E; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold;");
        alert.showAndWait();

    }
}
