package com.cibertec.sisam.services;

import com.cibertec.sisam.model.Animal;
import com.cibertec.sisam.response.AnimalResponseRest;
import org.springframework.http.ResponseEntity;

public interface IAnimalService {

    public ResponseEntity<AnimalResponseRest> save(Animal animal, Long tipoAnimalId);
    public  ResponseEntity<AnimalResponseRest> searchById(Long id);
    public  ResponseEntity<AnimalResponseRest> deleteById(Long id);
    public  ResponseEntity<AnimalResponseRest> search();
    public ResponseEntity<AnimalResponseRest> update(Animal animal, Long tipoAnimalId, Long id);

}
