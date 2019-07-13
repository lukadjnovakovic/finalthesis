package controllers;


import dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ITicketService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private  ITicketService ticketService;

    @GetMapping(value = "/tickets/{id}")
    public  List<TicketDTO> getAllTickets(@PathVariable String id) throws Exception {
        int userID= Integer.parseInt(id);
        return ticketService.retundAllTicketsById(userID);
    }
}
