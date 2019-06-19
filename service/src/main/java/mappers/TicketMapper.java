package mappers;

import dto.TicketDTO;
import entity.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "win", expression = "java(ticketEntity.getPayment() * ticketEntity.getOverallOdds())")
    TicketDTO team2DTO(TicketEntity ticketEntity);
}
