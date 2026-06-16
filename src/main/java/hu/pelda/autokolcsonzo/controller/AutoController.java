package hu.pelda.autokolcsonzo.controller;


import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.service.AutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class AutoController {

    private final AutoService autoService;
    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

/* @GetMapping("/api/autok")
    public String autok(
            @RequestParam(required = false)LocalDate datumtol,
            @RequestParam(required = false)LocalDate datumig,
            Model model
            ) {
        if (datumtol != null && datumig != null) {
            model.addAttribute("autok", autoService.getElerhetoAutok(datumtol, datumig));
            model.addAttribute("datumtol", datumtol);
            model.addAttribute("datumig", datumig);
        } else {
            model.addAttribute("autok", autoService.getOsszesAuto());
        }

        return "autok";
    }
*/
    @GetMapping("/foglal/{autoId}")
    public String foglalasOldal(
            @PathVariable Long autoId,
            @RequestParam LocalDate datumtol,
            @RequestParam LocalDate datumig,
            Model model
    ) {
        Auto auto = autoService.getAutoById(autoId);
        long napok = ChronoUnit.DAYS.between(datumtol, datumig) + 1;
        int teljesAr = (int) (napok * auto.getNapiAr());

        model.addAttribute("auto", auto);
        model.addAttribute("datumtol", datumtol);
        model.addAttribute("datumig", datumig);
        model.addAttribute("teljesAr", teljesAr);

        return "foglalas";
    }
}
