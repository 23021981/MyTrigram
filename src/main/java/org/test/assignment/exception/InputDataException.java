package org.test.assignment.exception;

/*
 * Author : Atul Kumar
 * */
public class InputDataException extends  Exception{
    private String errorId;

    public InputDataException(String errorId, String message) {
        super(message);
        this.errorId = errorId;
    }

    public String getErrorId() {
        return errorId;
    }
}
