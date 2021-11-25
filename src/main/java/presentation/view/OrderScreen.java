package presentation.view;

import dao.*;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The type Order screen - the GUI for managing Orders.
 * @author Ariana Horvath
 */
public class OrderScreen extends JFrame {
    private JButton cancelButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewButton;
    private JButton viewClientsButton;
    private JButton viewProductsButton;
    private JTextField orderIDTextField;
    private JTextField quantityTextField;
    private JComboBox<Integer> jComboBoxClient;
    private JComboBox<Integer> jComboBoxProduct;
    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();

    /**
     * Instantiates a new Order screen.
     */
    public OrderScreen(){
        this.setTitle("Orders Management");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setLocationRelativeTo(null);
        initializeForm(panel);
        this.setContentPane(panel);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(140, 246, 187));
    }

    /**
     * Initialize form.
     *
     * @param panel the panel
     */
    public void initializeForm(JPanel panel) {
        JLabel idLabel = new JLabel("Order ID: ");
        idLabel.setBounds(20,20,70,30);
        idLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        idLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(idLabel);

        orderIDTextField = new JTextField();
        orderIDTextField.setBounds(90, 20, 290, 30);
        orderIDTextField.setBackground(new Color(172, 248, 206));
        panel.add(orderIDTextField);

        JLabel clientIdLabel = new JLabel("Client ID: ");
        clientIdLabel.setBounds(20,60,70,30);
        clientIdLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        clientIdLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(clientIdLabel);

        List<Client> clients = clientDAO.findAll();
        jComboBoxClient = new JComboBox<>();
        jComboBoxClient.setBounds(90, 60, 290, 30);
        jComboBoxClient.setForeground(new Color(2, 95, 69, 255));
        jComboBoxClient.setBackground(new Color(172, 248, 206));
        for(Client c : clients){
            jComboBoxClient.addItem(c.getId());
        }
        panel.add(jComboBoxClient);

        JLabel productIDLabel = new JLabel("Product ID: ");
        productIDLabel.setBounds(20,100,70,30);
        productIDLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        productIDLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(productIDLabel);

        List<Product> products = productDAO.findAll();
        jComboBoxProduct = new JComboBox<>();
        jComboBoxProduct.setBounds(90, 100, 290, 30);
        jComboBoxProduct.setForeground(new Color(2, 95, 69, 255));
        jComboBoxProduct.setBackground(new Color(172, 248, 206));
        for(Product p : products){
            jComboBoxProduct.addItem(p.getId());
        }
        panel.add(jComboBoxProduct);

        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setBounds(20,140,70,30);
        quantityLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        quantityLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(quantityLabel);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(90, 140, 290, 30);
        quantityTextField.setBackground(new Color(172, 248, 206));
        panel.add(quantityTextField);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(240, 420, 210, 30);
        cancelButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        cancelButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(cancelButton);

        insertButton = new JButton("Insert Order");
        insertButton.setBounds(470, 20, 130, 30);
        insertButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        insertButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(insertButton);

        updateButton = new JButton("Update Order");
        updateButton.setBounds(470, 60, 130, 30);
        updateButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        updateButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(updateButton);

        deleteButton = new JButton("Delete Order");
        deleteButton.setBounds(470, 100, 130, 30);
        deleteButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        deleteButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(deleteButton);

        viewButton = new JButton("View Orders");
        viewButton.setBounds(470, 140, 130, 30);
        viewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        viewButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(viewButton);

        viewClientsButton = new JButton("View Clients");
        viewClientsButton.setBounds(90, 180, 130, 30);
        viewClientsButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        viewClientsButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(viewClientsButton);

        viewProductsButton = new JButton("View Products");
        viewProductsButton.setBounds(250, 180, 130, 30);
        viewProductsButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        viewProductsButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(viewProductsButton);
    }

    /**
     * Add table.
     *
     * @param jTable the j table
     */
    public void addTable(JTable jTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 230, 650, 170);
        scrollPane.setBackground(new Color(172, 248, 206));

        jTable.setVisible(true);
        jTable.setEnabled(true);
        scrollPane.setViewportView(jTable);
        this.getContentPane().add(scrollPane);
    }

    /**
     * Display error message.
     *
     * @param exception the exception
     */
    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(140, 246, 187));
            UI.put("Panel.background", new Color(140, 246, 187));
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Display information message.
     *
     * @param message the message
     */
    public void displayInformationMessage(String message) {
        if (!message.isEmpty()) {
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(140, 246, 187));
            UI.put("Panel.background", new Color(140, 246, 187));
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Gets cancel button.
     *
     * @return the cancel button
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Gets insert button.
     *
     * @return the insert button
     */
    public JButton getInsertButton() {
        return insertButton;
    }

    /**
     * Gets delete button.
     *
     * @return the delete button
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * Gets update button.
     *
     * @return the update button
     */
    public JButton getUpdateButton() {
        return updateButton;
    }

    /**
     * Gets view button.
     *
     * @return the view button
     */
    public JButton getViewButton() {
        return viewButton;
    }

    /**
     * Gets view clients button.
     *
     * @return the view clients button
     */
    public JButton getViewClientsButton() {
        return viewClientsButton;
    }

    /**
     * Gets view products button.
     *
     * @return the view products button
     */
    public JButton getViewProductsButton() {
        return viewProductsButton;
    }

    /**
     * Gets order id text field.
     *
     * @return the order id text field
     */
    public JTextField getOrderIDTextField() {
        return orderIDTextField;
    }

    /**
     * Gets quantity text field.
     *
     * @return the quantity text field
     */
    public JTextField getQuantityTextField() {
        return quantityTextField;
    }

    /**
     * Gets combo box client.
     *
     * @return the combo box client
     */
    public JComboBox<Integer> getjComboBoxClient() {
        return jComboBoxClient;
    }

    /**
     * Gets combo box product.
     *
     * @return the combo box product
     */
    public JComboBox<Integer> getjComboBoxProduct() {
        return jComboBoxProduct;
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
     * Gets product dao.
     *
     * @return the product dao
     */
    public ProductDAO getProductDAO() {
        return productDAO;
    }

    /**
     * Sets cancel button.
     *
     * @param cancelButton the cancel button
     */
    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    /**
     * Sets insert button.
     *
     * @param insertButton the insert button
     */
    public void setInsertButton(JButton insertButton) {
        this.insertButton = insertButton;
    }

    /**
     * Sets delete button.
     *
     * @param deleteButton the delete button
     */
    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    /**
     * Sets update button.
     *
     * @param updateButton the update button
     */
    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    /**
     * Sets view button.
     *
     * @param viewButton the view button
     */
    public void setViewButton(JButton viewButton) {
        this.viewButton = viewButton;
    }

    /**
     * Sets view clients button.
     *
     * @param viewClientsButton the view clients button
     */
    public void setViewClientsButton(JButton viewClientsButton) {
        this.viewClientsButton = viewClientsButton;
    }

    /**
     * Sets view products button.
     *
     * @param viewProductsButton the view products button
     */
    public void setViewProductsButton(JButton viewProductsButton) {
        this.viewProductsButton = viewProductsButton;
    }

    /**
     * Sets order id text field.
     *
     * @param orderIDTextField the order id text field
     */
    public void setOrderIDTextField(JTextField orderIDTextField) {
        this.orderIDTextField = orderIDTextField;
    }

    /**
     * Sets quantity text field.
     *
     * @param quantityTextField the quantity text field
     */
    public void setQuantityTextField(JTextField quantityTextField) {
        this.quantityTextField = quantityTextField;
    }

    /**
     * Sets combo box client.
     *
     * @param jComboBoxClient the j combo box client
     */
    public void setjComboBoxClient(JComboBox<Integer> jComboBoxClient) {
        this.jComboBoxClient = jComboBoxClient;
    }

    /**
     * Sets combo box product.
     *
     * @param jComboBoxProduct the j combo box product
     */
    public void setjComboBoxProduct(JComboBox<Integer> jComboBoxProduct) {
        this.jComboBoxProduct = jComboBoxProduct;
    }

    /**
     * Sets client dao.
     *
     * @param clientDAO the client dao
     */
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    /**
     * Sets product dao.
     *
     * @param productDAO the product dao
     */
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
