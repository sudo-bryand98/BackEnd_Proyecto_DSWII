package com.cibertec.sisam.dao;

import com.cibertec.sisam.model.Encargado;
import com.cibertec.sisam.model.TipoDoc;
import org.springframework.data.repository.CrudRepository;

public interface ITipoDocDao extends CrudRepository<TipoDoc,Long> {

}
