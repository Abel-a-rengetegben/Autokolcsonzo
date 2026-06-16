package hu.pelda.autokolcsonzo.dto;

import java.time.LocalDate;

public class FoglalasKeresDTO {

    private Long autoId;

    private String nev;
    private String email;
    private String cim;
    private String telefon;

    private LocalDate datumtol;
    private LocalDate datumig;

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDate getDatumtol() {
        return datumtol;
    }

    public void setDatumtol(LocalDate datumtol) {
        this.datumtol = datumtol;
    }

    public LocalDate getDatumig() {
        return datumig;
    }

    public void setDatumig(LocalDate datumig) {
        this.datumig = datumig;
    }
}
