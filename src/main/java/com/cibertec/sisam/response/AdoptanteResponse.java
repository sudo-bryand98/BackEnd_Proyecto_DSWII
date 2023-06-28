package com.cibertec.sisam.response;

import com.cibertec.sisam.model.Adoptante;
import lombok.Data;

import java.util.List;

@Data
public class AdoptanteResponse {

    List<Adoptante> adoptantes;
}
