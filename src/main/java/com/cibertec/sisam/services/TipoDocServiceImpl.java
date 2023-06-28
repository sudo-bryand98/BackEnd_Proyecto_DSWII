package com.cibertec.sisam.services;

import com.cibertec.sisam.dao.ITipoDocDao;
import com.cibertec.sisam.model.TipoAnimal;
import com.cibertec.sisam.model.TipoDoc;
import com.cibertec.sisam.response.TipoAnimalResponseRest;
import com.cibertec.sisam.response.TipoDocResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoDocServiceImpl implements ITipoDocService{

    @Autowired
    private ITipoDocDao tipoDocDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<TipoDocResponseRest> search() {
        TipoDocResponseRest response = new TipoDocResponseRest();

        try {
            List<TipoDoc> tipoDoc = (List<TipoDoc>) tipoDocDao.findAll();
            response.getTipoDocResponse().setTipoDoc(tipoDoc);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e) {
            response.setMetadata("Respuesta nOK", "-1", "Error en la consulta");
            e.getStackTrace();
            return new ResponseEntity<TipoDocResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TipoDocResponseRest>(response, HttpStatus.OK);
    }
}
