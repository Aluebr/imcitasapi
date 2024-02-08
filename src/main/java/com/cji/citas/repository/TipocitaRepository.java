package com.cji.citas.repository;

import com.cji.citas.dto.TipocitaDTO;
import com.cji.citas.entity.Citas;
import com.cji.citas.entity.Tipocita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipocitaRepository extends JpaRepository<Tipocita,Long> {

    Optional<Tipocita> findById(Long id);
    Optional<Tipocita> findByNombre(String nombre);
    List<Tipocita> findAll();
}
