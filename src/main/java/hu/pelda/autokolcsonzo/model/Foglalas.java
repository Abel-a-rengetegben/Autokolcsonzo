package hu.pelda.autokolcsonzo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

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
