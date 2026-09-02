package com.instituto.semana3_pedro;

import java.io.Serializable;

public class Persona implements Serializable {
    private String dni;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String asunto;
    private String descripcion;

    public Persona(String dni, String nombres, String apellidos, String telefono, String asunto, String descripcion) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.asunto = asunto;
        this.descripcion = descripcion;
    }

    public String getDni() { return dni; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getTelefono() { return telefono; }
    public String getAsunto() { return asunto; }
    public String getDescripcion() { return descripcion; }
}