package hu.pelda.autokolcsonzo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Foglalas {

    @Id
	@GeneratedValue
	private Long id;

    private String nev;
    private String email;
    private String cim;
    private String telefon;

    private LocalDate datumtol;
    private LocalDate datumig;

    private int teljesAr;

    @ManyToOne
    private Auto auto;
}
