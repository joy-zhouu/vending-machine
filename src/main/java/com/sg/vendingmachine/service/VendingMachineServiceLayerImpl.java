package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Function;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {


    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }



    @Override
    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException {
        Map<String, Product> productsInStock = dao.loadProductsFromFile()
                .values()
                .stream()
                .filter(p -> p.getItemsInStock() > 0)
                .peek(p -> {
                    try {
                        auditDao.writeAuditEntry("Product Id: " + p.getProductId());
                    } catch (VendingMachinePersistenceException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));

        return productsInStock;
    }

    // old ways before lamdas and streams
  /*  public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException {
        Map<String, Product> productsInStock = new HashMap<>();
        for (Product p: dao.loadProductsFromFile().values()) {
            if (p.getItemsInStock() < 1) {
                auditDao.writeAuditEntry("Product Id: " + p.getProductId());
            } else {
                productsInStock.put(p.getProductId(), p);
            }
        }
        return productsInStock;
    }*/

    @Override
    public void saveProductsList() throws VendingMachinePersistenceException {
        dao.writeProductsToFile();
        auditDao.writeAuditEntry("Product list saved to file");

    }

    @Override
    public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException {
        validateProductInStock(productId);
        return dao.getProduct(productId);

    }

    @Override
    public void checkSufficientMoneyToBuyProduct(BigDecimal inputAmount, Product product) throws VendingMachineInsufficientFundsException {
        if (inputAmount.compareTo(product.getPrice()) < 0) {
            throw new VendingMachineInsufficientFundsException("Insufficient Funds");
        }

    }

    @Override
    public Change calculateChange(BigDecimal amount, Product product) {
        BigDecimal change = amount.subtract(product.getPrice());
        return new Change(change);
    }

    @Override
    public void updateProductSale(Product product) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        if (product.getItemsInStock() > 0) {
            product.setItemsInStock(product.getItemsInStock() - 1);
        } else {
            throw new VendingMachineNoItemInventoryException("No more items");
        }
        dao.updateProduct(product.getProductId(), product);
        auditDao.writeAuditEntry("Product Id: " + product.getProductId());

    }

    private void validateProductInStock(String productId) throws VendingMachineNoItemInventoryException {
        List<String> ids = dao.getAllProductIds();
        Product product = dao.getProduct(productId);
        if (!ids.contains(productId) || (product.getItemsInStock() < 1 )) {
            throw new VendingMachineNoItemInventoryException("No more items");
        }
    }
}
