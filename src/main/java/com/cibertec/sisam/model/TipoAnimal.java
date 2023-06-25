package com.cibertec.sisam.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_tipoAnimal")
public class TipoAnimal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;

    @Column(length = 200, nullable = false)
    private String nombre;
}
