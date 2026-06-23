package hu.pelda.autokolcsonzo;

import hu.pelda.autokolcsonzo.dto.AutoFormDTO;
import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.model.Foglalas;
import hu.pelda.autokolcsonzo.repository.AutoRepository;
import hu.pelda.autokolcsonzo.repository.FoglalasRepository;
import hu.pelda.autokolcsonzo.service.AutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutoServiceTest {
    @Mock
    private AutoRepository autoRepository;
    @Mock
    private FoglalasRepository foglalasRepository;

    @InjectMocks
    private AutoService autoService;

    @Test
    void getElerhetoAutok_ReturnsOnlyAvailableCars(){
        Auto auto1 = new Auto();
        auto1.setId(1L);
        auto1.setAktiv(true);

        Auto auto2 = new Auto();
        auto2.setId(2L);
        auto2.setAktiv(true);

        when(autoRepository.findAll())
                .thenReturn(List.of(auto1, auto2));

        when(foglalasRepository.findConflictingFoglalas(
                eq(1L), any(), any()))
                .thenReturn(List.of());

        when(foglalasRepository.findConflictingFoglalas(
                eq(2L), any(), any()))
                .thenReturn(List.of(new Foglalas()));

        List<Auto> eredmeny = autoService.getElerhetoAutok(
                LocalDate.now(),
                LocalDate.now().plusDays(1));

        assertEquals(1, eredmeny.size());
        assertEquals(1L, eredmeny.getFirst().getId());
    }

    @Test
    void createAuto_ShouldSaveCar(){
        AutoFormDTO dto = new AutoFormDTO();
        dto.setMarka("Toyota");
        dto.setModell("Supra");
        dto.setNapiAr(10000);
        dto.setAktiv(true);

        autoService.createAuto(dto);
        verify(autoRepository).save(any(Auto.class));
    }

    @Test
    void updateAuto_ShouldUpdateCar(){
        Auto  auto1 = new Auto();
        auto1.setId(1L);

        when(autoRepository.findById(1L))
                .thenReturn(Optional.of(auto1));

        AutoFormDTO dto = new AutoFormDTO();
        dto.setMarka("BMW");
        dto.setModell("420d");
        dto.setNapiAr(30000);
        dto.setAktiv(false);

        autoService.updateAuto(1L, dto);

        assertEquals("BMW", dto.getMarka());
        assertEquals("420d", dto.getModell());
        assertEquals(30000, dto.getNapiAr());
        assertFalse(auto1.isAktiv());

        verify(autoRepository).save(auto1);
    }
}
