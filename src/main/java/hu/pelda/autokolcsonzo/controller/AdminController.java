package hu.pelda.autokolcsonzo.controller;

import hu.pelda.autokolcsonzo.dto.AutoFormDTO;
import hu.pelda.autokolcsonzo.model.Foglalas;
import hu.pelda.autokolcsonzo.service.AutoService;
import hu.pelda.autokolcsonzo.service.FoglalasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final FoglalasService foglalasService;
    private final AutoService autoService;

    public AdminController(FoglalasService foglalasService, AutoService autoService) {
        this.foglalasService = foglalasService;
        this.autoService = autoService;
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

    @GetMapping("/autok")
    public String autok(Model model) {
        model.addAttribute(
                "autok",
                autoService.getOsszesAuto()
        );
        return "admin/autok";
    }

    @GetMapping("/autok/{id}/szerkeszt")
    public String szerkeszt(@PathVariable Long id,
                            Model model

    ) {
        model.addAttribute(
                "auto",
                autoService.getAutoById(id)
        );
        return "admin/auto-form";
    }

    @PostMapping("/autok/{id}/szerkeszt")
    public String mentes(
            @PathVariable Long id,
            @ModelAttribute AutoFormDTO dto
    ) {
        autoService.updateAuto(id, dto);

        return "redirect:/admin/autok";
    }

    @GetMapping("/autok/uj")
    public String ujAuto(Model model) {
        model.addAttribute("auto", new  AutoFormDTO());

        return "admin/auto-hozzaad";
    }

    @PostMapping("/autok/uj")
    public String letrehoz(@ModelAttribute AutoFormDTO dto) {
        autoService.createAuto(dto);
        return "redirect:/admin/autok";
    }
}
