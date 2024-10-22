package repository.impl;

import entity.Employee;
import entity.Supplier;
import org.hibernate.Session;
import repository.EmployeeRepository;
import util.HibernateUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static db.DBConnection.connection;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public boolean addEmployee(Employee employeeDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(employeeDTO);
        session.getTransaction().commit();
        session.close();
//        String SQL = "INSERT INTO employees (employee_id, name, company, email) VALUES (?, ?, ?, ?)";
//        try{return CrudUtil.execute(SQL,
//            employeeDTO.getEmployeeId(),
//            employeeDTO.getName(),
//            employeeDTO.getCompany(),
//            employeeDTO.getEmail());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
       return false;
    }

    @Override
    public boolean updateEmployee(Employee employeeDTO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            session.update(employeeDTO);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
//        String query = "UPDATE employees SET name = ?, company = ?, email = ? WHERE employee_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, employeeDTO.getName());
//            statement.setString(2, employeeDTO.getCompany());
//            statement.setString(3, employeeDTO.getEmail());
//            statement.setString(4, employeeDTO.getEmployeeId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
    }

    @Override
    public void removeEmployee(String employeeId) {
        String query = "DELETE FROM employees WHERE employee_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee searchEmployee(String searchKey) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Employee product = session.get(Employee.class, searchKey);
        session.getTransaction().commit();
        session.close();
        return product;
//        String query = "SELECT * FROM employees WHERE employee_id = ? OR name = ? OR email = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, searchKey);
//            statement.setString(2, searchKey);
//            statement.setString(3, searchKey);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new Employee(
//                        resultSet.getString("employee_id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("company"),
//                        resultSet.getString("email")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Employee> suppliers = session.createQuery("from Employee", Employee.class).list();
        session.getTransaction().commit();
        session.close();
        return suppliers;
//        List<Employee> employeeDTOS = new ArrayList<>();
//        String SQL = "SELECT * FROM employees";
//        try {
//            ResultSet resultSet = CrudUtil.execute(SQL);
//            while (resultSet.next()) {
//                employeeDTOS.add( new Employee(
//                        resultSet.getString("employee_id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("company"),
//                        resultSet.getString("email")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employeeDTOS;
    }
}
