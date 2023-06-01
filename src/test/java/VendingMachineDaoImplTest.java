import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dto.Product;
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

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testProducts.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    public VendingMachineDaoImplTest() {
    }

    @Test
//    addProduct and getProduct being tested
    void addGetProduct() {
//        create a product
        Product chips = new Product("1","Chips",new BigDecimal("2.00"),10);
//      Add the products to the DAO
        testDao.addProduct(chips.getProductId(), chips);
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
    void removeProduct() {
    }

    @Test
    void loadProductsFromFile() throws Exception{
        System.out.println("loadProductsFromFile");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Cake", bd, 8);
        bd = new BigDecimal("2.35");
        Product p2 = new Product("5", "Soda", bd, 5);
        Map<String, Product> result = testDao.loadProductsFromFile();
        Map<String, Product> expResult = new TreeMap<>();
        expResult.put("1", p1);
        expResult.put("5", p2);

        assertEquals(expResult, result, "Test Loading Products");
    }

    @Test
    void writeProductsToFile() {
    }
}