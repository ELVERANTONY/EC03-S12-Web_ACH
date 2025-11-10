package com.cholan.edu.ev12_cholan.repository;

import com.cholan.edu.ev12_cholan.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findByEstado(Boolean estado);
    
    @Query("SELECT i FROM Insumo i WHERE i.stock <= i.stockMinimo AND i.estado = true")
    List<Insumo> findInsumosConStockBajo();
}
