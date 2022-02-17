package soa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@AllArgsConstructor
//@Getter
//@Setter
@XmlRootElement(name="exception")
@XmlAccessorType(XmlAccessType.FIELD)
//@NoArgsConstructor
public class ExceptionDTO {
    public ExceptionDTO() {}
    public ExceptionDTO(String message) {this.message = message;}

    @XmlElement
    String message;

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}

}
