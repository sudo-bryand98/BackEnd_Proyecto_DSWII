package com.cibertec.sisam.services;

import com.cibertec.sisam.dao.IAdoptanteDao;
import com.cibertec.sisam.dao.ITipoDocDao;
import com.cibertec.sisam.model.Adoptante;
import com.cibertec.sisam.model.TipoDoc;
import com.cibertec.sisam.response.AdoptanteResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdoptanteServiceImpl implements IAdoptanteService{

    private ITipoDocDao tipoDocDao;
    private IAdoptanteDao adoptanteDao;

    public AdoptanteServiceImpl(ITipoDocDao tipoDocDao, IAdoptanteDao adoptanteDao){
        super();
        this.tipoDocDao = tipoDocDao;
        this.adoptanteDao = adoptanteDao;
    }

    @Override
    @Transactional
    public ResponseEntity<AdoptanteResponseRest> save(Adoptante adoptante, Long tipoDocId) {
        AdoptanteResponseRest response = new AdoptanteResponseRest();
        List<Adoptante> list = new ArrayList<>();

        try{
            Optional<TipoDoc> tipoDoc = tipoDocDao.findById(tipoDocId);

            if( tipoDoc.isPresent() ){
                adoptante.setCodTipoDoc(tipoDoc.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Tipo de documento no encontrado asociado a adoptantes");
                return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Adoptante adoptanteSaved = adoptanteDao.save(adoptante);
            if(adoptanteSaved != null){
                list.add(adoptanteSaved);
                response.getAdoptante().setAdoptantes(list);
                response.setMetadata("Respuesta ok", "00", "Adoptante guardado");
            } else {
                response.setMetadata("Respuesta nok", "-1", "Adoptante no guardado");
                return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al guardar adoptante");
            return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AdoptanteResponseRest> searchById(Long id) {

        AdoptanteResponseRest response = new AdoptanteResponseRest();
        List<Adoptante> list = new ArrayList<>();

        try{
            Optional<Adoptante> adoptante = adoptanteDao.findById(id);

            if( adoptante.isPresent() ){
                list.add(adoptante.get());
                response.getAdoptante().setAdoptantes(list);
                response.setMetadata("Respuesta OK", "00", "Adoptante encontrado");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Adoptante no encontrado");
                return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar adoptante");
            return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AdoptanteResponseRest> deleteById(Long id) {

        AdoptanteResponseRest response = new AdoptanteResponseRest();

        try{
            adoptanteDao.deleteById(id);
            response.setMetadata("Respuesta OK", "00", "Adoptante eliminado");

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar adoptante");
            return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AdoptanteResponseRest> search() {

        AdoptanteResponseRest response = new AdoptanteResponseRest();
        List<Adoptante> list = new ArrayList<>();
        List<Adoptante> listAux = new ArrayList<>();
        try{
            listAux = (List<Adoptante>) adoptanteDao.findAll();

            if( listAux.size() > 0 ){
                listAux.stream().forEach( (p) -> {
                    list.add(p);
                });
                response.getAdoptante().setAdoptantes(list);
                response.setMetadata("Respuesta OK", "00", "Adoptantes encontrados");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Adoptantes no encontrados");
                return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar Adoptante");
            return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<AdoptanteResponseRest> update(Adoptante adoptante, Long tipoDocId, Long id) {
        AdoptanteResponseRest response = new AdoptanteResponseRest();
        List<Adoptante> list = new ArrayList<>();

        try{
            Optional<TipoDoc> tipoDoc = tipoDocDao.findById(tipoDocId);

            if( tipoDoc.isPresent() ){
                adoptante.setCodTipoDoc(tipoDoc.get());
            }else {
                response.setMetadata("Respuesta nok", "-1", "Tipo doc no encontrado asociado a adoptantes");
                return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Optional<Adoptante> adoptanteSearch = adoptanteDao.findById(id);
            if(adoptanteSearch.isPresent()){
                adoptanteSearch.get().setNombre(adoptante.getNombre());
                adoptanteSearch.get().setApellido(adoptante.getApellido());
                adoptanteSearch.get().setEdad(adoptante.getEdad());
                adoptanteSearch.get().setTelefono(adoptante.getTelefono());
                adoptanteSearch.get().setCorreo(adoptante.getCorreo());
                adoptanteSearch.get().setNumdoc(adoptante.getNumdoc());
                adoptanteSearch.get().setOcupacion(adoptante.getOcupacion());
                adoptanteSearch.get().setDomicilio(adoptante.getDomicilio());
                adoptanteSearch.get().setCodTipoDoc(adoptante.getCodTipoDoc());

                Adoptante adoptanteToUpdate = adoptanteDao.save(adoptanteSearch.get());

                if(adoptanteToUpdate != null){
                    list.add(adoptanteToUpdate);
                    response.getAdoptante().setAdoptantes(list);
                    response.setMetadata("Respuesta ok", "00", "Adoptante actualizado");
                }else{
                    response.setMetadata("Respuesta nok", "-1", "Adoptante no actualizado");
                    return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("Respuesta nok", "-1", "Animal no actualizado");
                return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar animal");
            return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AdoptanteResponseRest>(response, HttpStatus.OK);
    }
}
