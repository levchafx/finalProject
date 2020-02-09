package by.levchenko.exception;

public class YouAreNotAllowedToBeHereException extends Exception {
    public YouAreNotAllowedToBeHereException(String message){
        super(message);
    }
    public YouAreNotAllowedToBeHereException(String message,Throwable t){
        super(message,t);
    }
}
