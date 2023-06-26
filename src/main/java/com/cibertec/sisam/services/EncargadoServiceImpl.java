package com.cibertec.sisam.services;

import com.cibertec.sisam.dao.IEncargadoDao;
import com.cibertec.sisam.dao.ITipoDocDao;
import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.model.TipoDoc;
import com.cibertec.sisam.response.EncargadoResponseRest;
import com.cibertec.sisam.response.ResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EncargadoServiceImpl implements  IEncargadoService {

    private ITipoDocDao tipoDocDao;
    private IEncargadoDao encargadoDao;

    public EncargadoServiceImpl(ITipoDocDao tipoDocDao, IEncargadoDao encargadoDao){
        super();
        this.tipoDocDao= tipoDocDao;
        this.encargadoDao = encargadoDao;
    }

    @Override
    @Transactional
    public ResponseEntity<EncargadoResponseRest> save(Encargado encargado, Long CodTipoDoc) {
       EncargadoResponseRest response = new EncargadoResponseRest();
       List<Encargado> list = new ArrayList<>();
       try{

      Optional<TipoDoc> tipoDoc = tipoDocDao.findById(CodTipoDoc);
       if(tipoDoc.isPresent()){
        encargado.setCodTipoDoc(tipoDoc.get());

        }else {
           response.setMetadata("respuesta nok", "-1", "Documento no encontrado asociado");
           return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.NOT_FOUND);
       }
       //save encargado
           Encargado encargadoSaved = encargadoDao.save(encargado);
       if(encargadoSaved != null){
           list.add(encargadoSaved);
           response.getEncargado().setEncargados(list);
           response.setMetadata("respuesta ok", "00", "Encargado guardado");
       } else {

           response.setMetadata("respuesta nok", "-1", "Encagado no guardado");
           return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.BAD_REQUEST);
           }
       }catch (Exception e){
           e.getStackTrace();
           response.setMetadata("respuesta nok", "-1", "Error al guardar Encargado");
           return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);
       }

        return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EncargadoResponseRest> searchById(Long id) {
        EncargadoResponseRest response = new EncargadoResponseRest();
        List<Encargado> list = new ArrayList<>();
        try{
            Optional<Encargado> encarg = encargadoDao.findById(id);
            if(encarg.isPresent()){
                list.add(encarg.get());
                response.getEncargado().setEncargados(list);
                response.setMetadata("respuesta ok", "00", "Encargado  encontrado ");
                }else {
                response.setMetadata("respuesta nok", "-1", "Encargado no encontrado ");
                return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.NOT_FOUND);
                }
            }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al guardar Encargado");
            return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EncargadoResponseRest> deleteById(Long id) {
        EncargadoResponseRest response = new EncargadoResponseRest();

        try{

            //delete
            encargadoDao.deleteById(id);
            response.setMetadata("respuesta ok", "00", "Encargado  eliminado ");

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al eliminar Encargado");
            return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EncargadoResponseRest> search() {
        EncargadoResponseRest response = new EncargadoResponseRest();
        List<Encargado> list = new ArrayList<>();
        List<Encargado> listAux = new ArrayList<>();
        try{
            listAux = (List<Encargado>)  encargadoDao.findAll();

            if(listAux.size() >0) {
                listAux.stream().forEach((p) -> {
                    list.add(p);
                });

                response.getEncargado().setEncargados(list);
                response.setMetadata("respuesta ok", "00", "Encargados  encontrados ");


            }else{

                response.setMetadata("respuesta nok", "-1", "Encargados no encontrado ");
                return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al encontrar Encargados");
            return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);

        }


        return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EncargadoResponseRest> update(Encargado encargado, Long CodTipoDoc, Long id) {
        EncargadoResponseRest response = new EncargadoResponseRest();
        List<Encargado> list = new ArrayList<>();
        try{

            Optional<TipoDoc> tipoDoc = tipoDocDao.findById(CodTipoDoc);
            if(tipoDoc.isPresent()){
                encargado.setCodTipoDoc(tipoDoc.get());

            }else {
                response.setMetadata("respuesta nok", "-1", "Documento no encontrado asociado");
                return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.NOT_FOUND);
            }
            //search encargado para actualizar
            Optional <Encargado> encargadoSearch = encargadoDao.findById(id);
            if(encargadoSearch.isPresent()){
               encargadoSearch.get().setNombre(encargado.getNombre());
                encargadoSearch.get().setApellido(encargado.getApellido());
                encargadoSearch.get().setEdad(encargado.getEdad());
                encargadoSearch.get().setTelefono(encargado.getTelefono());
                encargadoSearch.get().setCorreo(encargado.getCorreo());
                encargadoSearch.get().setNroDocumento(encargado.getNroDocumento());
                Encargado encargadoToUpdate = encargadoDao.save(encargadoSearch.get());

                if(encargadoToUpdate != null){
                    list.add(encargadoToUpdate);
                    response.getEncargado().setEncargados(list);
                    response.setMetadata("respuesta ok", "00", "Encargado actualizado");
                }else {
                    response.setMetadata("respuesta nok", "-1", "Encargado no actualizado");
                    return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.BAD_REQUEST);
                }
            } else {

                response.setMetadata("respuesta nok", "-1", "Encargado no actualizado");
                return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al actualizar Encargado");
            return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);


        }

        return new ResponseEntity<EncargadoResponseRest>( response, HttpStatus.OK);

    }





}
