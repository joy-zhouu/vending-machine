package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Product;

import java.util.List;

public class VendingMachineView {
  private UserIO io;
  public VendingMachineView(UserIO io) {
    this.io = io;
  }

  public int printMenu(List<Product> productList) {
    io.print("Welcome to the Vending Machine!");
    io.print("No     Product          Price");
    io.print("-----------------------------\n");

    // Iterate through list of items and display them and their prices
    for (Product currentProduct: productList) {
      int index = productList.indexOf(currentProduct);
      String productInfo = String.format(index + "     %s          $%s",
              currentProduct.getProductName(),
              currentProduct.getPrice());
      io.print(productInfo);
    }

    return io.readInt("Please enter how much money you have (in dollars)");
  }

  public int getSelection() {
    return io.readInt("Please enter which item you'd like to purchase");
  }

  public void displayPurchaseSuccess() {
    io.print("Purchase successful.");
    // Print change
  }

  public String getContinuePurchase() {
    return io.readString("Would you like to make another purchase?");
  }

  public void displayExitBanner() {
    io.print("Good Bye!!");
  }

  public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
  }
}
