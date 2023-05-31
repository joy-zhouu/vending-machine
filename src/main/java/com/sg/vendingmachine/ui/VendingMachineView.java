package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.CoinValue;
import com.sg.vendingmachine.dto.Product;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
  private UserIO io;
  public VendingMachineView(UserIO io) {
    this.io = io;
  }
  public void displayVendingMachineWelcome() {
    io.print("Welcome to the Vending Machine!\n");
  }

  public void displayProductHeader() {
    io.print("No\tProduct\t\tPrice");
    io.print("------------------------------");
  }

  public void displayProduct(Product product) {
    io.print(product.getProductId() + "\t" + product.getProductName());
  }

  public BigDecimal promptUserMoneyInput() {
    return io.readBigDecimal("\nPlease put in how much money you have: ");
  }

  public String promptUserProductChoice() {
    return io.readString("Please select which product you'd like to purchase: ");
  }

  public void displayUserChoiceOfProduct(Product product) {
    io.print("You have chosen " + product.getProductName() + ".");
  }

  public void displayUserMoneyInput(BigDecimal amount) {
    io.print("You have deposited $" + amount + ".");
  }

  public void displayChangeDue(Change change) {
    io.print("Change due: ");
    io.print(CoinValue.QUARTERS + ": " + change.getQuarters());
    io.print(CoinValue.DIMES + ": " + change.getDimes());
    io.print(CoinValue.NICKELS + ": " + change.getNickels());
    io.print(CoinValue.PENNIES + ": " + change.getPennies());
  }

  public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
  }

  public boolean toExit() {
    String answer = io.readString("Do you want to exit the vending machine?");
    if (answer.startsWith("y")) {
      return true;
    }
    return false;
  }

  public void displayFinalMessage() {
    io.print("Goodbye!");
  }

  public void displayUserResponse() {
    io.print("Do you want to make another selection?");
  }
}
