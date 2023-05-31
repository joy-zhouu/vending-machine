package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.service.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();

        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);

        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoImpl();

        // Instantiate the Audit DAO
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();

        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);

        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController myController = new VendingMachineController(myView, myService);

        // Kick off the Controller
        myController.run();
    }
}
