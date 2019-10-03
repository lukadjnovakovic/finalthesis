package impl;

import dto.OddsDTO;
import entity.OddsEntity;
import mappers.OddsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OddsRepo;
import services.IOddsService;

import java.util.LinkedList;
import java.util.List;

@Service
public class OddsServiceImpl implements IOddsService {

    @Autowired
    OddsRepo oddsRepo;
    @Autowired
    OddsMapper oddsMapper;

    @Override
    public List<OddsDTO> returnAllOdds() {
        List<OddsEntity> oddsEntities = oddsRepo.findAll();
        List<OddsDTO> oddsDTOS = new LinkedList<>();

        for (OddsEntity oddsEntity : oddsEntities) {
            oddsDTOS.add(oddsMapper.odds2DTO(oddsEntity));
        }
        return oddsDTOS;
    }

    @Override
    public OddsDTO returOddsDTObyId(int id){

        OddsEntity oddsEntity = oddsRepo.findById(id);

        return oddsMapper.odds2DTO(oddsEntity);
    }

    @Override
    public void saveAllOdds(List<OddsDTO> oddsDTOS){

        List<OddsEntity> oddsEntities = new LinkedList<>();

        for (OddsDTO oddsDTO : oddsDTOS) {
            OddsEntity oddsEntity = oddsMapper.dto2entiry(oddsDTO);
            oddsEntities.add(oddsEntity);
        }

        oddsRepo.saveAll(oddsEntities);
    }
}
