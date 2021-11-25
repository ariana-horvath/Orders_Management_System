package presentation.controller;

import bll.OrderBLL;
import exception.InputValidationFailedException;
import model.*;
import presentation.view.OrderScreen;
import javax.swing.*;
import java.util.List;

/**
 * The type Order controller.
 *  - opens the frame for managing orders and initializes listeners for the buttons
 *  - validates the input from GUI to be non-empty and the integers to be integers, creates a new Order
 * and passes it to the OrderBLL for validation before making operations in the database.
 * @author Ariana Horvath
 */
public class OrderController {

    private OrderScreen orderScreen;
    private OrderBLL orderBLL;

    /**
     * Instantiates a new Order controller.
     */
    public OrderController(){
        orderScreen = new OrderScreen();
        orderBLL = new OrderBLL();
        initializeListeners();
    }

    /**
     * Initialize listeners.
     */
    public void initializeListeners() {
        orderScreen.getCancelButton().addActionListener(e->{
            orderScreen.setVisible(false);
        });

        orderScreen.getViewButton().addActionListener(e->{
                List<Order> orders = orderBLL.getOrderDAO().findAll();
                JTable ordersTable = orderBLL.getOrderDAO().createTable(orders);
                orderScreen.addTable(ordersTable);
        });

        orderScreen.getViewClientsButton().addActionListener(e->{
            List<Client> clients = orderScreen.getClientDAO().findAll();
            JTable clientsTable = orderScreen.getClientDAO().createTable(clients);
            orderScreen.addTable(clientsTable);
        });

        orderScreen.getViewProductsButton().addActionListener(e->{
            List<Product> products = orderScreen.getProductDAO().findAll();
            JTable productsTable = orderScreen.getProductDAO().createTable(products);
            orderScreen.addTable(productsTable);
        });

        orderScreen.getInsertButton().addActionListener(e->{
            try{
                validateTextField(orderScreen.getQuantityTextField().getText());
                int quantity = Integer.parseInt(orderScreen.getQuantityTextField().getText());
                orderBLL.insertOrder(new Order(Integer.parseInt(orderScreen.getjComboBoxClient().getSelectedItem().toString()),
                                               Integer.parseInt(orderScreen.getjComboBoxProduct().getSelectedItem().toString()), quantity));
                orderScreen.displayInformationMessage("Order successfully created.");
            } catch (NumberFormatException ex) {
                orderScreen.displayInformationMessage("Quantity is not a number.");
            } catch (InputValidationFailedException ex2) {
                orderScreen.displayErrorMessage(ex2);
            }
        });

        orderScreen.getDeleteButton().addActionListener(e->{
        try {
            int id = Integer.parseInt(orderScreen.getOrderIDTextField().getText());
            orderBLL.deleteOrder(id);
            orderScreen.displayInformationMessage("Order successfully deleted.");

        } catch (NumberFormatException ex) {
            orderScreen.displayInformationMessage("ID is not a number.");
        } catch (InputValidationFailedException ex2) {
            orderScreen.displayErrorMessage(ex2);
        }
        });

        orderScreen.getUpdateButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(orderScreen.getOrderIDTextField().getText());
                validateTextField(orderScreen.getOrderIDTextField().getText());
                validateTextField(orderScreen.getQuantityTextField().getText());
                int quantity = Integer.parseInt(orderScreen.getQuantityTextField().getText());
                orderBLL.updateOrder(new Order(Integer.parseInt(orderScreen.getjComboBoxClient().getSelectedItem().toString()),
                                         Integer.parseInt(orderScreen.getjComboBoxProduct().getSelectedItem().toString()), quantity), id);
                orderScreen.displayInformationMessage("Order successfully updated.");
            } catch (NumberFormatException ex) {
                orderScreen.displayInformationMessage("ID or quantity is not number.");
            } catch (InputValidationFailedException ex2) {
                orderScreen.displayErrorMessage(ex2);
            }
        });
    }

    /**
     * Validate text field.
     *
     * @param string the string
     */
    public void validateTextField(String string) {
        if(string.compareTo("") == 0)
            throw new InputValidationFailedException("Input cannot be empty.");
    }
}
