package com.cji.citas.service.impl;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.dto.UsersDTO;
import com.cji.citas.entity.Citas;
import com.cji.citas.entity.Users;
import com.cji.citas.repository.CitasRepository;
import com.cji.citas.repository.UserInfoRepository;
import com.cji.citas.service.ICitasService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CitasServiceImpl implements ICitasService {

    private CitasRepository citasRepository;
    private UserInfoRepository userInfoRepository;

    @Override
    public void crearCita(UsersDTO usersDTO, UsersDTO gestor) {

        Citas cita = new Citas();

        cita.setHoraInicio(LocalDateTime.of(2024, 2, 1, 10, 0)); // Ejemplo: 1 de febrero de 2024, 10:00 AM
        cita.setHoraFin(LocalDateTime.of(2024, 2, 1, 11, 0)); // Ejemplo: 1 de febrero de 2024, 11:00 AM

        Users currentUser = userInfoRepository.findByName(usersDTO.getName())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));

        cita.setUsuario(currentUser);

        Users currentGestor = userInfoRepository.findByName(gestor.getName())
                .orElseThrow(() -> new IllegalArgumentException("El gestor no existe"));

        cita.setGestor(currentGestor);
        citasRepository.save(cita);


    }



    @Override
    public List<CitasDTO> obtenerCitasPorGestor(String name) {

        Users gestor = userInfoRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("El gestor no existe"));


        List<Citas> citas = citasRepository.findByGestor(gestor);


        return citas.stream()
                .map(cita -> {
                    CitasDTO dto = new CitasDTO();
                    dto.setId(cita.getId());
                    dto.setHoraInicio(cita.getHoraInicio());
                    dto.setHoraFin(cita.getHoraFin());


                    UsersDTO usuarioDTO = new UsersDTO();
                    usuarioDTO.setId(cita.getUsuario().getId());
                    usuarioDTO.setName(cita.getUsuario().getName());
                    usuarioDTO.setEmail(cita.getUsuario().getEmail());

                    dto.setUsuario(usuarioDTO);


                    UsersDTO gestorDTO = new UsersDTO();
                    gestorDTO.setId(cita.getGestor().getId());
                    gestorDTO.setName(cita.getGestor().getName());
                    gestorDTO.setEmail(cita.getGestor().getEmail());

                    dto.setGestor(gestorDTO);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CitasDTO> obtenerCitasPorGestorYDia(String name, LocalDate diaEspecifico) {
        Users gestor = userInfoRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("El gestor no existe"));

        List<Citas> citas = citasRepository.findByGestor(gestor);

        return citas.stream()
                .filter(cita -> cita.getHoraInicio().toLocalDate().isEqual(diaEspecifico))
                .map(cita -> {
                    CitasDTO dto = new CitasDTO();
                    dto.setId(cita.getId());
                    dto.setHoraInicio(cita.getHoraInicio());
                    dto.setHoraFin(cita.getHoraFin());

                    UsersDTO usuarioDTO = new UsersDTO();
                    usuarioDTO.setId(cita.getUsuario().getId());
                    usuarioDTO.setName(cita.getUsuario().getName());
                    usuarioDTO.setEmail(cita.getUsuario().getEmail());
                    dto.setUsuario(usuarioDTO);

                    UsersDTO gestorDTO = new UsersDTO();
                    gestorDTO.setId(cita.getGestor().getId());
                    gestorDTO.setName(cita.getGestor().getName());
                    gestorDTO.setEmail(cita.getGestor().getEmail());
                    dto.setGestor(gestorDTO);

                    return dto;
                })
                .collect(Collectors.toList());
    }





}
