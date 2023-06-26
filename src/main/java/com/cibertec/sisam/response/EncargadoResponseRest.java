package com.cibertec.sisam.response;

import com.cibertec.sisam.model.Encargado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncargadoResponseRest  extends ResponseRest{

    private EncargadoResponse encargado = new EncargadoResponse();
}
