package ec.com.dinersclub.dddmodules.domain.exception;

public class DomainException extends RuntimeException {
    DomainException(final String message) {
        super(message);
    }
}
