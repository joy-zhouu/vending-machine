package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    BigDecimal bd = new BigDecimal("2.50");
    Product onlyProduct = new Product("1", "Cake", bd, 9);



    @Override
    public Product addProduct(String productId, Product product) {
        if (productId.equals(onlyProduct.getProductId())){
            return onlyProduct;
        } else{
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(onlyProduct);
        return productList;
    }

    @Override
    public List<String> getAllProductIds() {
        List<String> productListIds = new ArrayList<>();
        productListIds.add(onlyProduct.getProductId());
        return productListIds;
    }

    @Override
    public Product getProduct(String productId) {
        if (productId.equals(onlyProduct.getProductId())){
            return onlyProduct;
        } else{
            return null;
        }
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        if (productId.equals(onlyProduct.getProductId())){
            return onlyProduct;
        } else{
            return null;
        }
    }

    @Override
    public Product removeProduct(String productId) {
        if (productId.equals(onlyProduct.getProductId())){
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        Map<String, Product> productLoad = new HashMap<>();
        productLoad.put(onlyProduct.getProductId(), onlyProduct);
        return productLoad;
    }

    @Override
    public void writeProductsToFile() throws VendingMachinePersistenceException {

    }
}