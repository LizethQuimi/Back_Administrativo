package com.ecusol.cbs.admin.dto;

import lombok.Data;

@Data
public class PaisDto {
    private Integer paisId;
    private String nombre;
    private String isoAlpha2;
    private String isoAlpha3;
    private String codigoNumerico;
}
