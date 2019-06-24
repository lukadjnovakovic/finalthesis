package controllers;

import dto.TipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ITipService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TipsController {

    @Autowired
    ITipService tipService;

    @GetMapping(value = "/tips")
    public List<TipDTO> getAllTips(){
        return tipService.returnAllTips();
    }

}
