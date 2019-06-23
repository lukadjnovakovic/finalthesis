package impl;

import dto.TicketDTO;
import mappers.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TicketRepo;
import services.ITicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    TicketMapper ticketMapper;

    @Override
    public List<TicketDTO> retundAllTickets() {
        return null;
    }
}
