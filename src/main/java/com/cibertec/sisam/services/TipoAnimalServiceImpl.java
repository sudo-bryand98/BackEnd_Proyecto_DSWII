package com.cibertec.sisam.services;

import com.cibertec.sisam.dao.ITipoAnimalDao;
import com.cibertec.sisam.model.TipoAnimal;
import com.cibertec.sisam.response.TipoAnimalResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoAnimalServiceImpl implements ITipoAnimalService{

    @Autowired
    private ITipoAnimalDao tipoAnimalDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<TipoAnimalResponseRest> search() {
        TipoAnimalResponseRest response = new TipoAnimalResponseRest();

        try {
            List<TipoAnimal> tipoAnimal = (List<TipoAnimal>) tipoAnimalDao.findAll();
            response.getTipoAnimalResponse().setTipoAnimal(tipoAnimal);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
        }catch (Exception e) {
            response.setMetadata("Respuesta nOK", "-1", "Error en la consulta");
            e.getStackTrace();
            return new ResponseEntity<TipoAnimalResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TipoAnimalResponseRest>(response, HttpStatus.OK);
    }
}
