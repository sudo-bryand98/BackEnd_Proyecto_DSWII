package com.cibertec.sisam.services;

import com.cibertec.sisam.response.TipoDocResponseRest;
import org.springframework.http.ResponseEntity;

public interface ITipoDocService {

    public ResponseEntity<TipoDocResponseRest> search();
}
