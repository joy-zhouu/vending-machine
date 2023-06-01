package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceLayerImplTest {
    public static VendingMachineServiceLayer service;
    public VendingMachineServiceLayerImplTest(){
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void loadProductsInStock() {
        try{
            BigDecimal bd = new BigDecimal("2.50");
            Product product = new Product("1", "Cake", bd, 9);
            Map<String, Product> expectedProduct = new HashMap<>();
            expectedProduct.put("1", product);

            Map<String, Product> result = service.loadProductsInStock();
            assertEquals(expectedProduct, result, "Test products loaded from the file.");
        } catch(VendingMachinePersistenceException e){
            fail("Product was valid. No exception should have been thrown.");
        }
    }

    @Test
    void saveProductsList() {
    }

    @Test
    public void getChosenProduct() throws VendingMachineNoItemInventoryException {
        BigDecimal bd = new BigDecimal("2.50");
        Product expectedProduct = new Product("1", "Cake", bd, 9);
        Product resultProduct = service.getChosenProduct("1");

        assertEquals(expectedProduct, resultProduct, "check both products are same");
        assertEquals(expectedProduct.getProductName(), resultProduct.getProductName(), "Check if both names are equals.");
    }

    @Test
    void checkSufficientMoneyToBuyProduct() throws VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
        try{
            BigDecimal inputAmount = new BigDecimal("3.50");
            Product product = service.getChosenProduct("1");
            service.checkSufficientMoneyToBuyProduct(inputAmount, product);
        } catch(VendingMachineInsufficientFundsException e){
            fail("Sufficient funds to buy product. No exception should have been thrown.");
        }
    }

    @Test
    void calculateChange() throws VendingMachineNoItemInventoryException {
        BigDecimal amount = new BigDecimal("5");
        Product product = service.getChosenProduct("1");
        Change result = service.calculateChange(amount, product);

        assertEquals(10, result.getQuarters(), "Check number of quarters.");
        assertEquals(0, result.getDimes(), "Check number of dimes.");
        assertEquals(0, result.getNickels(), "Check number of nickels.");
        assertEquals(0, result.getPennies(), "Check number of pennies.");
    }

    @Test
    void updateProductSale() throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        Product product = service.getChosenProduct("1");
        assertEquals(9, product.getItemsInStock(), "Check items in stock.");
        service.updateProductSale(product);
        Product updatedProduct = service.getChosenProduct("1");
        assertEquals(8, updatedProduct.getItemsInStock(), "Check items in stock decreased by one");
    }
}