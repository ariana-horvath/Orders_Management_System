package bll;

import bll.validators.*;
import dao.ClientDAO;
import exception.InputValidationFailedException;
import model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Client bll - business logic for client - validates the client to be added/updated into the database or for delete ensures
 * that it exists.
 * @author Ariana Horvath
 */
public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Instantiates a new Client bll.
     */
    public ClientBLL(){
        validators = new ArrayList<>();
        validators.add(new ClientNameValidator());
        validators.add(new EmailValidator());
        validators.add(new PhoneNumberValidator());
        validators.add(new AddressValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * Insert client client if it is valid.
     *
     * @param client the client
     * @return the client
     */
    public Client insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return clientDAO.insert(client);
    }

    /**
     * Delete client - ensures the client to be deleted exists and if so it deletes it.
     *
     * @param id the id
     */
    public void deleteClient(int id) {
        Client client = clientDAO.findById(id);
        if (client == null)
            throw new InputValidationFailedException("Client with id "+id+" not existent.");
        clientDAO.delete(client);
    }

    /**
     * Update client - ensures the client to be updated exists and if so it updates it with the new client.
     *
     * @param client the client
     * @param id     the id
     */
    public void updateClient(Client client, int id) {
        Client existendClient = clientDAO.findById(id);
        if (existendClient == null)
            throw new InputValidationFailedException("Client with id "+id+" not existent");
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        client.setId(id);
        clientDAO.update(client);
    }

    /**
     * Gets validators.
     *
     * @return the validators
     */
    public List<Validator<Client>> getValidators() {
        return validators;
    }

    /**
     * Sets validators.
     *
     * @param validators the validators
     */
    public void setValidators(List<Validator<Client>> validators) {
        this.validators = validators;
    }

    /**
     * Gets client dao.
     *
     * @return the client dao
     */
    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    /**
     * Sets client dao.
     *
     * @param clientDAO the client dao
     */
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
