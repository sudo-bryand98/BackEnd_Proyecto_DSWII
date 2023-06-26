package com.cibertec.sisam.services;

import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.response.EncargadoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IEncargadoService {

    public ResponseEntity<EncargadoResponseRest> save(Encargado encargado, Long CodTipoDoc);
    public ResponseEntity<EncargadoResponseRest> searchById(Long id);
    public ResponseEntity<EncargadoResponseRest> deleteById(Long id);
    public ResponseEntity<EncargadoResponseRest> search();
    public ResponseEntity<EncargadoResponseRest> update(Encargado encargado, Long CodTipoDoc, Long id);
}
