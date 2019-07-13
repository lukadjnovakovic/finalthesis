package services;

import dto.TicketDTO;

import java.util.List;

public interface ITicketService {

    List<TicketDTO> retundAllTicketsById(Integer id) throws Exception;
}
