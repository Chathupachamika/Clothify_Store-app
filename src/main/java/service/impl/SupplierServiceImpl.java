package service.impl;

import entity.Supplier;
import repository.SupplierRepository;
import repository.impl.SupplierRepositoryImpl;
import service.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public boolean addSupplier(Supplier supplierDTO) {
        return supplierRepository.addSupplier(supplierDTO);
    }

    @Override
    public boolean updateSupplier(Supplier supplierDTO) {
        return supplierRepository.updateSupplier(supplierDTO);
    }

    @Override
    public boolean removeSupplier(int supplierId) {
        return supplierRepository.removeSupplier(supplierId);
    }

    @Override
    public Supplier searchSupplier(int supplierId) {
        return supplierRepository.searchSupplier(supplierId);
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.getAllSupplier();
    }
}
