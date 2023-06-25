package com.cibertec.sisam.controller;

import com.cibertec.sisam.response.TipoAnimalResponseRest;
import com.cibertec.sisam.services.ITipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoAnimalRestController {

    @Autowired
    private ITipoAnimalService service;

    @GetMapping("/tipoanimales")
    public ResponseEntity<TipoAnimalResponseRest> searchTipoAnimal(){
        ResponseEntity<TipoAnimalResponseRest> response = service.search();
        return response;
    }
}
