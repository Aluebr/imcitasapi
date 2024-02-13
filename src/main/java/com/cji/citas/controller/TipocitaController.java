package com.cji.citas.controller;

import com.cji.citas.entity.Tipocita;
import com.cji.citas.service.ITipocitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(
        name = "02 - API Tipos de asesoría",
        description = "Realizar diversas acciones con los tipos de asesoría"
)
@RestController
@RequestMapping("/tipo")
public class TipocitaController {

    private final ITipocitaService tipocitaService;

    @Autowired
    public TipocitaController(ITipocitaService tipocitaService) {
        this.tipocitaService = tipocitaService;
    }

    @Operation(
            summary = "Obtener lista de gestiones",
            description = "Obtener una lista de tipos de gestiones"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping("/cita")
    public ResponseEntity<List<Tipocita>> obtenerTodasLasCitas() {
        List<Tipocita> citas = tipocitaService.obtenerTiposCita();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
}
