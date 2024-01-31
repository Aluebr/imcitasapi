package com.cji.citas.repository;

import com.cji.citas.entity.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CitasRepository extends JpaRepository<Citas,Long> {

    Optional<Citas> findById(Long id);

}
