package com.cholan.edu.ev12_cholan.service;

import com.cholan.edu.ev12_cholan.model.Bitacora;
import com.cholan.edu.ev12_cholan.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BitacoraService {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    public List<Bitacora> findAll() {
        return bitacoraRepository.findAll();
    }

    public List<Bitacora> findRecientes() {
        return bitacoraRepository.findTop50ByOrderByFechaHoraDesc();
    }

    public List<Bitacora> findByTabla(String tabla) {
        return bitacoraRepository.findByTabla(tabla);
    }

    public List<Bitacora> findByOperacion(String operacion) {
        return bitacoraRepository.findByOperacion(operacion);
    }
}
