package bll;

import bll.validators.OrderQuantityValidator;
import bll.validators.Validator;
import dao.*;

import exception.InputValidationFailedException;
import model.Client;
import model.Order;
import model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Order bll - business logic for order - validates the order to be added/updated into the database or for delete it ensures
 * that it exists.
 * @author Ariana Horvath
 */
public class OrderBLL {
    private Validator<Order> validator;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private ClientDAO clientDAO;

    /**
     * Instantiates a new Order bll.
     */
    public OrderBLL(){
        orderDAO = new OrderDAO();
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        validator = new OrderQuantityValidator();
    }

    /**
     * Insert order order if it is valid, has enough products in stock and creates the bill for the inserted order.
     *
     * @param order the order
     * @return the order
     */
    public Order insertOrder(Order order) {
        validator.validate(order);
        Product product = productDAO.findById(order.getProductID());
        Client client = clientDAO.findById(order.getClientID());
        if (product.getStock() < order.getQuantity()) {
            throw new InputValidationFailedException("Stock for product "+product.getName()+" is not sufficient.");
        }
        product.setStock(product.getStock() - order.getQuantity());
        productDAO.update(product);
        ArrayList<Order> orders = (ArrayList<Order>) orderDAO.findAll();
        Order lastOrder = orders.get(orders.size()-1);
        order.setId(lastOrder.getId()+1);
        createBill(order, product, client);
        return orderDAO.insert(order);
    }

    /**
     * Create bill for the order - writes in a .txt file different for each order.
     *
     * @param order   the order
     * @param product the product
     * @param client  the client
     */
    public void createBill(Order order, Product product, Client client) {
        FileWriter writer;
        try {
            writer = new FileWriter("bill" + order.getId()+".txt");
            writer.append("Bill \nOrder ID: " + order.getId() + "\nProduct: " + product.getName()+", quantity: "+ order.getQuantity()
                        + "\nClient: " + client.getName()+ ", email: "+client.getEmail()+", phone number: "+client.getPhoneNumber()+
                        "\nDelivery address: " + client.getAddress());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete order if it exists, if so it increments the product stock with the quantity from deleted order.
     *
     * @param id the id
     */
    public void deleteOrder(int id) {
        Order order = orderDAO.findById(id);
        if (order == null)
            throw new InputValidationFailedException("Order with id "+id+" not existent.");
        Product product = productDAO.findById(order.getProductID());
        product.setStock(product.getStock() + order.getQuantity());
        productDAO.update(product);
        orderDAO.delete(order);
    }

    /**
     * Update order if the new order is valid and there are enough products in stock. Updates the product stock also.
     *
     * @param order the order
     * @param id    the id
     */
    public void updateOrder(Order order, int id){
        Order existentOrder = orderDAO.findById(id);
        if (existentOrder == null)
            throw new InputValidationFailedException("Order with id "+id+" not existent.");
        validator.validate(order);
        order.setId(id);
        Product product = productDAO.findById(order.getProductID());
        if (product.getStock() + existentOrder.getQuantity() < order.getQuantity()) {
            throw new InputValidationFailedException("Stock for product "+product.getName()+" is not sufficient.");
        }
        product.setStock(product.getStock() + existentOrder.getQuantity() - order.getQuantity());
        productDAO.update(product);
        orderDAO.update(order);
    }

    /**
     * Gets validator.
     *
     * @return the validator
     */
    public Validator<Order> getValidator() {
        return validator;
    }

    /**
     * Sets validator.
     *
     * @param validator the validator
     */
    public void setValidator(Validator<Order> validator) {
        this.validator = validator;
    }

    /**
     * Gets order dao.
     *
     * @return the order dao
     */
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    /**
     * Sets order dao.
     *
     * @param orderDAO the order dao
     */
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * Gets product dao.
     *
     * @return the product dao
     */
    public ProductDAO getProductDAO() {
        return productDAO;
    }

    /**
     * Sets product dao.
     *
     * @param productDAO the product dao
     */
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
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
