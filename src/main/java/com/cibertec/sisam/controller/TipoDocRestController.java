package com.cibertec.sisam.controller;

import com.cibertec.sisam.response.TipoDocResponseRest;
import com.cibertec.sisam.services.ITipoDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoDocRestController {

    @Autowired
    private ITipoDocService service;

    @GetMapping("/tipodoc")
    public ResponseEntity<TipoDocResponseRest> searchTipoDoc(){
        ResponseEntity<TipoDocResponseRest> response = service.search();
        return response;
    }
}
