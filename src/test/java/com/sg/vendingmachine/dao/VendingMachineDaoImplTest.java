package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VendingMachineDaoImplTest {
    VendingMachineDao testDao;

    public VendingMachineDaoImplTest() {
    }
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testProducts.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @Test
//    addProduct and getProduct being tested
    public void addGetProduct() throws VendingMachinePersistenceException {
//        create a product
        Product chips = new Product("1","Chips",new BigDecimal("2.00"),10);
//      Add the products to the DAO
        testDao.addProduct(chips.getProductId(), chips);

//        testDao.addProduct("1", chips);
//        Get the Student from the DAO
        Product retrievedProduct= testDao.getProduct(chips.getProductId());

        // Check the data is equal
        assertEquals(chips.getProductId(), retrievedProduct.getProductId(), "Checking product id");
        assertEquals(chips.getProductName(), retrievedProduct.getProductName(), "Checking product Name");
        assertEquals(chips.getPrice(), retrievedProduct.getPrice(), "Checking Product Price()");
        assertEquals(chips.getItemsInStock(), retrievedProduct.getItemsInStock(), "Checking product stock amount");
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getAllProductIds() {
    }

    @Test
    void getProduct() {
    }

    @Test
    void updateProduct() throws VendingMachinePersistenceException {
        System.out.println("updateProduct");
        BigDecimal bd = new BigDecimal("1.50");
        Product p1 = new Product("3", "Chips", bd, 10);
        bd = new BigDecimal("2.35");
        testDao.addProduct(p1.getProductId(), p1);
        p1.setProductName("Water");
        p1.setPrice(bd);
        p1.setItemsInStock(12);
        testDao.updateProduct(p1.getProductId(), p1);

        Product result = testDao.updateProduct(p1.getProductId(), p1);
        Product expResult = new Product("3", "Water", bd, 12);

        assertEquals(expResult, result, "Updated product");
        assertEquals("3", result.getProductId());
        assertEquals("Water", result.getProductName());
        assertEquals(bd, result.getPrice());
        assertEquals(12, result.getItemsInStock());
    }

    @Test
    void removeProduct() throws VendingMachinePersistenceException {
        System.out.println("removeProduct");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Cake", bd, 9);
        bd = new BigDecimal("1.05");
        Product p2 = new Product("2", "Water", bd, 12);
        testDao.addProduct(p1.getProductId(), p1);
        testDao.addProduct(p2.getProductId(), p2);

        Product removedProduct = testDao.removeProduct(p1.getProductId());
        assertEquals(removedProduct, p1, "The removed product");
        List<Product> result = testDao.getAllProducts();
        assertNotNull(result, "The list of products must not be null.");
        assertEquals(1, result.size(), "List of products");

        removedProduct = testDao.removeProduct(p2.getProductId());
        assertEquals(removedProduct, p2, "The removed product");

        result = testDao.getAllProducts();
        assertEquals(0, result.size(), "List of products");

        Product retrievedProduct = testDao.getProduct(p1.getProductId());
        assertNull(retrievedProduct, "Cake was removed, should be null");
        retrievedProduct = testDao.getProduct(p2.getProductId());
        assertNull(retrievedProduct, "Water was removed, should be null");
    }

    @Test
    void loadProductsFromFile() throws Exception{
        System.out.println("loadProductsFromFile");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Cake", bd, 9);
        bd = new BigDecimal("2.35");
        Product p2 = new Product("5", "Soda", bd, 5);
        testDao.addProduct(p1.getProductId(), p1);
        testDao.addProduct(p2.getProductId(), p2);

        Map<String, Product> result = testDao.loadProductsFromFile();
        Map<String, Product> expResult = new TreeMap<>();
        expResult.put("1", p1);
        expResult.put("5", p2);

        assertEquals(expResult, result, "Test Loading Products");
    }

    @Test
    void writeProductsToFile() throws Exception{
        System.out.println("writeProductsToFile");
        BigDecimal bd = new BigDecimal("2.35");
        Product p1 = new Product("5", "Soda", bd, 5);
        testDao.addProduct(p1.getProductId(), p1);

        testDao.writeProductsToFile();
        Map<String, Product> result = testDao.loadProductsFromFile();
        Map<String, Product> expResult = new TreeMap<>();
        expResult.put("5", p1);

        assertEquals(expResult, result, "Test Writing Products");
    }

}