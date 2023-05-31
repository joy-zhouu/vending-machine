package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

import java.util.List;

public class VendingMachineView {
  private UserIO io;
  public VendingMachineView(UserIO io) {
    this.io = io;
  }

  public int printMenu(List<Item> itemList) {
    io.print("Welcome to the Vending Machine!");
    io.print("No     Product          Price");
    io.print("-----------------------------\n");

    // Iterate through list of items and display them and their prices
    for (Item currentItem: itemList) {
      int index = itemList.indexOf(currentItem);
      String itemInfo = String.format(index + "     %s          $%s",
              currentItem.getItemName(),
              currentItem.getItemPrice());
      io.print(itemInfo);
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
