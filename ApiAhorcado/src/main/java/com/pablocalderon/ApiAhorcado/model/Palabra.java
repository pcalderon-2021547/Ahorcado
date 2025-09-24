package com.pablocalderon.ApiAhorcado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Palabras")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoPalabra;

    private String palabra;
    private String pista;

    // Getters y Setters
    public Integer getCodigoPalabra() { return codigoPalabra; }
    public void setCodigoPalabra(Integer codigoPalabra) { this.codigoPalabra = codigoPalabra; }

    public String getPalabra() { return palabra; }
    public void setPalabra(String palabra) { this.palabra = palabra; }

    public String getPista() { return pista; }
    public void setPista(String pista) { this.pista = pista; }
}

