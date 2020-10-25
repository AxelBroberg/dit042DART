
//In order to implement Epic Feature 12 we created these four classes to handle our exceptions.

class NameEmptyException extends RuntimeException{
    public NameEmptyException(String message) throws RuntimeException {
        super(message);
    }
}

class NegativeSalaryException extends RuntimeException{
    public NegativeSalaryException(String message) throws RuntimeException {
        super(message);
    }
}

class NegativeRentException extends RuntimeException{
    public NegativeRentException(String message) throws RuntimeException {
        super(message);
    }
}

class EarlyDateException extends RuntimeException{
    public EarlyDateException(String message) throws RuntimeException {
        super(message);
    }
}