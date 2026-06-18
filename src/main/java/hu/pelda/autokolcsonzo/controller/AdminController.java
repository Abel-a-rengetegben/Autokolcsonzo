package hu.pelda.autokolcsonzo.controller;

import hu.pelda.autokolcsonzo.model.Foglalas;
import hu.pelda.autokolcsonzo.service.FoglalasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final FoglalasService foglalasService;

    public AdminController(FoglalasService foglalasService) {
        this.foglalasService = foglalasService;
    }

    @GetMapping
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping("/foglalasok")
    public String foglalasok(Model model) {
        model.addAttribute("foglalasok",
                foglalasService.getOsszesFoglalas()
        );

        return "admin/foglalasok";
    }
}
