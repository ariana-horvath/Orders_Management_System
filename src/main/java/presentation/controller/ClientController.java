package presentation.controller;

import bll.ClientBLL;
import exception.InputValidationFailedException;
import model.Client;
import presentation.view.ClientScreen;

import javax.swing.*;
import java.util.List;

/**
 * The type Client controller
 * - opens the frame for managing clients and initializes listeners for the buttons
 * - validates the input from GUI to be non-empty, creates a new Client
 * and passes it to the ClientBLL for validation before making operations in the database.
 * @author Ariana Horvath
 */
public class ClientController {

    private ClientScreen clientScreen;
    private ClientBLL clientBLL;

    /**
     * Instantiates a new Client controller.
     */
    public ClientController() {
        clientScreen = new ClientScreen();
        clientBLL = new ClientBLL();
        initializeListeners();
    }

    /**
     * Initialize listeners.
     */
    public void initializeListeners(){
        clientScreen.getCancelButton().addActionListener(e->{
             clientScreen.setVisible(false);
        });

        clientScreen.getViewButton().addActionListener(e->{
            List<Client> clients = clientBLL.getClientDAO().findAll();
            JTable clientsTable = clientBLL.getClientDAO().createTable(clients);
            clientScreen.addTable(clientsTable);
        });

        clientScreen.getInsertButton().addActionListener(e->{
            try {
                validateTextField(clientScreen.getNameTextField().getText());
                validateTextField(clientScreen.getEmailTextField().getText());
                validateTextField(clientScreen.getPhoneTextField().getText());
                validateTextField(clientScreen.getAddressTextField().getText());
                Client client = new Client(clientScreen.getNameTextField().getText(), clientScreen.getEmailTextField().getText(),
                                            clientScreen.getPhoneTextField().getText(),clientScreen.getAddressTextField().getText());
                clientBLL.insertClient(client);
                clientScreen.displayInformationMessage("Client successfully inserted.");
            } catch(InputValidationFailedException ex) {
                clientScreen.displayErrorMessage(ex);
            }
        });

        clientScreen.getDeleteButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(clientScreen.getIdTextField().getText());
                clientBLL.deleteClient(id);
                clientScreen.displayInformationMessage("Client successfully deleted.");
            } catch (NumberFormatException ex) {
                clientScreen.displayInformationMessage("ID is not a number.");
            } catch (InputValidationFailedException ex2) {
                clientScreen.displayErrorMessage(ex2);
            }
        });

        clientScreen.getUpdateButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(clientScreen.getIdTextField().getText());
                validateTextField(clientScreen.getNameTextField().getText());
                validateTextField(clientScreen.getEmailTextField().getText());
                validateTextField(clientScreen.getPhoneTextField().getText());
                validateTextField(clientScreen.getAddressTextField().getText());
                Client client = new Client(clientScreen.getNameTextField().getText(), clientScreen.getEmailTextField().getText(),
                        clientScreen.getPhoneTextField().getText(),clientScreen.getAddressTextField().getText());
                clientBLL.updateClient(client, id);
                clientScreen.displayInformationMessage("Client successfully updated.");
            } catch (NumberFormatException ex) {
                clientScreen.displayInformationMessage("ID is not a number.");
            } catch (InputValidationFailedException ex2) {
                clientScreen.displayErrorMessage(ex2);
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
