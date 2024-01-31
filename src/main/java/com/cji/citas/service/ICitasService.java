package com.cji.citas.service;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;

public interface ICitasService {

    void crearCita(UsersDTO usersDTO, UsersDTO gestor);

}
