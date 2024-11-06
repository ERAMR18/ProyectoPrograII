/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.domain;

import java.sql.Date;

/**
 *
 * @author クリス
 */
public class Estacionamiento {
    private Integer estacionamientoId;
    private String placa;
    private Date fechaRegistro;
    private Boolean disponible;

    public Integer getEstacionamientoId() {
        return estacionamientoId;
    }

    public void setEstacionamientoId(Integer estacionamientoId) {
        this.estacionamientoId = estacionamientoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
}
