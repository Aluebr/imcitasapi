package com.cji.citas.controller;


import com.cji.citas.dto.TokenDTO;
import com.cji.citas.dto.AuthRequest;
import com.cji.citas.entity.Users;
import com.cji.citas.service.JwtService;
import com.cji.citas.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Tag(
        name = "01 - API Gestión de usuarios",
        description = "Realizar diversas acciones con los usuarios"
)
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(
            summary = "Bienvenida",
            description = "Endpoint de bienvenida"
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
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @Operation(
            summary = "Crear nuevo usuario",
            description = "Endpoint para crear un nuevo usuario"
    )
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Users user) {
         return service.addUser(user);
    }

    @Operation(
            summary = "Bienvenida Usuario",
            description = "Mensaje de bienvenida al usuario"
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
    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @Operation(
            summary = "Bienvenida administrador",
            description = "Mensaje de bienvenida al administrador"
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
    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @Operation(
            summary = "Generar token",
            description = "Endpoint para generar el token del usuario"
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
    @PostMapping("/generateToken")
    public TokenDTO authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        TokenDTO newToken = new TokenDTO();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            newToken.setToken(jwtService.generateToken(authRequest.getUsername()));
            return newToken;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @Operation(
            summary = "Obtener email usuario",
            description = "Endpoint para obtener el email de usuario con el token"
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
    @GetMapping("/user/email")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getUserEmail(@RequestParam("token") String token) {
        try {
            String username = jwtService.extractUsername(token);
            String email = service.getEmailByUsername(username);
            return ResponseEntity.ok(email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @Operation(
            summary = "Obtener username",
            description = "Obtener username con el token"
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
    @GetMapping("/user/name")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getUserName(@RequestParam("token") String token) {
        try {
            String username = jwtService.extractUsername(token);
            String email = service.getEmailByUsername(username);
            return ResponseEntity.ok(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @Operation(
            summary = "Obtener gestores",
            description = "Obtener una lista de gestores"
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
    @GetMapping("/user/gestores")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<String>> getAdminNames(){
        try{
            List<Users> gestores = service.getAdminUsers();

            List<String> gestoresInfo = new ArrayList<>();
            gestores.forEach(gestor -> gestoresInfo.add(gestor.getName()));

                    return ResponseEntity.ok(gestoresInfo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(
            summary = "Validar token",
            description = "Endpoint para ver si el token sigue activo"
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
    @GetMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        try {
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = service.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return ResponseEntity.ok("Token válido");
            } else {
                return ResponseEntity.status(401).body("Token no válido o expirado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token no válido o expirado");
        }
    }

}


