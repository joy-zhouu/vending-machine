package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;

import java.io.*;
import java.util.*;

public class VendingMachineDaoImpl implements VendingMachineDao {

    public final String PRODUCTS_FILE;
    private Map<String, Product> products = new HashMap<>();

    // constructor for production data
    public VendingMachineDaoImpl() {
        PRODUCTS_FILE = "products.txt";
    }
//    overloaded constructor for Test data
    public VendingMachineDaoImpl(String productsTextFile) {
        PRODUCTS_FILE = productsTextFile;
    }

    @Override
    public Product addProduct(String productId, Product product) throws VendingMachinePersistenceException {
        loadProductsFromFile();
        Product prevProduct = products.put(productId, product);
        writeProductsToFile();
        return prevProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public List<String> getAllProductIds() {
        return new ArrayList<>(products.keySet());
    }

    @Override
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        return products.replace(productId, product);
    }

    @Override
    public Product removeProduct(String productId) {
        Product removedProduct = products.remove(productId);
        return removedProduct;
    }

   // method that reads the roster file into memory
    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        Scanner scanner;
        try{
            // Create Scanner for reading the file
            scanner = new Scanner(
            new BufferedReader(
                    new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentProduct holds the most recent Product unmarshalled
        Product currentProduct;
        // Go through PRODUCTS_FILE line by line, decoding each line into a Product
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // reassign variable and pass the String currentLine as argument
            currentProduct = new Product(currentLine);
            // add product to map using the key (product id) & value (currentProduct)
            products.put(currentProduct.getProductId(), currentProduct);
        }
        // close scanner
        scanner.close();
//       return map of products
        return products;
    }

//    took code from ClassRosterServiceLayer
    @Override
    public void writeProductsToFile() throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(PRODUCTS_FILE));
        } catch (IOException ex) {
            throw new VendingMachinePersistenceException(
                    "Could not save product data.", ex);
        }
        String productAsText;
        List<Product> productList = this.getAllProducts();
        for (Product currentProduct : productList) {
            // turn a Product into a String
            productAsText = currentProduct.marshalProductAsText();
            // write the Product object to the file
            out.println(productAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
