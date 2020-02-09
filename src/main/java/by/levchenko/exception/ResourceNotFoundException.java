package by.levchenko.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(String message,Throwable t){
        super(message,t);
    }
}
