package hu.pelda.autokolcsonzo.controller;


import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.service.AutoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/autok")
public class AutoController {

    private final AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping
    public List<Auto> getAutok() {
        return autoService.getOsszesAuto();
    }

    @GetMapping("/elerheto")
    public List<Auto> getElerhetoAutok(
            @RequestParam LocalDate datumtol,
            @RequestParam LocalDate datumig
    ) {
        return autoService.getElerhetoAutok(datumtol, datumig);
    }

    @GetMapping("/{id:\\d+}")
    public Auto getAuto(@PathVariable Long id) {
        return autoService.getAutoById(id);
    }
}
