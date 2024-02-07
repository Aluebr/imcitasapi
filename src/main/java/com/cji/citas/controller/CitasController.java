package com.cji.citas.controller;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.service.ICitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
            citasService.crearCita(citaRequestDTO);
            return ResponseEntity.ok("Cita creada exitosamente");
        } catch (IllegalArgumentException e) {
            // Manejar el caso en el que el usuario o el gestor no existan
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la cita");
        }
    }

    @GetMapping("/citasgestor")
    public ResponseEntity<List<CitasDTO>> obtenerCitasPorGestor(@RequestParam("user") String username) {
        try {
            List<CitasDTO> citas = citasService.obtenerCitasPorGestor(username);
            return ResponseEntity.ok(citas);
        } catch (IllegalArgumentException e) {
            // Manejo del caso en que el gestor no existe
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // Manejo de cualquier otro error no esperado
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/citagestorydia")
    public ResponseEntity<List<CitasDTO>> obtenerCitasPorGestorYDia(
            @RequestParam String name,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        try {
            List<CitasDTO> citas = citasService.obtenerCitasPorGestorYDia(name, dia);
            return ResponseEntity.ok(citas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
