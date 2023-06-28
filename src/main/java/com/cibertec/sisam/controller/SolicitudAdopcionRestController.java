package com.cibertec.sisam.controller;

import com.cibertec.sisam.model.SolicitudAdopcion;
import com.cibertec.sisam.response.SolicitudAdoptanteResponseRest;
import com.cibertec.sisam.services.ISolicitudAdoptanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class SolicitudAdopcionRestController {

    private ISolicitudAdoptanteService solicitudAdoptanteService;

    public SolicitudAdopcionRestController(ISolicitudAdoptanteService solicitudAdoptanteService) {
        this.solicitudAdoptanteService = solicitudAdoptanteService;
    }

    @PostMapping("/solicitudes")
    public ResponseEntity<SolicitudAdoptanteResponseRest> save(@RequestParam("fechasolicitud") Date fechasolicitud,
                                                               @RequestParam("observaciones") String observaciones,
                                                               @RequestParam("id") Long id,
                                                               @RequestParam("ida") Long ida,
                                                               @RequestParam("ide") Long ide) throws IOException {
        SolicitudAdopcion solicitudAdopcion = new SolicitudAdopcion();
        solicitudAdopcion.setFechasolicitud(fechasolicitud);
        solicitudAdopcion.setObservaciones(observaciones);
        ResponseEntity<SolicitudAdoptanteResponseRest> response = solicitudAdoptanteService.save(solicitudAdopcion, id, ida, ide);

        return response;
    }

    @GetMapping("/solicitudes")
    public ResponseEntity<SolicitudAdoptanteResponseRest> search(){
        ResponseEntity<SolicitudAdoptanteResponseRest> response = solicitudAdoptanteService.search();
        return response;
    }

    @GetMapping("/solicitudes/{idsolicitud}")
    public ResponseEntity<SolicitudAdoptanteResponseRest> searchProductoById(@PathVariable Long idsolicitud){
        ResponseEntity<SolicitudAdoptanteResponseRest> response = solicitudAdoptanteService.searchById(idsolicitud);
        return response;
    }
}
