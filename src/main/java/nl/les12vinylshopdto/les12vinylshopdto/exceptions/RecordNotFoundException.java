package nl.les12vinylshopdto.les12vinylshopdto.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}