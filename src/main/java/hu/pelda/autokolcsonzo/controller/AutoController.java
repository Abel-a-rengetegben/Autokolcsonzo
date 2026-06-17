package hu.pelda.autokolcsonzo.controller;


import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.service.AutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public Auto getAuto(@PathVariable Long id) {
        return autoService.getAutoById(id);
    }
}
