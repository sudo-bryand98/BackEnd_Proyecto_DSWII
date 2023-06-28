package com.cibertec.sisam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import com.cibertec.sisam.model.TipoDoc;
import lombok.Data;

@Data
@Entity
@Table(name="tb_encargado")
public class Encargado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  CodEncargado;
    private String Nombre;
    private String Apellido;
    private Long Edad;
    private String Telefono;
    private String Correo;
    private String NroDocumento;
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoDoc CodTipoDoc;













}
