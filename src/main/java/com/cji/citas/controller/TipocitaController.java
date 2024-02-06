package com.cji.citas.controller;

import com.cji.citas.entity.Tipocita;
import com.cji.citas.service.ITipocitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipo")
public class TipocitaController {

    private final ITipocitaService tipocitaService;

    @Autowired
    public TipocitaController(ITipocitaService tipocitaService) {
        this.tipocitaService = tipocitaService;
    }


    @GetMapping("/cita")
    public ResponseEntity<List<Tipocita>> obtenerTodasLasCitas() {
        List<Tipocita> citas = tipocitaService.obtenerTiposCita();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

}
