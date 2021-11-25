package bll.validators;

import exception.InputValidationFailedException;
import model.Client;

import java.util.regex.Pattern;

/**
 * The type Phone number validator.
 * @author Ariana Horvath
 */
public class PhoneNumberValidator implements Validator<Client> {
    private static final String PHONE_PATTERN = "07[0-9]{8}";

    /**
     *
     * @param client
     * @throws InputValidationFailedException if client phone number is not of valid pattern.
     */
    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if(!pattern.matcher(client.getPhoneNumber()).matches()) {
            throw new InputValidationFailedException("Phone number is not valid!");
        }
    }
}
