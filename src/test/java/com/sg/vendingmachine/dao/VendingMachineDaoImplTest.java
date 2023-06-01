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

//        Get the Product from the DAO
        Product retrievedProduct= testDao.getProduct(chips.getProductId());

        // Check the data is equal
        assertEquals(chips.getProductId(), retrievedProduct.getProductId(), "Checking product id");
        assertEquals(chips.getProductName(), retrievedProduct.getProductName(), "Checking product Name");
        assertEquals(chips.getPrice(), retrievedProduct.getPrice(), "Checking Product Price()");
        assertEquals(chips.getItemsInStock(), retrievedProduct.getItemsInStock(), "Checking product stock amount");
    }

    @Test
    void addGetAllProducts() throws VendingMachinePersistenceException {
        // create 2 products
        Product chips = new Product("1","Chips",new BigDecimal("2.00"),10);
        Product water = new Product("2","Water",new BigDecimal("1.00"),10);

        // Add both our products to the DAO
        testDao.addProduct(chips.getProductId(), chips);
        testDao.addProduct(water.getProductId(), water);

        // Retrieve the list of all products within the DAO
        List<Product> allProducts = testDao.getAllProducts();

        // First check the general contents of the list that it isn't null, then that there are 2 products
        assertNotNull(allProducts,"The list of products must not be null" );
        assertEquals(2, allProducts.size(), "List of products should have 2 products");

        // Then the specifics of each product are contained within the List
        assertTrue(testDao.getAllProducts().contains(chips),"The list of products should include chips");
        assertTrue(testDao.getAllProducts().contains(water), "The list of products should include water");
    }

    @Test
    void addGetAllProductIds() throws VendingMachinePersistenceException {
        // create 3 products
        Product chips = new Product("1","Chips",new BigDecimal("2.00"),10);
        Product water = new Product("2","Water",new BigDecimal("1.00"),10);
        Product candy = new Product("3","Candy",new BigDecimal("2.00"),10);

        // Add products to the DAO
        testDao.addProduct(chips.getProductId(), chips);
        testDao.addProduct(water.getProductId(), water);
        testDao.addProduct(candy.getProductId(), candy);

        // Retrieve the list of all products IDS within the DAO
        List<String> allProducts = testDao.getAllProductIds();

        // First check the general contents of the list that it isn't null, then that there are 3 products
        assertNotNull(allProducts,"The list of products must not be null" );
        assertEquals(3, allProducts.size(), "List of products should have 3 products");

        // Then the specifics of each product are contained within the List
        assertTrue(testDao.getAllProductIds().contains(chips.getProductId()),"The list of products should include chips as #1");
        assertTrue(testDao.getAllProductIds().contains(water.getProductId()),"The list of products should include water as #2");
        assertTrue(testDao.getAllProductIds().contains(candy.getProductId()),"The list of products should include candy as #3");
    }

    @Test
    void updateProduct() {

    }

    @Test
    void removeProduct() {
    }

    @Test
    void loadProductsFromFile() throws Exception{
        System.out.println("loadProductsFromFile");
        BigDecimal bd = new BigDecimal("1.50");
        Product p1 = new Product("3", "Chips", bd, 10);
        bd = new BigDecimal("2.35");
        Product p2 = new Product("5", "Soda", bd, 5);
        Map<String, Product> result = testDao.loadProductsFromFile();
        Map<String, Product> expResult = new TreeMap<>();
        expResult.put("1", p1);
        expResult.put("2", p2);

        assertEquals(expResult, result, "Test Loading Products");
    }

    @Test
    void writeProductsToFile() {
    }
}