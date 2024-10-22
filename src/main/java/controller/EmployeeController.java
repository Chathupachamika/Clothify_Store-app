package controller;

import com.jfoenix.controls.JFXTextField;
import entity.Employee;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.EmployeeRepository;
import repository.impl.EmployeeRepositoryImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmployeeCompany;

    @FXML
    private TableColumn<?, ?> colEmployeeEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeID;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private JFXTextField txtGetSearchNameorEmail;

    @FXML
    private JFXTextField txtemployeeCompany;

    @FXML
    private JFXTextField txtemployeeEmail;

    @FXML
    private JFXTextField txtemployeeID;

    @FXML
    private JFXTextField txtemployeeName1;

    private ObservableList<Employee> employeeDTOList = FXCollections.observableArrayList();
    private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    @FXML
    void btnAddEmployee(ActionEvent event) {
        try {
            String id = txtemployeeID.getText();
            String productName = txtemployeeName1.getText();
            String email = txtemployeeEmail.getText();  // Corrected to String
            String company = txtemployeeCompany.getText(); // Placeholder for actual image

            Employee employeeDTO = new Employee(id, productName, email, company);
            if (employeeRepository.addEmployee(employeeDTO)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee updated successfully!");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for ID, Price, Quantity, and Supplier ID.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        clearFields();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearFields(){
        txtemployeeCompany.clear();
        txtemployeeID.clear();
        txtemployeeEmail.clear();
        txtemployeeName1.clear();
    }
    @FXML
    void btnRemoveEmployee(ActionEvent event) {
        Employee selectedEmployeeDTO = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployeeDTO != null) {
            employeeRepository.removeEmployee(selectedEmployeeDTO.getEmployeeId());
            employeeDTOList.remove(selectedEmployeeDTO);
            employeeTable.setItems(employeeDTOList);
        }
        loadTable();
    }

    @FXML
    void btnSearchEmployee(ActionEvent event) {
        String searchKey = txtGetSearchNameorEmail.getText();
        Employee employeeDTO = employeeRepository.searchEmployee(searchKey);
        if (employeeDTO != null) {
            txtemployeeID.setText(employeeDTO.getEmployeeId());
            txtemployeeName1.setText(employeeDTO.getName());
            txtemployeeCompany.setText(employeeDTO.getCompany());
            txtemployeeEmail.setText(employeeDTO.getEmail());
        }
    }

    @FXML
    void btnUpdateEmployee(ActionEvent event) {
        Employee selectedEmployeeDTO = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployeeDTO != null) {
            selectedEmployeeDTO.setName(txtemployeeName1.getText());
            selectedEmployeeDTO.setCompany(txtemployeeCompany.getText());
            selectedEmployeeDTO.setEmail(txtemployeeEmail.getText());

            employeeRepository.updateEmployee(selectedEmployeeDTO);
            employeeTable.refresh();
            clearFields();
        }
    }
    @FXML
    void btnReloadEmployee(ActionEvent event) {
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadTable();
        employeeTable.getSelectionModel().selectedItemProperty().addListener(((ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) -> {
            if (newValue != null) {
                setTextToValues(newValue);
            }
        }));
}

    private void loadTable() {
        List<Employee> allEmployeeDTOS = employeeRepository.getAllEmployees();
        ObservableList<Employee> observableEmployeeDTOS = FXCollections.observableArrayList(allEmployeeDTOS);
        System.out.println(allEmployeeDTOS);
        employeeTable.setItems(observableEmployeeDTOS);
    }
    private void setTextToValues(Employee newValue) {
        txtemployeeID.setText(newValue.getEmployeeId());
        txtemployeeName1.setText(newValue.getName());
        txtemployeeEmail.setText(newValue.getEmail());
        txtemployeeCompany.setText(newValue.getCompany());
    }
    }
