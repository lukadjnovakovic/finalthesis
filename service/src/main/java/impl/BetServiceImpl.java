package impl;

import dto.BetDTO;
import entity.BetEntity;
import mappers.BetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BetRepo;
import services.IBetService;

import java.util.LinkedList;
import java.util.List;

@Service
public class BetServiceImpl implements IBetService {

    @Autowired
    BetRepo betRepo;
    @Autowired
    BetMapper betMapper;

    @Override
    public List<BetDTO> returnAllBets() {
        List<BetEntity> betEntities = betRepo.findAll();
        List<BetDTO> betDTOS = new LinkedList<>();

        for (BetEntity betEntity : betEntities) {
            betDTOS.add(betMapper.bet2DTO(betEntity));
        }
        return betDTOS;
    }

    @Override
    public BetDTO returnBetById(int id) throws Exception {
        BetEntity betEntity = betRepo.findById(id).orElse(null);
        if (betEntity == null) {
            throw new Exception("No bet with given ID");
        }
        return betMapper.bet2DTO(betEntity);
    }

    @Override
    public List<BetDTO> returnAllBetsByTicketId(Integer id){
        List<BetEntity> betEntities = betRepo.findAllByTicket(id);
        List<BetDTO> betDTOS = new LinkedList<>();
        for (BetEntity betEntity :
                betEntities) {
            BetDTO betDTO = betMapper.bet2DTO(betEntity);
            betDTOS.add(betDTO);
        }
        return betDTOS;
    }
}
