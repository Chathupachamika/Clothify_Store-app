package entity;
import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
public class Employee {
    @Id
    private String employeeId;

    private String name;
    private String company;
    private String email;


    public Employee() {}

    public Employee(String employeeId, String name, String company, String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.company = company;
        this.email = email;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
