package hu.pelda.autokolcsonzo;

import hu.pelda.autokolcsonzo.dto.FoglalasKeresDTO;
import hu.pelda.autokolcsonzo.model.Auto;
import hu.pelda.autokolcsonzo.model.Foglalas;
import hu.pelda.autokolcsonzo.repository.AutoRepository;
import hu.pelda.autokolcsonzo.repository.FoglalasRepository;
import hu.pelda.autokolcsonzo.service.FoglalasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoglalasServiceTest {

    @Mock
    private FoglalasRepository foglalasRepository;

    @Mock
    private AutoRepository  autoRepository;

    @InjectMocks
    private FoglalasService foglalasService;

    @Test
    void createFoglalas_Sikeres() {
        Auto auto = new Auto();
        auto.setId(1L);
        auto.setNapiAr(10000);

        when(autoRepository.findById(1L))
                .thenReturn(Optional.of(auto));

        when(foglalasRepository.findConflictingFoglalas(
                anyLong(), any(), any()))
                .thenReturn(List.of());

        when(foglalasRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        FoglalasKeresDTO dto = new FoglalasKeresDTO();
        dto.setAutoId(1L);
        dto.setNev("Teszt");
        dto.setDatumtol(LocalDate.now());
        dto.setDatumig(LocalDate.now().plusDays(2));

        Foglalas eredmeny = foglalasService.createFoglalas(dto);

        assertNotNull(eredmeny);
        assertEquals(30000, eredmeny.getTeljesAr());
    }

    @Test
    void createFoglalas_ThrowsWhenConflictExists() {
        Auto auto = new Auto();
        auto.setId(1L);

        when(autoRepository.findById(1L))
                .thenReturn(Optional.of(auto));

        when(foglalasRepository.findConflictingFoglalas(
                anyLong(), any(), any()))
                .thenReturn(List.of(new Foglalas()));

        FoglalasKeresDTO dto = new FoglalasKeresDTO();
        dto.setAutoId(1L);
        dto.setDatumtol(LocalDate.now());
        dto.setDatumig(LocalDate.now().plusDays(1));

        assertThrows(RuntimeException.class,
                () -> foglalasService.createFoglalas(dto));
    }

    @Test
    void creatFoglalas_ThrowsWhenCarNotFound() {
        when(autoRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        FoglalasKeresDTO dto = new FoglalasKeresDTO();
        dto.setAutoId(999L);

        assertThrows(RuntimeException.class,
                () -> foglalasService.createFoglalas(dto));
    }

    @Test
    void getOsszesFoglalas_ShouldReturnList() {
        List<Foglalas> foglalasok = List.of(new Foglalas(),  new Foglalas());

        when(foglalasRepository.findAll())
                .thenReturn(foglalasok);

        List<Foglalas> eredmeny =  foglalasService.getOsszesFoglalas();

        assertEquals(2, eredmeny.size());
    }
}
