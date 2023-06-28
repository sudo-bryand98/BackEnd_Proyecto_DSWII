package com.cibertec.sisam.services;

import com.cibertec.sisam.dao.IAnimalDao;
import com.cibertec.sisam.dao.ITipoAnimalDao;
import com.cibertec.sisam.model.Animal;
import com.cibertec.sisam.model.TipoAnimal;
import com.cibertec.sisam.response.AnimalResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements IAnimalService{
    private ITipoAnimalDao tipoAnimalDao;
    private IAnimalDao animalDao;
    public AnimalServiceImpl(ITipoAnimalDao tipoAnimalDao, IAnimalDao animalDao){
        super();
        this.tipoAnimalDao = tipoAnimalDao;
        this.animalDao = animalDao;
    }

    @Override
    @Transactional
    public ResponseEntity<AnimalResponseRest> save(Animal animal, Long tipoAnimalId) {
        AnimalResponseRest response = new AnimalResponseRest();
        List<Animal> list = new ArrayList<>();

        try{
            Optional<TipoAnimal> tipoAnimal = tipoAnimalDao.findById(tipoAnimalId);

            if( tipoAnimal.isPresent() ){
                animal.setTipoAnimal(tipoAnimal.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Tipo animal no encontrado asociado a animales");
                return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Animal animalSaved = animalDao.save(animal);
            if(animalSaved != null){
                list.add(animalSaved);
                response.getAnimal().setAnimales(list);
                response.setMetadata("Respuesta ok", "00", "Animal guardado");
            } else {
                response.setMetadata("Respuesta nok", "-1", "Animal no guardado");
                return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al guardar animal");
            return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AnimalResponseRest> searchById(Long id) {

        AnimalResponseRest response = new AnimalResponseRest();
        List<Animal> list = new ArrayList<>();

        try{
            Optional<Animal> animal = animalDao.findById(id);

            if( animal.isPresent() ){
                list.add(animal.get());
                response.getAnimal().setAnimales(list);
                response.setMetadata("Respuesta OK", "00", "Animal encontrado");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Animal no encontrado");
                return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar animal");
            return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AnimalResponseRest> deleteById(Long id) {
        AnimalResponseRest response = new AnimalResponseRest();

        try{
            animalDao.deleteById(id);
            response.setMetadata("Respuesta OK", "00", "Animal eliminado");

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar animal");
            return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AnimalResponseRest> search() {
        AnimalResponseRest response = new AnimalResponseRest();
        List<Animal> list = new ArrayList<>();
        List<Animal> listAux = new ArrayList<>();
        try{
            listAux = (List<Animal>) animalDao.findAll();

            if( listAux.size() > 0 ){
                listAux.stream().forEach( (p) -> {
                    list.add(p);
                });
                response.getAnimal().setAnimales(list);
                response.setMetadata("Respuesta OK", "00", "Animales encontrados");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Animales no encontrados");
                return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar animal");
            return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AnimalResponseRest> update(Animal animal, Long tipoAnimalId, Long id) {
        AnimalResponseRest response = new AnimalResponseRest();
        List<Animal> list = new ArrayList<>();

        try{
            Optional<TipoAnimal> tipoAnimal = tipoAnimalDao.findById(tipoAnimalId);

            if( tipoAnimal.isPresent() ){
                animal.setTipoAnimal(tipoAnimal.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Tipo animal no encontrado asociado a animales");
                return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Optional<Animal> animalSearch = animalDao.findById(id);
            if(animalSearch.isPresent()){
                animalSearch.get().setNombre(animal.getNombre());
                animalSearch.get().setEdad(animal.getEdad());
                animalSearch.get().setPeso(animal.getPeso());
                animalSearch.get().setTamanio(animal.getTamanio());
                animalSearch.get().setRaza(animal.getRaza());
                animalSearch.get().setDescripcion(animal.getDescripcion());
                animalSearch.get().setTipoAnimal(animal.getTipoAnimal());

                Animal animalToUpdate = animalDao.save(animalSearch.get());

                if(animalToUpdate != null){
                    list.add(animalToUpdate);
                    response.getAnimal().setAnimales(list);
                    response.setMetadata("Respuesta ok", "00", "Animal actualizado");
                }else{
                    response.setMetadata("Respuesta nok", "-1", "Animal no actualizado");
                    return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("Respuesta nok", "-1", "Animal no actualizado");
                return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar animal");
            return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AnimalResponseRest>(response, HttpStatus.OK);
    }
}
