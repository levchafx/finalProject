package by.levchenko.exception;

public class SomethingWentTerriblyWrongException extends Exception {
    public SomethingWentTerriblyWrongException(String message){
        super(message);
    }
    public SomethingWentTerriblyWrongException(String message,Throwable t){
        super(message,t);
    }
}
