package com.cji.citas.service;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.dto.UsersDTO;
import com.cji.citas.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ICitasService {

    void crearCita(CitasDTO citsDTO);

    List<CitasDTO> obtenerCitasPorUsuario(String name);

    int cantidadCitasUsuario(String name);


    List<CitasDTO> obtenerCitasPorGestorYDia(String name, LocalDate diaEspecifico);
}
