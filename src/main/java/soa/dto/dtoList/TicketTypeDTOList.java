package soa.dto.dtoList;

import soa.dto.TicketTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class TicketTypeDTOList {
    @XmlElementWrapper(name = "ticketTypes")
    @XmlElement(name = "ticketType")
    private ArrayList<TicketTypeDTO> ticketTypeList;
}