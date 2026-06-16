package hu.pelda.autokolcsonzo.model;

import jakarta.persistence.*;

@Entity
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marka;
    private String modell;
    private int napiAr;
    private String kepURL;
    private boolean aktiv = true;
}
