package bll;

import bll.validators.*;
import dao.ProductDAO;
import exception.InputValidationFailedException;
import model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Product bll - business logic for product - validates the product to be added/updated into the database or for delete ensures
 * that it exists.
 * @author Ariana Horvath
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * Instantiates a new Product bll.
     */
    public ProductBLL(){
        validators = new ArrayList<>();
        validators.add(new ProductNameValidator());
        validators.add(new ProductStockValidator());
        productDAO = new ProductDAO();
    }

    /**
     * Insert product product if it is valid.
     *
     * @param product the product
     * @return the product
     */
    public Product insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return productDAO.insert(product);
    }

    /**
     * Delete product  - ensures the product to be deleted exists and if so it deletes it.
     *
     * @param id the id
     */
    public void deleteProduct(int id) {
        Product product = productDAO.findById(id);
        if(product == null)
            throw new InputValidationFailedException("Product with id "+id+" not existent.");
        productDAO.delete(product);
    }

    /**
     * Update product - ensures the product to be updated exists and if so it updates it with the new product.
     *
     * @param product the product
     * @param id      the id
     */
    public void updateProduct(Product product, int id){
        Product existentProduct = productDAO.findById(id);
        if (existentProduct == null)
            throw new InputValidationFailedException("Product with id "+id+" not existent.");
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        product.setId(id);
        productDAO.update(product);
    }

    /**
     * Gets validators.
     *
     * @return the validators
     */
    public List<Validator<Product>> getValidators() {
        return validators;
    }

    /**
     * Sets validators.
     *
     * @param validators the validators
     */
    public void setValidators(List<Validator<Product>> validators) {
        this.validators = validators;
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
}
