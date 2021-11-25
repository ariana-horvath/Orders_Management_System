package bll.validators;

import exception.InputValidationFailedException;
import model.Client;

import java.util.regex.Pattern;

/**
 * The type Address validator.
 * @author Ariana Horvath
 */
public class AddressValidator implements Validator<Client> {
    private static final String ADDRESS_PATTERN = "Str\\.\\s[a-zA-Z]+\\sNr\\.\\s[0-9]+[A-Z]?(\\sAp\\.\\s[0-9]+)?";

    /**
     *
     * @param client - the client
     * @throws InputValidationFailedException if client address is not of valid pattern.
     */
    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        if(!pattern.matcher(client.getAddress()).matches()) {
            throw new InputValidationFailedException("Address is not a valid address!");
        }
    }
}
