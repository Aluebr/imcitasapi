package com.cji.citas.controller;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.service.ICitasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Tag(
        name = "03 - API Gestión de citas",
        description = "Realizar diversas acciones con las citas"
)
@RestController
@RequestMapping("/citas")
public class CitasController {

    private final ICitasService citasService;

    @Autowired
    public CitasController(ICitasService citasService) {
        this.citasService = citasService;
    }
    @Operation(
            summary = "Crear cita",
            description = "Crear una cita personalizada"
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
    @SecurityRequirement(name="bearerAuth")
    @PostMapping("/crear")
    public ResponseEntity<String> crearCita(@RequestBody CitasDTO citaRequestDTO) {
        try {
            citasService.crearCita(citaRequestDTO);
            return ResponseEntity.ok("Cita creada exitosamente");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la cita");
        }
    }
    @Operation(
            summary = "Borrar cita",
            description = "Eliminar una cita por nombre de usuario y fecha"
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
    @SecurityRequirement(name="bearerAuth")
    @DeleteMapping("/borrar")
    public ResponseEntity<String> borrarCita(@RequestParam String name, @RequestParam String fechaCita) {
        try {
            citasService.borrarCita(name, fechaCita);
            return ResponseEntity.ok("Cita borrada con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al borrar la cita.");
        }
    }
    @Operation(
            summary = "Obtener lista de citas usuario",
            description = "Obtener una lista de citas por usuario"
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
    @GetMapping("/citasusuario")
    public ResponseEntity<List<CitasDTO>> obtenerCitasPorUsuario(@RequestParam("user") String username) {
        try {
            List<CitasDTO> citas = citasService.obtenerCitasPorUsuario(username);
            return ResponseEntity.ok(citas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().body(null);
        }
    }
    @Operation(
            summary = "Obtener lista de citas gestor",
            description = "Obtener una lista de citas por gestor y día"
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
    @Operation(
            summary = "Obtener cantidad de citas usuario",
            description = "Obtener la cantidad de citas de un usuario"
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
    @GetMapping("/cantidad")
    public ResponseEntity<?> cantidadCitasUsuario(@RequestParam("userName") String userName) {
        try {
            int cantidad = citasService.cantidadCitasUsuario(userName);
            return ResponseEntity.ok(cantidad);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener la cantidad de citas");
        }
    }
}
