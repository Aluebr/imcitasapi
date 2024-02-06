package com.cji.citas.service;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public interface ICitasService {

    void crearCita(UsersDTO usersDTO, UsersDTO gestor);

    List<CitasDTO> obtenerCitasPorGestor(String name);


    List<CitasDTO> obtenerCitasPorGestorYDia(String name, LocalDate diaEspecifico);
}
