package com.ecusol.cbs.admin.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DiaFeriadoDto {
    private Integer feriadoId;
    private Integer paisId;
    private Integer localizacionGeograficaId;
    private LocalDate fecha;
    private String descripcion;
    private Boolean esFinDeSemana;
}
