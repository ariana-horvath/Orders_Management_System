package presentation.controller;

import bll.ProductBLL;
import exception.InputValidationFailedException;
import model.Product;
import presentation.view.ProductScreen;

import javax.swing.*;
import java.util.List;

/**
 * The type Product controller.
 *  - opens the frame for managing products and initializes listeners for the buttons
 *  - validates the input from GUI to be non-empty and the integers to be integers, creates a new Product
 * and passes it to the ProductBLL for validation before making operations in the database.
 * @author Ariana Horvath
 */
public class ProductController {

    private ProductScreen productScreen;
    private ProductBLL productBLL;

    /**
     * Instantiates a new Product controller.
     */
    public ProductController(){
        productScreen = new ProductScreen();
        productBLL = new ProductBLL();
        initializeListeners();
    }

    /**
     * Initialize listeners.
     */
    public void initializeListeners() {
        productScreen.getCancelButton().addActionListener(e->{
            productScreen.setVisible(false);
        });

        productScreen.getViewButton().addActionListener(e->{
            List<Product> products = productBLL.getProductDAO().findAll();
            JTable productsTable = productBLL.getProductDAO().createTable(products);
            productScreen.addTable(productsTable);
        });

        productScreen.getInsertButton().addActionListener(e->{
            try {
                validateTextField(productScreen.getNameTextField().getText());
                validateTextField(productScreen.getStockTextField().getText());
                int stock = Integer.parseInt(productScreen.getStockTextField().getText());
                productBLL.insertProduct(new Product(productScreen.getNameTextField().getText(), stock));
                productScreen.displayInformationMessage("Product successfully inserted.");
            }  catch(NumberFormatException ex2) {
                productScreen.displayInformationMessage("Stock is not a number.");
            }  catch(InputValidationFailedException ex) {
                    productScreen.displayErrorMessage(ex);
            }
        });

        productScreen.getDeleteButton().addActionListener(e->{
            try {
                validateTextField(productScreen.getIdTextField().getText());
                int id = Integer.parseInt(productScreen.getIdTextField().getText());
                productBLL.deleteProduct(id);
                productScreen.displayInformationMessage("Product successfully deleted.");
            } catch (InputValidationFailedException ex2) {
                productScreen.displayErrorMessage(ex2);
            } catch (NumberFormatException ex) {
                productScreen.displayInformationMessage("ID is not a number.");
            }
        });

        productScreen.getUpdateButton().addActionListener(e->{
            try {
                validateTextField(productScreen.getIdTextField().getText());
                int id = Integer.parseInt(productScreen.getIdTextField().getText());
                validateTextField(productScreen.getNameTextField().getText());
                validateTextField(productScreen.getStockTextField().getText());
                int stock = Integer.parseInt(productScreen.getStockTextField().getText());
                productBLL.updateProduct(new Product(productScreen.getNameTextField().getText(), stock), id);
                productScreen.displayInformationMessage("Product successfully updated.");

            }catch (NumberFormatException ex) {
                productScreen.displayInformationMessage("ID or Stock is not a number.");
            } catch (InputValidationFailedException ex2) {
                productScreen.displayErrorMessage(ex2);
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
