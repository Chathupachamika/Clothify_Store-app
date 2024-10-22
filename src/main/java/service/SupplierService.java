package service;

import entity.Supplier;

import java.util.List;

public interface SupplierService {
    boolean addSupplier(Supplier supplierDTO);
    boolean updateSupplier(Supplier supplierDTO);
    boolean removeSupplier(int supplierId);
    Supplier searchSupplier(int supplierId);
    List<Supplier> getAllSupplier();
}
