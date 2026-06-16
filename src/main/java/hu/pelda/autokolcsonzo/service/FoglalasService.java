package hu.pelda.autokolcsonzo.service;

import hu.pelda.autokolcsonzo.dto.FoglalasKeresDTO;
import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.model.Foglalas;
import hu.pelda.autokolcsonzo.repository.AutoRepository;
import hu.pelda.autokolcsonzo.repository.FoglalasRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class FoglalasService {
    private final FoglalasRepository foglalasRepository;
    private final AutoRepository autoRepository;

    FoglalasService(FoglalasRepository foglalasRepository, AutoRepository autoRepository) {
        this.foglalasRepository = foglalasRepository;
        this.autoRepository = autoRepository;
    }

    public Foglalas createFoglalas(FoglalasKeresDTO dto) {
        Auto auto = autoRepository.findById(dto.getAutoId())
                .orElseThrow(() -> new RuntimeException("Auto Not Found"));

        boolean konfliktus = foglalasRepository
                .findConflictingFoglalas(auto.getId(), dto.getDatumtol(), dto.getDatumig())
                .size() > 0;

        if (konfliktus) {
            throw new RuntimeException("Az autó már foglalt ebben az időszakban");
        }

        long napok = ChronoUnit.DAYS.between(dto.getDatumtol(), dto.getDatumig()) + 1;

        int teljesAr = (int) (napok * auto.getNapiAr());

        Foglalas foglalas = new Foglalas();
        foglalas.setAuto(auto);
        foglalas.setNev(dto.getNev());
        foglalas.setEmail(dto.getEmail());
        foglalas.setCim(dto.getCim());
        foglalas.setTelefon(dto.getTelefon());
        foglalas.setDatumtol(dto.getDatumtol());
        foglalas.setDatumig(dto.getDatumig());
        foglalas.setTeljesAr(teljesAr);

        return foglalasRepository.save(foglalas);
    }
}
