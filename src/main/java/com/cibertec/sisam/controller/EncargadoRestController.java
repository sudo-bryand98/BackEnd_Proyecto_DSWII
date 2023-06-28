package com.cibertec.sisam.controller;

import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.response.EncargadoResponseRest;
import com.cibertec.sisam.services.IEncargadoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class EncargadoRestController {

    private IEncargadoService encargadoService;

    public EncargadoRestController(IEncargadoService encargadoService){
        this.encargadoService = encargadoService;
    }

    @PostMapping("/encargado")
    public ResponseEntity<EncargadoResponseRest> save(
            @RequestParam("nombre") String Nombre,
            @RequestParam("apellido") String Apellido,
            @RequestParam("edad") Long Edad,
            @RequestParam("telefono") String Telefono,
            @RequestParam("correo") String Correo,
            @RequestParam("nroDocumento") String NroDocumento,
            @RequestParam("codTipoDoc") Long CodTipoDoc) throws IOException{
        Encargado encargado = new Encargado();
        encargado.setNombre(Nombre);
        encargado.setApellido(Apellido);
        encargado.setEdad(Edad);
        encargado.setTelefono(Telefono);
        encargado.setCorreo(Correo);
        encargado.setNroDocumento(NroDocumento);


        ResponseEntity<EncargadoResponseRest> response = encargadoService.save(encargado, CodTipoDoc);

        return response;
    }

    @GetMapping("/encargado/{id}")
    public ResponseEntity<EncargadoResponseRest> searchById(@PathVariable Long id){

        ResponseEntity<EncargadoResponseRest> response = encargadoService.searchById(id);
        return response;

    }

    @DeleteMapping("/encargado/{codEncargado}")
    public ResponseEntity<EncargadoResponseRest> deleteById(@PathVariable Long codEncargado){
        ResponseEntity<EncargadoResponseRest> response = encargadoService.deleteById(codEncargado);
        return response;
    }

    @GetMapping("/encargado")
    public ResponseEntity<EncargadoResponseRest> search(){
        ResponseEntity<EncargadoResponseRest> response = encargadoService.search();
        return response;
    }


    @PutMapping("/encargado/{codEncargado}")
    public ResponseEntity<EncargadoResponseRest> update(
            @RequestParam("nombre") String Nombre,
            @RequestParam("apellido") String Apellido,
            @RequestParam("edad") Long Edad,
            @RequestParam("telefono") String Telefono,
            @RequestParam("correo") String Correo,
            @RequestParam("nroDocumento") String NroDocumento,
            @RequestParam("codTipoDoc") Long CodTipoDoc,
            @PathVariable Long codEncargado) throws IOException{
        Encargado encargado = new Encargado();
        encargado.setNombre(Nombre);
        encargado.setApellido(Apellido);
        encargado.setEdad(Edad);
        encargado.setTelefono(Telefono);
        encargado.setCorreo(Correo);
        encargado.setNroDocumento(NroDocumento);


        ResponseEntity<EncargadoResponseRest> response = encargadoService.update(encargado, CodTipoDoc,codEncargado);

        return response;
    }
}
