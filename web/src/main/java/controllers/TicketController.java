package controllers;


import dto.*;
import entity.TicketEntity;
import mappers.BetMapper;
import mappers.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import payload.ApiResponse;
import payload.TicketPayload;
import services.*;

import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private  ITicketService ticketService;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private IBetService betService;
    @Autowired
    private BetMapper betMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOddsService oddsService;

    @GetMapping(value = "/tickets")
    public  List<TicketDTO> getAllTickets() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO user = userService.returnUserByUsername(username);
        int userID = user.getId();
        return ticketService.retundAllTicketsById(userID);
    }

    @PostMapping(value = "/saveTicket",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTicket(@RequestBody TicketPayload ticketPayload) {

        System.out.println("Create Ticket************" + ticketPayload.getAmount());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO user = userService.returnUserByUsername(username);

        double amount = ticketPayload.getAmount();
        double overallOdds = ticketPayload.getOddsOverall();
        double win = amount * overallOdds;

        TicketDTO ticketDTO = new TicketDTO(amount,overallOdds,win, new Date());
        ticketDTO.setUser(user);
        TicketEntity ticketEntity = ticketService.saveTicket(ticketMapper.ticketEntity(ticketDTO));

        List<BetDTO> betsMade = new LinkedList<>();

        for (Integer oddsID: ticketPayload.getOdds() ) {
            OddsDTO selectedOdds = oddsService.returOddsDTObyId(oddsID);
            int ticketId = ticketEntity.getId();
            BetDTO newBet = new BetDTO(selectedOdds,ticketId);
            betsMade.add(newBet);
        }

        for (BetDTO bet: betsMade) {
            betService.saveBet(betMapper.bet2Entity(bet));
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/ticketCreated")
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Ticket created successfully"));
    }

}
