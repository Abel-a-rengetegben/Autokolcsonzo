package hu.pelda.autokolcsonzo.service;

import hu.pelda.autokolcsonzo.dto.AutoFormDTO;
import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.repository.AutoRepository;
import hu.pelda.autokolcsonzo.repository.FoglalasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AutoService {

    private final AutoRepository autoRepository;
    private final FoglalasRepository foglalasRepository;

    public AutoService(AutoRepository autoRepository, FoglalasRepository foglalasRepository) {
        this.autoRepository = autoRepository;
        this.foglalasRepository = foglalasRepository;
    }

    public List<Auto> getOsszesAuto() {
        return autoRepository.findAll();
    }

    public List<Auto> getElerhetoAutok(LocalDate datumtol, LocalDate datumig) {
        List<Auto> autok = autoRepository.findAll();

        return autok.stream()
                .filter(Auto::isAktiv)
                .filter(auto -> foglalasRepository
                        .findConflictingFoglalas(auto.getId(), datumtol, datumig)
                        .isEmpty())
                .toList();
    }

    public Auto getAutoById(Long id) {
        return autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nem talált autó: " + id));
    }

    public Auto updateAuto(Long id, AutoFormDTO dto) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Nem található autó: + " + id));

        auto.setMarka(dto.getMarka());
        auto.setModell(dto.getModell());
        auto.setNapiAr(dto.getNapiAr());
        auto.setAktiv(dto.isAktiv());

        return autoRepository.save(auto);
    }
}
