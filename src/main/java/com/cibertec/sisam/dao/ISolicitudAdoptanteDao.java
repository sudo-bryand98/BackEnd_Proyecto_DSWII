package com.cibertec.sisam.dao;

import com.cibertec.sisam.model.SolicitudAdopcion;
import org.springframework.data.repository.CrudRepository;

public interface ISolicitudAdoptanteDao extends CrudRepository<SolicitudAdopcion, Long> {
}
