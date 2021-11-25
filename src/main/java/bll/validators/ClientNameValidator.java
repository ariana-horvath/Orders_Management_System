package bll.validators;

import exception.InputValidationFailedException;
import model.Client;

import java.util.regex.Pattern;

/**
 * The type Client name validator.
 * @author Ariana Horvath
 */
public class ClientNameValidator implements Validator<Client>{
    private static final String NAME_PATTERN = "[a-zA-Z\\s]+";

    /**
     *
     * @param client
     * @throws InputValidationFailedException if client name contains characters different than letters
     */
    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if(!pattern.matcher(client.getName()).matches()) {
            throw new InputValidationFailedException("Client name is not valid!");
        }
    }
}
