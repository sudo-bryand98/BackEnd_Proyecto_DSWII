package com.cibertec.sisam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="tb_adoptante")
public class Adoptante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String nombre;
    private String apellido;
    private String edad;
    private String telefono;
    private String correo;
    private String numdoc;
    private String ocupacion;
    private String domicilio;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoDoc codTipoDoc;
}
