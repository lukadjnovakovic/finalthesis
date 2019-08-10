package controllers;


import dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import payload.ApiResponse;
import payload.SignUpRequest;
import services.ITicketService;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping(value = "/saveTicket",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody TicketDTO ticketDTO) {

        System.out.println("TEST************");

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
