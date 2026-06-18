package hu.pelda.autokolcsonzo.dto;

public class AutoFormDTO {
    private String marka;
    private String modell;
    private Integer napiAr;
    private boolean aktiv;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public Integer getNapiAr() {
        return napiAr;
    }

    public void setNapiAr(Integer napiAr) {
        this.napiAr = napiAr;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }
}
