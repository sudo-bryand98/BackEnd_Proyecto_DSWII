package com.cibertec.sisam.services;

import com.cibertec.sisam.response.TipoAnimalResponseRest;
import org.springframework.http.ResponseEntity;

public interface ITipoAnimalService {

    public ResponseEntity<TipoAnimalResponseRest> search();
}
