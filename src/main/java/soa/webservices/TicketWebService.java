package soa.webservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import soa.ApplicationContextProvider;
import soa.dto.PagedTicketList;
import soa.dto.TicketDTO;
import soa.dto.dtoList.TicketDTOList;
import soa.entity.Ticket;
import soa.exceptions.WSFault;
import soa.mapper.PersonMapper;
import soa.mapper.TicketMapper;
import soa.services.PersonService;
import soa.services.TicketService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@Service
@WebService
public class TicketWebService {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketWebService(
            TicketService ticketService,
            TicketMapper ticketMapper
    ) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    public TicketWebService() {
        this.ticketService = ApplicationContextProvider.getApplicationContext().getBean(TicketService.class);;
        this.ticketMapper = ApplicationContextProvider.getApplicationContext().getBean(TicketMapper.class);;
    }

    @WebMethod
    public TicketDTOList getTickets(
            Integer perPage,
            Integer curPage,
            String sortBy,
            String filterBy
    ) throws WSFault {
        try {
            System.out.println("FILTER BY " + filterBy);
            PagedTicketList pagedTicketList = ticketService.getTickets(perPage, curPage, sortBy, filterBy);
            return new TicketDTOList(
                    ticketMapper.mapTicketListToTicketDTOList(pagedTicketList.getTicketList()),
                    pagedTicketList.getCount()
            );
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public TicketDTOList getTicket(Integer id) throws WSFault {
        try {
            Ticket ticket = ticketService.getTicketById(id);
            TicketDTOList dto = new TicketDTOList(new ArrayList<>(), 1);
            ArrayList<TicketDTO> dtoList = dto.getTicketList();
            dtoList.add(ticketMapper.mapTicketToTicketDTO(ticket));
            return dto;
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public void updateTicket(TicketDTOList ticketDTOList) throws WSFault {
        try {
            System.out.println("UPDATE");
            Ticket ticketToUpdate = ticketMapper.mapTicketDTOToTicket(ticketDTOList.getTicketList().get(0));
            ticketService.updateTicket(ticketToUpdate);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public void createTicket(TicketDTOList ticketDTOList) throws WSFault {
        try {
            System.out.println("CREATE");
            Ticket ticketToCreate = ticketMapper.mapTicketDTOToTicket(ticketDTOList.getTicketList().get(0));
            ticketService.createTicket(ticketToCreate);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }

    @WebMethod
    public void deleteTicket(Integer id) throws WSFault {
        try {
            ticketService.deleteTicket(id);
        } catch (RuntimeException e) {
            throw new WSFault(e.getMessage(), 400);
        }
    }
}
