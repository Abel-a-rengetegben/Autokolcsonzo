package hu.pelda.autokolcsonzo.config;

import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.repository.AutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(AutoRepository autoRepository) {
        return args -> {
            if (autoRepository.findAll().isEmpty()) {
                Auto auto1 = new Auto();
                auto1.setMarka("Toyota");
                auto1.setModell("Supra");
                auto1.setNapiAr(20000);
                auto1.setKepURL("toyota.jpg");
                auto1.setAktiv(true);

                Auto auto2 = new Auto();
                auto2.setMarka("Bugatti");
                auto2.setModell("Veyron");
                auto2.setNapiAr(200000);
                auto2.setKepURL("bugatti.jpg");
                auto2.setAktiv(true);

                autoRepository.save(auto1);
                autoRepository.save(auto2);
            }
        };
    }
}
