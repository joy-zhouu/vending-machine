package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface VendingMachineServiceLayer {

    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException;

    public void saveProductsList() throws VendingMachinePersistenceException;

    public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException;

    public void checkSufficientMoneyToBuyProduct(BigDecimal inputAmount, Product product) throws VendingMachineInsufficientFundsException;

    public Change calculateChange(BigDecimal amount, Product product);

    public void updateProductSale(Product product) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException;

}