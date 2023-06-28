package com.cibertec.sisam.response;

import com.cibertec.sisam.model.SolicitudAdopcion;
import lombok.Data;

import java.util.List;

@Data
public class SolicitudAdoptanteResponse {
    List<SolicitudAdopcion> solicitudAdopciones;
}
