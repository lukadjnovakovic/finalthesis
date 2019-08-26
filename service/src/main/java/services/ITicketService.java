package services;

import dto.TicketDTO;
import entity.TicketEntity;

import java.util.List;

public interface ITicketService {

    List<TicketDTO> retundAllTicketsById(Integer id) throws Exception;

    TicketEntity saveTicket(TicketEntity ticketEntity);
}
