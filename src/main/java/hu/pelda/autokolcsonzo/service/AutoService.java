package hu.pelda.autokolcsonzo.service;

import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.repository.AutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    private final AutoRepository autoRepository;
    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public List<Auto> getOsszesAuto() {
        return autoRepository.findAll();
    }
}
