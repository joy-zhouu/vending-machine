package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceLayer service; //access to dao through service layer

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }

    public void run() throws VendingMachinePersistenceException{
        BigDecimal moneyDeposited = new BigDecimal("0");
        Product chosenProduct = null;
        String keepGoing = "yes";
        String input;
        Scanner scan = new Scanner(System.in);

        while(keepGoing.equals("yes")){
            boolean isEnoughMoney = false;
            try{
                displayHeader();
                do{
                    productMenu();
                    moneyDeposited = userMoneyInput(moneyDeposited);
                    chosenProduct = getChosenProduct();
                    isEnoughMoney = didUserPutSufficientAmountOfMoney(moneyDeposited, chosenProduct);
                            if(toExitVendingMachine(isEnoughMoney)){
                                return;
                            }
                } while(!isEnoughMoney);

                displayUserMoneyInput(moneyDeposited);
                displayChangeReturnedToUser(moneyDeposited, chosenProduct);
                updateSoldProduct(chosenProduct);
                saveProductList();
            } catch(VendingMachinePersistenceException e){
                displayErrorMessage(e.getMessage());
            } finally {
                displayFinalMessage();
            }
            view.displayUserResponse();
            keepGoing = scan.nextLine();
        }
    }


    void displayHeader(){
        view.displayVendingMachineWelcome();
    }

    boolean didUserPutSufficientAmountOfMoney(BigDecimal userAmount, Product chosenProduct){
        try{
            service.checkSufficientMoneyToBuyProduct(userAmount, chosenProduct);
            return true;
        } catch(VendingMachineInsufficientFundsException e){
            displayErrorMessage(e.getMessage());
            displayUserMoneyInput(userAmount);
            return false;
        }
    }

    void displayUserMoneyInput(BigDecimal amount){
        view.displayUserMoneyInput(amount);
    }

    void displayChangeReturnedToUser(BigDecimal amount, Product product){
        Change change = service.calculateChange(amount, product);
        view.displayChangeDue(change);
    }

    void productMenu() throws VendingMachinePersistenceException {
        view.displayProductHeader();
        Map<String, Product> products = service.loadProductsInStock();
        for(Product p: products.values()){
            view.displayProduct(p);
        }
    }

    public BigDecimal userMoneyInput(BigDecimal amount){
        return amount.add(view.promptUserMoneyInput());
    }

    public Product getChosenProduct(){
        while(true){
            String productId = view.promptUserProductChoice();
            try{
                Product product = service.getChosenProduct(productId);
                view.displayUserChoiceOfProduct(product);
                return product;
            } catch(VendingMachineNoItemInventoryException e){
                displayErrorMessage(e.getMessage());
            }
        }
    }

    public boolean toExitVendingMachine(boolean isEnoughMoney){
        if(isEnoughMoney){
            return false;
        } else{
            return view.toExit();
        }
    }

    void updateSoldProduct(Product product) throws VendingMachinePersistenceException {
        try{
            service.updateProductSale(product);
        } catch(VendingMachineNoItemInventoryException e){
            displayErrorMessage(e.getMessage());
        }
    }

    void displayErrorMessage(String message) {
        view.displayErrorMessage(message);
    }

     void saveProductList() throws VendingMachinePersistenceException {
        service.saveProductsList();
    }

     void displayFinalMessage() {
        view.displayFinalMessage();
    }
}
