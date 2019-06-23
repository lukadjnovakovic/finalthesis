package impl;

import dto.TipDTO;
import entity.TipEntity;
import mappers.TipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TipRepo;
import services.ITipService;

import java.util.LinkedList;
import java.util.List;

@Service
public class TipServiceImpl implements ITipService {

    @Autowired
    TipRepo tipRepo;
    @Autowired
    TipMapper tipMapper;



    @Override
    public List<TipDTO> returnAllTips() {
        List<TipEntity> tipEntities = tipRepo.findAll();
        List<TipDTO> tipDTOS = new LinkedList<>();

        for (TipEntity tipEntity : tipEntities){
            tipDTOS.add(tipMapper.tip2DTO(tipEntity));
        }
        return tipDTOS;
    }
}
