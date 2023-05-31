import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;

import java.util.List;
import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDao {
    @Override
    public Product addProduct(String productId, Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<String> getAllProductIds() {
        return null;
    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        return null;
    }

    @Override
    public Product removeProduct(String productId) {
        return null;
    }

    @Override
    public Map<String, Product> loadProductsFromFile() throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public void writeProductsToFile() throws VendingMachinePersistenceException {

    }
}
