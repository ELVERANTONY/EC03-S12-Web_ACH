package com.cholan.edu.ev12_cholan.service;

import com.cholan.edu.ev12_cholan.model.Compra;
import com.cholan.edu.ev12_cholan.model.DetalleCompra;
import com.cholan.edu.ev12_cholan.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private InsumoService insumoService;

    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    public Optional<Compra> findById(Long id) {
        return compraRepository.findById(id);
    }

    public Compra save(Compra compra) {
        // Calcular subtotales y actualizar stock de insumos
        BigDecimal total = BigDecimal.ZERO;
        
        for (DetalleCompra detalle : compra.getDetalles()) {
            BigDecimal subtotal = detalle.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(detalle.getCantidad()));
            detalle.setSubtotal(subtotal);
            detalle.setCompra(compra);
            total = total.add(subtotal);
            
            // Actualizar stock del insumo
            insumoService.actualizarStock(detalle.getInsumo().getIdInsumo(), detalle.getCantidad());
        }
        
        compra.setTotal(total);
        return compraRepository.save(compra);
    }

    public void deleteById(Long id) {
        compraRepository.deleteById(id);
    }
}
