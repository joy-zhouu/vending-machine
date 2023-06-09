package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class Change {
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public Change(BigDecimal amount){
        this.quarters = amount.divide(new BigDecimal("0.25")).intValue();
        amount = amount.remainder(new BigDecimal("0.25"));
        this.dimes = amount.divide(new BigDecimal("0.10")).intValue();
        amount = amount.remainder(new BigDecimal("0.10"));
        this.nickels = amount.divide(new BigDecimal("0.05")).intValue();
        amount = amount.remainder(new BigDecimal("0.05"));
        this.pennies = amount.divide(new BigDecimal("0.01")).intValue();
    }

    //we don't need setters because we use constructor to initialize the properties.

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
}
