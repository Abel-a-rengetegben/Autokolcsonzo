package hu.pelda.autokolcsonzo.service;

import hu.pelda.autokolcsonzo.dto.AutoFormDTO;
import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.repository.AutoRepository;
import hu.pelda.autokolcsonzo.repository.FoglalasRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AutoService {

    @Value("${file.upload-dir}")
    private String uploadDir;

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

    public void updateAuto(Long id, AutoFormDTO dto) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Nem található autó: + " + id));

        auto.setMarka(dto.getMarka());
        auto.setModell(dto.getModell());
        auto.setNapiAr(dto.getNapiAr());
        auto.setAktiv(dto.isAktiv());

        if (dto.getKep() != null && !dto.getKep().isEmpty()) {
            kepFeltoltes(dto.getKep(), auto);
        }

        autoRepository.save(auto);
    }

    public void createAuto(AutoFormDTO dto) {
        Auto auto = new Auto();
        auto.setMarka(dto.getMarka());
        auto.setModell(dto.getModell());
        auto.setNapiAr(dto.getNapiAr());
        auto.setAktiv(dto.isAktiv());

        kepFeltoltes(dto.getKep(), auto);

        autoRepository.save(auto);
    }

    private void kepFeltoltes(MultipartFile file, Auto auto) {
        if (file == null ||file.isEmpty()) {
            return;
        }

        try {

            if (auto.getKepNev() != null){
                Files.deleteIfExists(Paths.get(uploadDir, auto.getKepNev()));
            }

            Path uploadDirPath = Paths.get(uploadDir);
            Files.createDirectories(uploadDirPath);

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path filePath = uploadDirPath.resolve(fileName);
            Files.copy(file.getInputStream(),filePath);

            auto.setKepNev(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Kép feltöltése sikertelen", e);
        }
    }
}
