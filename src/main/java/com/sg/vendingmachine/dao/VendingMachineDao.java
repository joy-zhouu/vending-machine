package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;

import java.util.List;
import java.util.Map;

/*
/*This interface defines the methods that must be implemented by any class that wants to play the role of DAO in app
DAO- responsible for persistence and retrieval of Product data
 */
public interface VendingMachineDao {
//    CRUD operation methods:
    Product addProduct(String productId, Product product);
    List<Product> getAllProducts();
    List<String> getAllProductIds();

    Product getProduct(String productId);
    Product updateProduct(String productId, Product product);
    Product removeProduct(String productId);
    Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException;
    void writeProductsToFile() throws VendingMachinePersistenceException;

}
