package com.cibertec.sisam.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name="tb_TipoDoc")
public class TipoDoc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long CodTipoDoc;
    String NombreTipoDoc;
}
