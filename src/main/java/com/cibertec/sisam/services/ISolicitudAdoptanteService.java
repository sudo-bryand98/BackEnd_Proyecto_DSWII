package com.cibertec.sisam.services;

import com.cibertec.sisam.model.SolicitudAdopcion;
import com.cibertec.sisam.response.SolicitudAdoptanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface ISolicitudAdoptanteService {
    public ResponseEntity<SolicitudAdoptanteResponseRest> save(SolicitudAdopcion solicitud, Long animalId, Long adoptanteId, Long encargadoId);
    public ResponseEntity<SolicitudAdoptanteResponseRest> search();
    public ResponseEntity<SolicitudAdoptanteResponseRest> searchById(Long idsolicitud);
}
