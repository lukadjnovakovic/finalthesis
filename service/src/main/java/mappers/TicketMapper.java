package mappers;

import dto.TicketDTO;
import entity.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface TicketMapper {


//    @Mapping(target = "win", expression = "java(ticketEntity.getPayment() * ticketEntity.getOverallOdds())")
    TicketDTO ticket2DTO(TicketEntity ticketEntity);
}
