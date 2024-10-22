package dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class SupplierDTO extends RecursiveTreeObject<SupplierDTO> {

    private int id;
    private String name;
    private String company;
    private String email;
    private String suppliedItem;
    public SupplierDTO(int id, String name, String company, String email, String suppliedItem) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.email = email;
        this.suppliedItem = suppliedItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuppliedItem() {
        return suppliedItem;
    }

    public void setSuppliedItem(String suppliedItem) {
        this.suppliedItem = suppliedItem;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", suppliedItem=" + suppliedItem +
                '}';
    }
}
