package com.cibertec.sisam.services;

import com.cibertec.sisam.dao.IAdoptanteDao;
import com.cibertec.sisam.dao.IAnimalDao;
import com.cibertec.sisam.dao.IEncargadoDao;
import com.cibertec.sisam.dao.ISolicitudAdoptanteDao;
import com.cibertec.sisam.model.Adoptante;
import com.cibertec.sisam.model.Animal;
import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.model.SolicitudAdopcion;
import com.cibertec.sisam.response.SolicitudAdoptanteResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudAdoptanteServiceImpl implements ISolicitudAdoptanteService {

    private IAnimalDao animalDao;
    private IAdoptanteDao adoptanteDao;
    private IEncargadoDao encargadoDao;
    private ISolicitudAdoptanteDao solicitudAdoptanteDao;

    public SolicitudAdoptanteServiceImpl(IAnimalDao animalDao, IAdoptanteDao adoptanteDao, IEncargadoDao encargadoDao, ISolicitudAdoptanteDao solicitudAdoptanteDao) {
        super();
        this.animalDao = animalDao;
        this.adoptanteDao = adoptanteDao;
        this.encargadoDao = encargadoDao;
        this.solicitudAdoptanteDao = solicitudAdoptanteDao;
    }

    @Override
    @Transactional
    public ResponseEntity<SolicitudAdoptanteResponseRest> save(SolicitudAdopcion solicitud, Long animalId, Long adoptanteId, Long encargadoId) {
        SolicitudAdoptanteResponseRest response = new SolicitudAdoptanteResponseRest();
        List<SolicitudAdopcion> list = new ArrayList<>();

        try{
            //BUSCAR ANIMAL ENVIADO A LA SOLICITUD
            Optional<Animal> animal = animalDao.findById(animalId);
            //BUSCAR AOPTANTE ENVIADO A LA SOLICITUD
            Optional<Adoptante> adoptante = adoptanteDao.findById(adoptanteId);
            //BUSCAR ENCARGADO ENVIADO A LA SOLICITUD
            Optional<Encargado> encargado = encargadoDao.findById(adoptanteId);

            if(animal.isPresent() && adoptante.isPresent() && encargado.isPresent()){
                solicitud.setAnimal(animal.get());
                solicitud.setAdoptante(adoptante.get());
                solicitud.setEncargado(encargado.get());
            }else{
                response.setMetadata("respuesta nok","-1","Animal, adoptante o encargado no encontrados asociado a la solicitud");
                return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            // GUARDAR ALMACEN
            SolicitudAdopcion solicitudSaved = solicitudAdoptanteDao.save(solicitud);

            if(solicitudSaved != null){
                list.add(solicitudSaved);
                response.getSolicitudAdoptanteResponse().setSolicitudAdopciones(list);
                response.setMetadata("respuesta ok","00","Solicitud guardada");
            }else{
                response.setMetadata("respuesta nok","-1","Solicitud no guardada");
                return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al guardar solicitud");
            return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SolicitudAdoptanteResponseRest> search() {
        SolicitudAdoptanteResponseRest response = new SolicitudAdoptanteResponseRest();
        List<SolicitudAdopcion> list = new ArrayList<>();
        List<SolicitudAdopcion> listAux = new ArrayList<>();

        try{
            //LISTAR SOLICITUDES
            listAux = (List<SolicitudAdopcion>) solicitudAdoptanteDao.findAll();

            if(listAux.size() > 0){
                listAux.stream().forEach((p) ->{
                    list.add(p);
                });

                response.getSolicitudAdoptanteResponse().setSolicitudAdopciones(list);
                response.setMetadata("respuesta ok","00","solicitudes encontradas");
            }else {
                response.setMetadata("respuesta nok","-1","Solicitudes no encontradas");
                return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al buscar las solicitudes");
            return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SolicitudAdoptanteResponseRest> searchById(Long idsolicitud) {
        SolicitudAdoptanteResponseRest response = new SolicitudAdoptanteResponseRest();
        List<SolicitudAdopcion> list = new ArrayList<>();

        try{
            //BUSCAR PRODUCTO POR ID
            Optional<SolicitudAdopcion> solicitudAdopcion = solicitudAdoptanteDao.findById(idsolicitud);

            if(solicitudAdopcion.isPresent()){
                list.add(solicitudAdopcion.get());
                response.getSolicitudAdoptanteResponse().setSolicitudAdopciones(list);
                response.setMetadata("respuesta ok","00","solicitud encontrada");
            }else{
                response.setMetadata("respuesta nok","-1","Producto no encontrado");
                return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","Error al buscar producto por ID");
            return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<SolicitudAdoptanteResponseRest>(response, HttpStatus.OK);
    }
}
