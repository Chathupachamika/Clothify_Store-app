package repository;

import entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    boolean addEmployee(Employee employeeDTO);
    boolean updateEmployee(Employee employeeDTO);
    void removeEmployee(String employeeId);
    Employee searchEmployee(String searchKey);
    List<Employee> getAllEmployees();
}
