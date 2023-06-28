package com.cibertec.sisam.response;

import com.cibertec.sisam.model.TipoDoc;
import lombok.Data;

import java.util.List;

@Data
public class TipoDocResponse {

    private List<TipoDoc> tipoDoc;
}
