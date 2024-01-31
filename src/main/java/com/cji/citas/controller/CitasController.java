package com.cji.citas.controller;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.service.ICitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
public class CitasController {

    private final ICitasService citasService;

    @Autowired
    public CitasController(ICitasService citasService) {
        this.citasService = citasService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearCita(@RequestBody CitasDTO citaRequestDTO) {
        try {
            // Llama al servicio para crear una cita
            citasService.crearCita(citaRequestDTO.getUsuario(), citaRequestDTO.getGestor());
            return ResponseEntity.ok("Cita creada exitosamente");
        } catch (IllegalArgumentException e) {
            // Manejar el caso en el que el usuario o el gestor no existan
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la cita");
        }
    }
}
