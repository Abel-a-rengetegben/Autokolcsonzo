package hu.pelda.autokolcsonzo.controller;

import hu.pelda.autokolcsonzo.dto.FoglalasKeresDTO;
import hu.pelda.autokolcsonzo.service.FoglalasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoglalasViewController {

    private final FoglalasService foglalasService;

    public FoglalasViewController(FoglalasService foglalasService) {
        this.foglalasService = foglalasService;
    }

    @PostMapping("/foglalas")
    public String letrehoz(@ModelAttribute FoglalasKeresDTO dto){
        foglalasService.createFoglalas(dto);
        return "redirect:/";
    }
}
