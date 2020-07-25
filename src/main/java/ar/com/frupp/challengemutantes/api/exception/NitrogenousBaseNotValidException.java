package ar.com.frupp.challengemutantes.api.exception;

public class NitrogenousBaseNotValidException extends RuntimeException {
    private static final String DESCRIPTION = "Nitrogenous Base not valid: ";

    public NitrogenousBaseNotValidException(String dnaSequence){
        super(DESCRIPTION + dnaSequence);
    }
}
