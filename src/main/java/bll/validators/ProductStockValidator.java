package bll.validators;

import exception.InputValidationFailedException;
import model.Product;

/**
 * The type Product stock validator.
 * @author Ariana Horvath
 */
public class ProductStockValidator implements Validator<Product>{
    /**
     *
     * @param product
     * @throws InputValidationFailedException if product stock is negative.
     */
    @Override
    public void validate(Product product) {
        if(product.getStock() < 0)
            throw new InputValidationFailedException("Product stock cannot be negative!");
    }
}
