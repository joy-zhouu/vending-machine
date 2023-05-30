package com.sg.vendingmachine.dto;

import com.sg.vendingmachine.service.VendingMachinePersistenceException;

import java.math.BigDecimal;
import java.util.regex.PatternSyntaxException;

public class Product {
    private String productId;
    private String productName;
    private BigDecimal price;
    private int itemsInStock;

    private final String DELIMITER = "::";

    public Product(String productId, String productName, BigDecimal price, int itemsInStock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.itemsInStock = itemsInStock;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getItemsInStock() {
        return itemsInStock;
    }

    public void setItemsInStock(int itemsInStock) {
        this.itemsInStock = itemsInStock;
    }

    public Product(String productAsText) throws VendingMachinePersistenceException {
        try{
            String[] fields = productAsText.split(DELIMITER);
            this.productId =fields[0];
            this.productName = fields[1];
            this.price = new BigDecimal(fields[2]);
            this.itemsInStock = Integer.parseInt(fields[3]);
        } catch(PatternSyntaxException e){
            throw new VendingMachinePersistenceException(e.getMessage(), e);
        } catch(NullPointerException | NumberFormatException e){
            throw new VendingMachinePersistenceException(e.getMessage(), e);
        }
    }

    public String marshalProductAsText(){
        return productId + DELIMITER + productName + DELIMITER + price + DELIMITER + itemsInStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (itemsInStock != product.itemsInStock) return false;
        if (!productId.equals(product.productId)) return false;
        if (!productName.equals(product.productName)) return false;
        return price.equals(product.price);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + itemsInStock;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", itemsInStock=" + itemsInStock +
                '}';
    }
}
