package com.sg.vendingmachine.service;

public class VendingMachineDuplicateIdException extends Exception{


    public VendingMachineDuplicateIdException(String message) {
        super(message);
    }

    public VendingMachineDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }

}
