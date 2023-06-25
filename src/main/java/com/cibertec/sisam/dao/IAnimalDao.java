package com.cibertec.sisam.dao;

import com.cibertec.sisam.model.Animal;
import org.springframework.data.repository.CrudRepository;

public interface IAnimalDao extends CrudRepository<Animal, Long> {
}
