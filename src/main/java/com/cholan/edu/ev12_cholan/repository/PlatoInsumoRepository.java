package com.cholan.edu.ev12_cholan.repository;

import com.cholan.edu.ev12_cholan.model.PlatoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoInsumoRepository extends JpaRepository<PlatoInsumo, Long> {
    List<PlatoInsumo> findByPlatoIdPlato(Long idPlato);
    List<PlatoInsumo> findByInsumoIdInsumo(Long idInsumo);
}
