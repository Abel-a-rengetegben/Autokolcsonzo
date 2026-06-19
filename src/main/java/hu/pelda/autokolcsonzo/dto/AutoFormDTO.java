package hu.pelda.autokolcsonzo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AutoFormDTO {
    private String marka;
    private String modell;
    private Integer napiAr;
    private boolean aktiv;
    private MultipartFile kep;
}
