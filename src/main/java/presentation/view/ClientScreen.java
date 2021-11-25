package presentation.view;

import javax.swing.*;
import java.awt.*;

/**
 * The type Client screen - the GUI for managing Clients.
 * @author Ariana Horvath
 */
public class ClientScreen extends JFrame {

    private JButton cancelButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton viewButton;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JTextField addressTextField;

    /**
     * Instantiates a new Client screen.
     */
    public ClientScreen(){
        this.setTitle("Clients Management");
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

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(20,100,70,30);
        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        emailLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(80, 100, 300, 30);
        emailTextField.setBackground(new Color(172, 248, 206));
        panel.add(emailTextField);

        JLabel phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBounds(20,140,70,30);
        phoneLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        phoneLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(80, 140, 300, 30);
        phoneTextField.setBackground(new Color(172, 248, 206));
        panel.add(phoneTextField);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(20,180,70,30);
        addressLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        addressLabel.setForeground(new Color(2, 95, 69, 255));
        panel.add(addressLabel);

        addressTextField = new JTextField();
        addressTextField.setBounds(80, 180, 300, 30);
        addressTextField.setBackground(new Color(172, 248, 206));
        panel.add(addressTextField);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(240, 420, 210, 30);
        cancelButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        cancelButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(cancelButton);

        insertButton = new JButton("Insert Client");
        insertButton.setBounds(470, 20, 130, 30);
        insertButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        insertButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(insertButton);

        updateButton = new JButton("Update Client");
        updateButton.setBounds(470, 60, 130, 30);
        updateButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        updateButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(updateButton);

        deleteButton = new JButton("Delete Client");
        deleteButton.setBounds(470, 100, 130, 30);
        deleteButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        deleteButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(deleteButton);

        viewButton = new JButton("View Clients");
        viewButton.setBounds(470, 140, 130, 30);
        viewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        viewButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(viewButton);
    }

    /**
     * Add table.
     *
     * @param clientsTable the clients JTable
     */
    public void addTable(JTable clientsTable) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 230, 650, 170);
        scrollPane.setBackground(new Color(172, 248, 206));

        clientsTable.setVisible(true);
        clientsTable.setEnabled(true);
        scrollPane.setViewportView(clientsTable);
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
     * Gets email text field.
     *
     * @return the email text field
     */
    public JTextField getEmailTextField() {
        return emailTextField;
    }

    /**
     * Gets phone text field.
     *
     * @return the phone text field
     */
    public JTextField getPhoneTextField() {
        return phoneTextField;
    }

    /**
     * Gets address text field.
     *
     * @return the address text field
     */
    public JTextField getAddressTextField() {
        return addressTextField;
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
     * Sets email text field.
     *
     * @param emailTextField the email text field
     */
    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    /**
     * Sets phone text field.
     *
     * @param phoneTextField the phone text field
     */
    public void setPhoneTextField(JTextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }

    /**
     * Sets address text field.
     *
     * @param addressTextField the address text field
     */
    public void setAddressTextField(JTextField addressTextField) {
        this.addressTextField = addressTextField;
    }
}
