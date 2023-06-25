package com.cibertec.sisam.controller;

import com.cibertec.sisam.model.Animal;
import com.cibertec.sisam.response.AnimalResponseRest;
import com.cibertec.sisam.services.IAnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimalRestController {

    private IAnimalService animalService;

    public AnimalRestController(IAnimalService animalService){
        super();
        this.animalService = animalService;
    }

    @PostMapping("/animales")
    public ResponseEntity<AnimalResponseRest> save(
            @RequestParam("nombre") String nombre,
            @RequestParam("edad") String edad,
            @RequestParam("peso") String peso,
            @RequestParam("tamanio") String tamanio,
            @RequestParam("raza") String raza,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("tipoAnimalId") Long tipoAnimalId) throws IOException
     {
         Animal animal = new Animal();
         animal.setNombre(nombre);
         animal.setEdad(edad);
         animal.setPeso(peso);
         animal.setTamanio(tamanio);
         animal.setRaza(raza);
         animal.setDescripcion(descripcion);

         ResponseEntity<AnimalResponseRest> response = animalService.save(animal, tipoAnimalId);

         return response;

    }
    @GetMapping("/animales/{id}")
    public ResponseEntity<AnimalResponseRest> searchById(@PathVariable Long id){
        ResponseEntity<AnimalResponseRest> response = animalService.searchById(id);
        return response;
    }

    @DeleteMapping("/animales/{id}")
    public ResponseEntity<AnimalResponseRest> deleteById(@PathVariable Long id){
        ResponseEntity<AnimalResponseRest> response = animalService.deleteById(id);
        return response;
    }

    @GetMapping("/animales")
    public ResponseEntity<AnimalResponseRest> search(){
        ResponseEntity<AnimalResponseRest> response = animalService.search();
        return response;
    }

    @PutMapping("/animales/{id}")
    public ResponseEntity<AnimalResponseRest> update(
            @RequestParam("nombre") String nombre,
            @RequestParam("edad") String edad,
            @RequestParam("peso") String peso,
            @RequestParam("tamanio") String tamanio,
            @RequestParam("raza") String raza,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("tipoAnimalId") Long tipoAnimalId,
            @PathVariable Long id ) throws IOException
    {
        Animal animal = new Animal();
        animal.setNombre(nombre);
        animal.setEdad(edad);
        animal.setPeso(peso);
        animal.setTamanio(tamanio);
        animal.setRaza(raza);
        animal.setDescripcion(descripcion);

        ResponseEntity<AnimalResponseRest> response = animalService.update(animal, tipoAnimalId, id);

        return response;

    }




}
