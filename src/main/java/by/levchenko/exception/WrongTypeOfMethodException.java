package by.levchenko.exception;

public class WrongTypeOfMethodException extends Exception {
    public WrongTypeOfMethodException (String message){
        super(message);
    }
    public WrongTypeOfMethodException (String message,Throwable t){
        super(message,t);
    }
}
