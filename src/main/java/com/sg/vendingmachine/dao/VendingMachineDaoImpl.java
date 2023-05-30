package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;

import java.util.List;
import java.util.Map;

public class VendingMachineDaoImpl implements VendingMachineDao {

    /**
     * @param productId
     * @param product
     * @return
     */
    @Override
    public Product addProduct(String productId, Product product) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<String> getAllProductIds() {
        return null;
    }

    /**
     * @param productId
     * @return
     */
    @Override
    public Product getProduct(String productId) {
        return null;
    }

    /**
     * @param productId
     * @param product
     * @return
     */
    @Override
    public Product updateProduct(String productId, Product product) {
        return null;
    }

    /**
     * @param productId
     * @return
     */
    @Override
    public Product removeProduct(String productId) {
        return null;
    }

    /**
     * @return
     * @throws VendingMachinePersistenceException
     */
    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        return null;
    }

    /**
     * @throws VendingMachinePersistenceException
     */
    @Override
    public void writeProductsToFile() throws VendingMachinePersistenceException {

    }
}
