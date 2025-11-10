package com.cholan.edu.ev12_cholan.repository;

import com.cholan.edu.ev12_cholan.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    Optional<Mesa> findByNumero(Integer numero);
    List<Mesa> findByEstado(String estado);
    boolean existsByNumero(Integer numero);
}
