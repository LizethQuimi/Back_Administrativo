package com.ecusol.cbs.admin.dto;

import lombok.Data;

@Data
public class LocalizacionGeograficaDto {
    private Integer localizacionGeograficaId;
    private Integer jerarquiaId;
    private Integer parentLocalizacionGeograficaId;
    private String nombre;
    private String codigo;
}
