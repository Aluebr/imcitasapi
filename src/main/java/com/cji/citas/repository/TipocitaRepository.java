package com.cji.citas.repository;

import com.cji.citas.dto.TipocitaDTO;
import com.cji.citas.entity.Tipocita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipocitaRepository extends JpaRepository<Tipocita,Long> {

    List<Tipocita> findAll();
}
