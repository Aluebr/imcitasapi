package com.cji.citas.service.impl;

import com.cji.citas.dto.CitasDTO;
import com.cji.citas.dto.TipocitaDTO;
import com.cji.citas.dto.UsersDTO;
import com.cji.citas.entity.Citas;
import com.cji.citas.entity.Tipocita;
import com.cji.citas.entity.Users;
import com.cji.citas.repository.CitasRepository;
import com.cji.citas.repository.TipocitaRepository;
import com.cji.citas.repository.UserInfoRepository;
import com.cji.citas.service.ICitasService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CitasServiceImpl implements ICitasService {

    private CitasRepository citasRepository;
    private UserInfoRepository userInfoRepository;
    private TipocitaRepository tipocitaRepository;

    @Override
    public void crearCita(CitasDTO citasDTO) {
        int cantidadCitasFuturas = cantidadCitasUsuario(citasDTO.getUsuario().getName());
        if (cantidadCitasFuturas >= 3) {
            throw new IllegalStateException("Ya tienes 3 citas pendientes, no puedes pedir más.");
        } else {


            Citas cita = new Citas();
            cita.setHoraInicio(citasDTO.getHoraInicio());
            cita.setHoraFin(citasDTO.getHoraFin());

            Tipocita currentTipo = tipocitaRepository.findByNombre(citasDTO.getTipoCita().getNombre())
                    .orElseThrow(() -> new IllegalArgumentException("El tipo de cita no existe"));
            cita.setTipoCita(currentTipo);

            Users currentUser = userInfoRepository.findByName(citasDTO.getUsuario().getName())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));
            cita.setUsuario(currentUser);

            Users currentGestor = userInfoRepository.findByName(citasDTO.getGestor().getName())
                    .orElseThrow(() -> new IllegalArgumentException("El gestor no existe"));
            cita.setGestor(currentGestor);

            citasRepository.save(cita);
        }

    }


    @Override
    public List<CitasDTO> obtenerCitasPorUsuario(String name) {

        Users usuario = userInfoRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("El gestor no existe"));


        List<Citas> citas = citasRepository.findByUsuario(usuario);


        return citas.stream()
                .map(cita -> {
                    CitasDTO dto = new CitasDTO();
                    dto.setId(cita.getId());
                    dto.setHoraInicio(cita.getHoraInicio());
                    dto.setHoraFin(cita.getHoraFin());

                    TipocitaDTO tipocitaDTO = new TipocitaDTO();
                    tipocitaDTO.setId(cita.getTipoCita().getId());
                    tipocitaDTO.setNombre(cita.getTipoCita().getNombre());
                    tipocitaDTO.setPrecio(cita.getTipoCita().getPrecio());
                    tipocitaDTO.setSesiones(cita.getTipoCita().getSesiones());
                    dto.setTipoCita(tipocitaDTO);

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
    public int cantidadCitasUsuario(String name) {
        Users usuario = userInfoRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));

        LocalDateTime ahora = LocalDateTime.now();

        return citasRepository.countByUsuarioAndFechaHoraAfter(usuario, ahora);

    }

    @Override
    public void borrarCita(String name, String fechaCita) {

        LocalDateTime fechaInicio = LocalDateTime.parse(fechaCita, DateTimeFormatter.ISO_LOCAL_DATE_TIME);


        Users usuario = userInfoRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));


        List<Citas> citas = citasRepository.findByUsuarioAndAndHoraInicio(usuario, fechaInicio);


        if (citas.isEmpty()) {
            throw new IllegalArgumentException("No se encontró ninguna cita para borrar con los criterios especificados.");
        }

        Citas citaParaBorrar = citas.get(0);
        citasRepository.delete(citaParaBorrar);
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

                    TipocitaDTO tipocitaDTO = new TipocitaDTO();
                    tipocitaDTO.setId(cita.getTipoCita().getId());
                    tipocitaDTO.setNombre(cita.getTipoCita().getNombre());
                    tipocitaDTO.setPrecio(cita.getTipoCita().getPrecio());
                    tipocitaDTO.setSesiones(cita.getTipoCita().getSesiones());
                    dto.setTipoCita(tipocitaDTO);

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
