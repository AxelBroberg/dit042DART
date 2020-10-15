public class Exceptions extends RuntimeException {
}



class NameEmptyException extends RuntimeException{

    public NameEmptyException() {
        super("Error: name cannot be empty");
    }

    public NameEmptyException(String message) throws RuntimeException {
        super(message);
    }
}

class NegativeSalaryException extends RuntimeException{

    public NegativeSalaryException() {
        super("Error: salary cannot be less than zero.");
    }

    public NegativeSalaryException(String message) throws RuntimeException {
        super(message);
    }
}

class NegativeRentException extends RuntimeException{

    public NegativeRentException() {
        super("Error: rent cannot be less than zero.");
    }

    public NegativeRentException(String message) throws RuntimeException {
        super(message);
    }
}

class EarlyDateException extends RuntimeException{

    public EarlyDateException() {
        super("Invalid operation. Upon returning an item, the number of days rented must be positive.");
    }

    public EarlyDateException(String message) throws RuntimeException {
        super(message);
    }
}