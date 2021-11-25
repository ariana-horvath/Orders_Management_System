package presentation.view;

import javax.swing.*;
import java.awt.*;

/**
 * The type Product screen - the GUI for managing Products.
 * @author Ariana Horvath
 */
public class ProductScreen extends JFrame{

    private JButton cancelButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewButton;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField stockTextField;

    /**
     * Instantiates a new Product screen.
     */
    public ProductScreen(){
        this.setTitle("Products Management");
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
        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(20,20,70,30);
        idLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        idLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(80, 20, 300, 30);
        idTextField.setBackground(new Color(172, 248, 206));
        panel.add(idTextField);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20,60,70,30);
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(80, 60, 300, 30);
        nameTextField.setBackground(new Color(172, 248, 206));
        panel.add(nameTextField);

        JLabel stockLabel = new JLabel("Stock: ");
        stockLabel.setBounds(20,100,70,30);
        stockLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        stockLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(stockLabel);

        stockTextField = new JTextField();
        stockTextField.setBounds(80, 100, 300, 30);
        stockTextField.setBackground(new Color(172, 248, 206));
        panel.add(stockTextField);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(240, 420, 210, 30);
        cancelButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        cancelButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(cancelButton);

        insertButton = new JButton("Insert Product");
        insertButton.setBounds(470, 20, 130, 30);
        insertButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        insertButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(insertButton);

        updateButton = new JButton("Update Product");
        updateButton.setBounds(470, 60, 130, 30);
        updateButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        updateButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(updateButton);

        deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(470, 100, 130, 30);
        deleteButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        deleteButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(deleteButton);

        viewButton = new JButton("View Products");
        viewButton.setBounds(470, 140, 130, 30);
        viewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        viewButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(viewButton);
    }

    /**
     * Add table.
     *
     * @param productsTable the products table
     */
    public void addTable(JTable productsTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 230, 650, 170);
        scrollPane.setBackground(new Color(172, 248, 206));

        productsTable.setVisible(true);
        productsTable.setEnabled(true);
        scrollPane.setViewportView(productsTable);
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
     * Gets id text field.
     *
     * @return the id text field
     */
    public JTextField getIdTextField() {
        return idTextField;
    }


    /**
     * Gets name text field.
     *
     * @return the name text field
     */
    public JTextField getNameTextField() {
        return nameTextField;
    }


    /**
     * Gets stock text field.
     *
     * @return the stock text field
     */
    public JTextField getStockTextField() {
        return stockTextField;
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
     * Sets id text field.
     *
     * @param idTextField the id text field
     */
    public void setIdTextField(JTextField idTextField) {
        this.idTextField = idTextField;
    }

    /**
     * Sets name text field.
     *
     * @param nameTextField the name text field
     */
    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    /**
     * Sets stock text field.
     *
     * @param stockTextField the stock text field
     */
    public void setStockTextField(JTextField stockTextField) {
        this.stockTextField = stockTextField;
    }
}
