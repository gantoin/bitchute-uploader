package exception;

public class AuthenticateErrorException extends RuntimeException {

    private static final long serialVersionUID = 2725675308255088481L;

    public AuthenticateErrorException(String message) {
        super(message);
    }
}
