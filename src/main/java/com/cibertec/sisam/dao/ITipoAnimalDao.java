package com.cibertec.sisam.dao;

import com.cibertec.sisam.model.TipoAnimal;
import org.springframework.data.repository.CrudRepository;


public interface ITipoAnimalDao extends CrudRepository<TipoAnimal, Long> {
}
