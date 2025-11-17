package com.ecusol.cbs.admin.dto;

import lombok.Data;

@Data
public class EntidadBancariaDto {
    private Integer entidadBancariaId;
    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String sitioWeb;
    private String emailContacto;
    private String codigoInternacional;
}
