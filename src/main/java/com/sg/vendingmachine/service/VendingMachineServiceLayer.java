package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;

import java.math.BigDecimal;

public interface VendingMachineServiceLayer {

    public Map<String, Product> loadProductsInStock() throws VendingMachinePersistenceException;

    public void saveProductsList() throws VendingMachinePersistenceException;

    public Product getChosenProduct(String productId) throws VendingMachineNoItemInventoryException;

    public void checkSufficientMoneyToBuyProduct(BigDecimal inputAmount, Product product) throws VendingMachineInsufficientFundsException;

    public Change calculateChange(BigDecimal amount, Product product);

    public void updateProductSale(Product product) throws VendingMachineNoItemInventoryException;

}