package com.pablocalderon.ApiAhorcado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoUsuario;

    private String nombreUsuario;

    @Column(nullable = false)
    private String email;

    private String contra;

    // Getters y Setters
    public Integer getCodigoUsuario() { return codigoUsuario; }
    public void setCodigoUsuario(Integer codigoUsuario) { this.codigoUsuario = codigoUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContra() { return contra; }
    public void setContra(String contra) { this.contra = contra; }
}

