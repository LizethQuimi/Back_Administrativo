package com.ecusol.cbs.admin.dto;

import lombok.Data;

@Data
public class SucursalDto {
    private Integer sucursalId;
    private Integer localizacionGeograficaId;
    private Integer entidadBancariaId;
    private String codigoUnico;
    private String nombre;
    private String direccion;
    private String telefono;
    private Double latitud;
    private Double longitud;
    private Boolean activa;
    private String fechaCreacion;
}
