package soa.dto;

import soa.entity.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class PagedTicketList {
    private ArrayList<Ticket> ticketList;
    private Long count;
}
