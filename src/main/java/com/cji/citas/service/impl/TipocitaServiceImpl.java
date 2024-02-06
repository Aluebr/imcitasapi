package com.cji.citas.service;

import com.cji.citas.entity.Tipocita;
import com.cji.citas.repository.TipocitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipocitaServiceImpl implements ITipocitaService {

    private final TipocitaRepository tipocitaRepository;

    @Autowired
    public TipocitaServiceImpl(TipocitaRepository tipocitaRepository) {
        this.tipocitaRepository = tipocitaRepository;
    }



    @Override
    public List<Tipocita> obtenerTiposCita() {
        return tipocitaRepository.findAll();
    }
}
