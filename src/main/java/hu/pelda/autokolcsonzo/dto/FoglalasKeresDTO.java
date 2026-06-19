package hu.pelda.autokolcsonzo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FoglalasKeresDTO {

    private Long autoId;

    private String nev;
    private String email;
    private String cim;
    private String telefon;

    private LocalDate datumtol;
    private LocalDate datumig;
}
