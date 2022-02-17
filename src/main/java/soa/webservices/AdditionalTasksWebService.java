package soa.webservices;

import org.springframework.stereotype.Service;
import soa.ApplicationContextProvider;
import soa.dto.CountDTO;
import soa.dto.dtoList.TicketDTOList;
import soa.dto.dtoList.TicketTypeDTOList;
import soa.entity.Ticket;
import soa.enums.TicketType;
import soa.exceptions.WSFault;
import soa.mapper.TicketMapper;
import soa.services.TicketService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@Service
@WebService
public class AdditionalTasksWebService {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;


    public AdditionalTasksWebService(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    public AdditionalTasksWebService() {
        this.ticketService = ApplicationContextProvider.getApplicationContext().getBean(TicketService.class);;
        this.ticketMapper = ApplicationContextProvider.getApplicationContext().getBean(TicketMapper.class);;
    }

//    @WebMethod
//    public Object execAdditionalTasks(int person,
//                               long discount) {
//        if (person != -1 && discount == -1) {
//            return countTicketsByPeopleWithIdLessThan(person);
//        } else if (discount != -1 && person == -1) {
//            return findTicketsByDiscountLessThan(discount);
//        } else {
//            return getDistinctTicketTypes();
//        }
//    }

    @WebMethod
    public TicketTypeDTOList getDistinctTicketTypes() throws WSFault {
        try {
            ArrayList<TicketType> ticketTypeList = ticketService.getDistinctTypes();
            TicketTypeDTOList dto = new TicketTypeDTOList(new ArrayList<>());
            dto.setTicketTypeList(ticketMapper.mapTicketTypesListToTicketTypesDTOList(ticketTypeList));
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public CountDTO countTicketsByPeopleWithIdLessThan(
            Integer person
    ) throws WSFault {
        try {
            Long countResult = ticketService.countAllByPerson_IdLessThan(person);
            CountDTO dto = new CountDTO();
            dto.setCount(countResult);
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public TicketDTOList findTicketsByDiscountLessThan (
            Long discount
    ) throws WSFault {
        try {
            ArrayList<Ticket> ticketList = ticketService.findAllByDiscountLessThan(discount);
            TicketDTOList dto = new TicketDTOList(
                    ticketMapper.mapTicketListToTicketDTOList(ticketList),
                    ticketList.size()
            );
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }
}
