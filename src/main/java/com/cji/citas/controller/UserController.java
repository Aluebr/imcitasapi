package com.cji.citas.controller;


import com.cji.citas.dto.TokenDTO;
import com.cji.citas.dto.UsersDTO;
import com.cji.citas.entity.AuthRequest;
import com.cji.citas.entity.Users;
import com.cji.citas.repository.UserInfoRepository;
import com.cji.citas.service.JwtService;
import com.cji.citas.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Users user) {
         return service.addUser(user);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

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

}


