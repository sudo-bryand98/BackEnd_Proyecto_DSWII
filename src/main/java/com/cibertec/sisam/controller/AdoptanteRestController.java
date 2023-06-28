package com.cibertec.sisam.controller;

import com.cibertec.sisam.model.Adoptante;
import com.cibertec.sisam.model.Animal;
import com.cibertec.sisam.response.AdoptanteResponseRest;
import com.cibertec.sisam.response.AnimalResponseRest;
import com.cibertec.sisam.services.IAdoptanteService;
import com.cibertec.sisam.services.IAnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AdoptanteRestController {

    private IAdoptanteService adoptanteService;

    public AdoptanteRestController(IAdoptanteService adoptanteService){
        super();
        this.adoptanteService = adoptanteService;
    }

    @PostMapping("/adoptantes")
    public ResponseEntity<AdoptanteResponseRest> save(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("edad") String edad,
            @RequestParam("telefono") String telefono,
            @RequestParam("correo") String correo,
            @RequestParam("numdoc") String numdoc,
            @RequestParam("ocupacion") String ocupacion,
            @RequestParam("domicilio") String domicilio,
            @RequestParam("tipoDocId") Long tipoDocId) throws IOException
    {
        Adoptante adoptante = new Adoptante();
        adoptante.setNombre(nombre);
        adoptante.setApellido(apellido);
        adoptante.setEdad(edad);
        adoptante.setTelefono(telefono);
        adoptante.setCorreo(correo);
        adoptante.setNumdoc(numdoc);
        adoptante.setOcupacion(ocupacion);
        adoptante.setDomicilio(domicilio);

        ResponseEntity<AdoptanteResponseRest> response = adoptanteService.save(adoptante, tipoDocId);

        return response;

    }
    @GetMapping("/adoptantes/{id}")
    public ResponseEntity<AdoptanteResponseRest> searchById(@PathVariable Long id){
        ResponseEntity<AdoptanteResponseRest> response = adoptanteService.searchById(id);
        return response;
    }

    @DeleteMapping("/adoptantes/{id}")
    public ResponseEntity<AdoptanteResponseRest> deleteById(@PathVariable Long id){
        ResponseEntity<AdoptanteResponseRest> response = adoptanteService.deleteById(id);
        return response;
    }

    @GetMapping("/adoptantes")
    public ResponseEntity<AdoptanteResponseRest> search(){
        ResponseEntity<AdoptanteResponseRest> response = adoptanteService.search();
        return response;
    }

    @PutMapping("/adoptantes/{id}")
    public ResponseEntity<AdoptanteResponseRest> update(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("edad") String edad,
            @RequestParam("telefono") String telefono,
            @RequestParam("correo") String correo,
            @RequestParam("numdoc") String numdoc,
            @RequestParam("ocupacion") String ocupacion,
            @RequestParam("domicilio") String domicilio,
            @RequestParam("tipoDocId") Long tipoDocId,
            @PathVariable Long id) throws IOException
    {
        Adoptante adoptante = new Adoptante();
        adoptante.setNombre(nombre);
        adoptante.setApellido(apellido);
        adoptante.setEdad(edad);
        adoptante.setTelefono(telefono);
        adoptante.setCorreo(correo);
        adoptante.setNumdoc(numdoc);
        adoptante.setOcupacion(ocupacion);
        adoptante.setDomicilio(domicilio);

        ResponseEntity<AdoptanteResponseRest> response = adoptanteService.update(adoptante, tipoDocId, id);

        return response;

    }
}
