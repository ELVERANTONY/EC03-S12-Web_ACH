package com.cholan.edu.ev12_cholan.service;

import com.cholan.edu.ev12_cholan.model.Mesa;
import com.cholan.edu.ev12_cholan.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> findById(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Mesa update(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void deleteById(Long id) {
        mesaRepository.deleteById(id);
    }

    public List<Mesa> findByEstado(String estado) {
        return mesaRepository.findByEstado(estado);
    }

    public List<Mesa> findMesasDisponibles() {
        return mesaRepository.findByEstado("DISPONIBLE");
    }

    public void cambiarEstado(Long id, String nuevoEstado) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        mesa.setEstado(nuevoEstado);
        mesaRepository.save(mesa);
    }
}
