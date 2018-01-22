package api.biblioteca.satc.util;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

    private String message;

    public ExceptionResponse() {
        this.message = message;
    }
    public ExceptionResponse(String message) {
        this.message = message;
    }

    public void addMessage(String message){
        this.message = this.message != null && !this.message.isEmpty() ? this.message + ", " + message : message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
