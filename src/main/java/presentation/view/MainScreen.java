package presentation.view;

import javax.swing.*;
import java.awt.*;

/**
 * The type Main screen - the principal GUI.
 * @author Ariana Horvath
 */
public class MainScreen extends JFrame {

    private JButton manageClientsButton;
    private JButton manageProductsButton;
    private JButton manageOrdersButton;
    private JButton exitButton;

    /**
     * Instantiates a new Main screen.
     */
    public MainScreen() {
        this.setTitle("Orders Management");
        this.setSize(500, 500);
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

    private void initializeForm(JPanel panel) {
        JLabel titleLabel = new JLabel("ORDERS MANAGEMENT APPLICATION");
        titleLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        titleLabel.setForeground(new Color(2, 95, 69, 255));
        titleLabel.setBounds(87, 40, 400, 30);
        panel.add(titleLabel);

        manageClientsButton = new JButton("Manage Clients");
        manageClientsButton.setBounds(140, 100, 210, 30);
        manageClientsButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        manageClientsButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(manageClientsButton);

        manageProductsButton = new JButton("Manage Products");
        manageProductsButton.setBounds(140, 160, 210, 30);
        manageProductsButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        manageProductsButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(manageProductsButton);

        manageOrdersButton = new JButton("Manage Orders");
        manageOrdersButton.setBounds(140, 220, 210, 30);
        manageOrdersButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        manageOrdersButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(manageOrdersButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(140, 400, 210, 30);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        exitButton.setForeground(new Color(2, 95, 69, 255));
        panel.add(exitButton);
    }

    /**
     * Gets manage clients button.
     *
     * @return the manage clients button
     */
    public JButton getManageClientsButton() {
        return manageClientsButton;
    }

    /**
     * Gets manage products button.
     *
     * @return the manage products button
     */
    public JButton getManageProductsButton() {
        return manageProductsButton;
    }

    /**
     * Gets manage orders button.
     *
     * @return the manage orders button
     */
    public JButton getManageOrdersButton() {
        return manageOrdersButton;
    }

    /**
     * Gets exit button.
     *
     * @return the exit button
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Sets manage clients button.
     *
     * @param manageClientsButton the manage clients button
     */
    public void setManageClientsButton(JButton manageClientsButton) {
        this.manageClientsButton = manageClientsButton;
    }

    /**
     * Sets manage products button.
     *
     * @param manageProductsButton the manage products button
     */
    public void setManageProductsButton(JButton manageProductsButton) {
        this.manageProductsButton = manageProductsButton;
    }

    /**
     * Sets manage orders button.
     *
     * @param manageOrdersButton the manage orders button
     */
    public void setManageOrdersButton(JButton manageOrdersButton) {
        this.manageOrdersButton = manageOrdersButton;
    }

    /**
     * Sets exit button.
     *
     * @param exitButton the exit button
     */
    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }
}
