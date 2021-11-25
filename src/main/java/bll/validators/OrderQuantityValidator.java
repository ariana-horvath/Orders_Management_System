package bll.validators;

import exception.InputValidationFailedException;
import model.Order;

/**
 * The type Order quantity validator.
 * @author Ariana Horvath
 */
public class OrderQuantityValidator implements Validator<Order>{
    /**
     *
     * @param order
     * @throws InputValidationFailedException if order quantity is negative or 0.
     */
    @Override
    public void validate(Order order) {
        if(order.getQuantity() <= 0)
            throw new InputValidationFailedException("Order quantity cannot be 0 or negative!");
    }
}
