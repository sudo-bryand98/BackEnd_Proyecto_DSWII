package com.cibertec.sisam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="tb_animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String nombre;

    @Column(length = 15, nullable = false)
    private String edad;

    @Column(length = 15, nullable = false)
    private String peso;

    @Column(length = 15, nullable = false)
    private String tamanio;

    @Column(length = 200, nullable = false)
    private String raza;

    @Column(length = 300, nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties( { "hibernateLazyInitializer", "handler" })
    private TipoAnimal tipoAnimal;
}
