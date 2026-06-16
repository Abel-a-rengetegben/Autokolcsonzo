package hu.pelda.autokolcsonzo.controller;

import hu.pelda.autokolcsonzo.dto.FoglalasKeresDTO;
import hu.pelda.autokolcsonzo.model.Foglalas;
import hu.pelda.autokolcsonzo.service.FoglalasService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foglalasok")
public class FoglalasController {

    private final FoglalasService foglalasService;

    public FoglalasController(FoglalasService foglalasService) {
        this.foglalasService = foglalasService;
    }

    @PostMapping
    public Foglalas letrehoz(@RequestBody FoglalasKeresDTO dto){
        return foglalasService.createFoglalas(dto);
    }
}
