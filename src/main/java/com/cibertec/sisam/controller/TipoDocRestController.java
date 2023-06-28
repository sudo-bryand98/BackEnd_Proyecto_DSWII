package com.cibertec.sisam.controller;


import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.model.TipoDoc;
import com.cibertec.sisam.response.EncargadoResponseRest;
import com.cibertec.sisam.response.TipoDocResponseRest;
import com.cibertec.sisam.services.IEncargadoService;
import com.cibertec.sisam.services.ITipoDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TipoDocRestController {

    @Autowired
    private ITipoDocService tipoDocService;


    @GetMapping("/tipodoc")
    public ResponseEntity<TipoDocResponseRest> search(){
        ResponseEntity<TipoDocResponseRest> response = tipoDocService.search();
        return response;
    }


    @DeleteMapping("/tipodoc/{id}")
        public ResponseEntity<TipoDocResponseRest> deleteById(@PathVariable Long id){
        ResponseEntity<TipoDocResponseRest> response = tipoDocService.deleteById(id);
        return response;
    }
}
