import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigDecimal;
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