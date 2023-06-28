package com.cibertec.sisam.services;

import com.cibertec.sisam.dao.IAnimalDao;
import com.cibertec.sisam.dao.IEncargadoDao;
import com.cibertec.sisam.dao.ITipoAnimalDao;
import com.cibertec.sisam.dao.ITipoDocDao;
import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.model.TipoAnimal;
import com.cibertec.sisam.model.TipoDoc;
import com.cibertec.sisam.response.EncargadoResponseRest;
import com.cibertec.sisam.response.TipoAnimalResponseRest;
import com.cibertec.sisam.response.TipoDocResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoDocServiceImpl  implements ITipoDocService {


    private ITipoDocDao tipoDocDao;



    public TipoDocServiceImpl(ITipoDocDao tipoDocDao ){
        super();
        this.tipoDocDao= tipoDocDao;

    }


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<TipoDocResponseRest> search() {
        TipoDocResponseRest response = new TipoDocResponseRest();
        List<TipoDoc> list = new ArrayList<>();
        List<TipoDoc> listAux = new ArrayList<>();
        try{
            listAux = (List<TipoDoc>)  tipoDocDao.findAll();

            if(listAux.size() >0) {
                listAux.stream().forEach((p) -> {
                    list.add(p);
                });

                response.getTipoDocumentoResponse().setTipoDoc(list);
                response.setMetadata("respuesta ok", "00", "Encargados  encontrados ");


            }else{

                response.setMetadata("respuesta nok", "-1", "Encargados no encontrado ");
                return new ResponseEntity<TipoDocResponseRest>( response, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al encontrar Encargados");
            return new ResponseEntity<TipoDocResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);

        }


        return new ResponseEntity<TipoDocResponseRest>( response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDocResponseRest> searchById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<TipoDocResponseRest> deleteById(Long id) {
        TipoDocResponseRest response = new TipoDocResponseRest();

        try{

            //delete
            tipoDocDao.deleteById(id);
            response.setMetadata("respuesta ok", "00", " eliminado ");

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al eliminar  ");
            return new ResponseEntity<TipoDocResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TipoDocResponseRest>( response, HttpStatus.OK);
    }


}
