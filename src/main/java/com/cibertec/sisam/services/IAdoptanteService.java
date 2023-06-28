package com.cibertec.sisam.services;

import com.cibertec.sisam.model.Adoptante;
import com.cibertec.sisam.response.AdoptanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IAdoptanteService {

    public ResponseEntity<AdoptanteResponseRest> save(Adoptante adoptante, Long tipoDocId);
    public  ResponseEntity<AdoptanteResponseRest> searchById(Long id);
    public  ResponseEntity<AdoptanteResponseRest> deleteById(Long id);
    public  ResponseEntity<AdoptanteResponseRest> search();
    public ResponseEntity<AdoptanteResponseRest> update(Adoptante adoptante, Long tipoDocId, Long id);
}
