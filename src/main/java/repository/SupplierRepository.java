package repository;

import entity.Supplier;

import java.util.List;

public interface SupplierRepository {
    boolean addSupplier(Supplier supplierDTO);
    boolean updateSupplier(Supplier supplierDTO);
    boolean removeSupplier(int supplierId);
    Supplier searchSupplier(int supplierId);
    List<Supplier> getAllSupplier();
}
