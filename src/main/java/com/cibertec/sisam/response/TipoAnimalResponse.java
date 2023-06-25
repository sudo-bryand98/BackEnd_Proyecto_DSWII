package com.cibertec.sisam.response;

import com.cibertec.sisam.model.TipoAnimal;
import lombok.Data;

import java.util.List;

@Data
public class TipoAnimalResponse {

    private List<TipoAnimal> tipoAnimal;
}
