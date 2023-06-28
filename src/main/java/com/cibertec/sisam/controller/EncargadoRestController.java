package com.cibertec.sisam.controller;

import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.response.EncargadoResponseRest;
import com.cibertec.sisam.services.IEncargadoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins ={"http://localhost:4200"})
public class EncargadoRestController {

    private IEncargadoService encargadoService;

    public EncargadoRestController(IEncargadoService encargadoService){
        this.encargadoService = encargadoService;
    }

    @PostMapping("/encargado")
    public ResponseEntity<EncargadoResponseRest> save(
            //@RequestParam("CodEncargado") Long CodEncargado,
            @RequestParam("Nombre") String Nombre,
            @RequestParam("Apellido") String Apellido,
            @RequestParam("Edad") Long Edad,
            @RequestParam("Telefono") String Telefono,
            @RequestParam("Correo") String Correo,
            @RequestParam("NroDocumento") String NroDocumento,
            @RequestParam("CodTipoDoc") Long CodTipoDoc) throws IOException{
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

    @DeleteMapping("/encargado/{id}")
    public ResponseEntity<EncargadoResponseRest> deleteById(@PathVariable Long id){
        ResponseEntity<EncargadoResponseRest> response = encargadoService.deleteById(id);
        return response;
    }

    @GetMapping("/encargado")
    public ResponseEntity<EncargadoResponseRest> search(){
        ResponseEntity<EncargadoResponseRest> response = encargadoService.search();
        return response;
    }


    @PutMapping("/encargado/{id}")
    public ResponseEntity<EncargadoResponseRest> update(
            //@RequestParam("CodEncargado") Long CodEncargado,
            @RequestParam("Nombre") String Nombre,
            @RequestParam("Apellido") String Apellido,
            @RequestParam("Edad") Long Edad,
            @RequestParam("Telefono") String Telefono,
            @RequestParam("Correo") String Correo,
            @RequestParam("NroDocumento") String NroDocumento,
            @RequestParam("CodTipoDoc") Long CodTipoDoc,
            @PathVariable Long id) throws IOException{
        Encargado encargado = new Encargado();
        encargado.setNombre(Nombre);
        encargado.setApellido(Apellido);
        encargado.setEdad(Edad);
        encargado.setTelefono(Telefono);
        encargado.setCorreo(Correo);
        encargado.setNroDocumento(NroDocumento);


        ResponseEntity<EncargadoResponseRest> response = encargadoService.update(encargado, CodTipoDoc,id);

        return response;
    }








}
