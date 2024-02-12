package com.cji.citas.repository;

import com.cji.citas.entity.Citas;
import com.cji.citas.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface CitasRepository extends JpaRepository<Citas,Long> {

    Optional<Citas> findById(Long id);


    List<Citas> findByGestor(Users gestor);
    @Query("SELECT COUNT(c) FROM Citas c WHERE c.usuario = :usuario AND c.horaInicio > :ahora")
    int countByUsuarioAndFechaHoraAfter(Users usuario, LocalDateTime ahora);
    List<Citas> findByUsuario(Users gestor);

    List<Citas> findByUsuarioAndAndHoraInicio(Users usuario, LocalDateTime fechaInicio);

}
