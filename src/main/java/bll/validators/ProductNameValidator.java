package bll.validators;

import exception.InputValidationFailedException;
import model.Product;

import java.util.regex.Pattern;

/**
 * The type Product name validator.
 * @author Ariana Horvath
 */
public class ProductNameValidator implements Validator<Product>{
    private static final String NAME_PATTERN = "[a-zA-Z\\s]+";

    /**
     *
     * @param product
     * @throws InputValidationFailedException if product name contains characters different than letters.
     */
    @Override
    public void validate(Product product) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if(!pattern.matcher(product.getName()).matches()) {
            throw new InputValidationFailedException("Product name is not valid!");
        }
    }
}
