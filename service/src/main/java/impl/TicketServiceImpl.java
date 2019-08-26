package impl;

import dto.BetDTO;
import dto.TicketDTO;
import dto.UserDTO;
import entity.TicketEntity;
import mappers.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TicketRepo;
import services.IBetService;
import services.ITicketService;
import services.IUserService;

import java.util.LinkedList;
import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    IUserService userService;
    @Autowired
    IBetService betService;

    @Override
    public List<TicketDTO> retundAllTicketsById(Integer id) throws Exception {

        List<TicketEntity> ticketEntities = ticketRepo.findAllByUserId(id);
        List<TicketDTO> ticketDTOS = new LinkedList<>();

        for (TicketEntity ticketEntity :
                ticketEntities) {
            TicketDTO ticketDTO = ticketMapper.ticket2DTO(ticketEntity);

            UserDTO user = userService.returnUserById(ticketDTO.getUser().getId());
            ticketDTO.setUser(user);

            List<BetDTO> betDTOS =  betService.returnAllBetsByTicketId(ticketDTO.getId());
            ticketDTO.setBets(betDTOS);
            ticketDTOS.add(ticketDTO);
        }

        return ticketDTOS;
    }

    @Override
    public TicketEntity saveTicket(TicketEntity ticketEntity){return ticketRepo.save(ticketEntity);};
}
