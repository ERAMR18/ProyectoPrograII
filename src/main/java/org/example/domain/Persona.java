package org.example.domain;


/**
 *
 * @author クリス
 */
public class Persona {
    private String nombres;
    private String apellidos;
    private Long cui;
    private String nit;
    private String domicilio;
    private String fechaNacimiento;
    private String cel;
    private Vehiculo vehiculo;
    
    public Persona() {}

    public Persona(String nombres, String apellidos, Long cui, String nit, String domicilio, String fechaNacimiento, String cel, Vehiculo vehiculo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cui = cui;
        this.nit = nit;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.cel = cel;
        this.vehiculo = vehiculo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getCui() {
        return cui;
    }

    public void setCui(Long cui) {
        this.cui = cui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
    
}
