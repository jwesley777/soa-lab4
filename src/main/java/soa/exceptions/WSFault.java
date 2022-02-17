package soa.exceptions;

import soa.dto.ExceptionDTO;

import javax.xml.ws.WebFault;

@WebFault(name = "wsfault")
public class WSFault extends Exception{

    private final int code;
//    private final String message;
    ExceptionDTO exception;


    public int getCode() {
        return code;
    }

    public ExceptionDTO getException() { return exception; }
    public void setException(ExceptionDTO exception) {this.exception = exception;}

//    public String getMessage() {
//        return message;
//    }

    public WSFault(String message, int code){
        super(message);
//        this.message = message;
        this.exception = new ExceptionDTO(message);
        this.code = code;
    }
}