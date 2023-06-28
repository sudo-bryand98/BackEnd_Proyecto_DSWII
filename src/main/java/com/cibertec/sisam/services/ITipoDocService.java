package com.cibertec.sisam.services;

import com.cibertec.sisam.response.AnimalResponseRest;
import com.cibertec.sisam.response.EncargadoResponseRest;
import com.cibertec.sisam.response.TipoDocResponseRest;
import org.springframework.http.ResponseEntity;

public interface ITipoDocService {

    public ResponseEntity<TipoDocResponseRest> search();

    public ResponseEntity<TipoDocResponseRest> searchById(Long id);
    public ResponseEntity<TipoDocResponseRest>deleteById(Long id);
}
