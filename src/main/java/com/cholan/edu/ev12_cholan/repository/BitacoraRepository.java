package com.cholan.edu.ev12_cholan.repository;

import com.cholan.edu.ev12_cholan.model.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    List<Bitacora> findByUsuarioIdUsuario(Long idUsuario);
    List<Bitacora> findByTabla(String tabla);
    List<Bitacora> findByOperacion(String operacion);
    List<Bitacora> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Bitacora> findTop50ByOrderByFechaHoraDesc();
}
