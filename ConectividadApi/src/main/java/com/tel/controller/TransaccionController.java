package com.tel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tel.dto.FiltroBusquedaDTO;
import com.tel.dto.TransaccionDTO;
import com.tel.dto.TransactionDetailResponse;
import com.tel.service.TransaccionService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @GetMapping
    public ResponseEntity<Page<TransaccionDTO>> buscarTransacciones(
        @ModelAttribute FiltroBusquedaDTO filtro, // Usar @ModelAttribute para parámetros GET
        @PageableDefault(size = 10, sort = "fechaInicio", direction = DESC) Pageable pageable) {

	 System.out.println("Parámetros recibidos en la API: " + filtro); // <-- IMPRIMIR FILTROS
	
        // Aplicar filtro por defecto si no hay parámetros
        if (filtro.isEmpty()) {
            LocalDateTime hoy = LocalDateTime.now();
            filtro.setFechaInicio(hoy.toLocalDate().atStartOfDay()); // 00:00:00 del día actual
            filtro.setFechaFin(hoy); // Hora actual
        }

        // Validar rango de fechas
        if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null 
            && filtro.getFechaFin().isBefore(filtro.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha final no puede ser anterior a la fecha inicial");
        }
        
        Page<TransaccionDTO> resultados = transaccionService.buscarTransacciones(filtro, pageable);

        System.out.println("Total registros encontrados: " + resultados.getTotalElements()); // <-- IMPRIMIR RESULTADOS

        return ResponseEntity.ok(transaccionService.buscarTransacciones(filtro, pageable));
    }
    
    @GetMapping("/details")
    public ResponseEntity<List<TransactionDetailResponse>> buscarDetalles(@ModelAttribute FiltroBusquedaDTO filtro) {
        List<TransactionDetailResponse> detalles = transaccionService.buscarDetalles(filtro);
        return ResponseEntity.ok(detalles);
    }
    
    @PostMapping("/filtrar")
    public ResponseEntity<List<TransactionDetailResponse>> filtrarTransacciones(@RequestBody FiltroBusquedaDTO filtros) {
        List<TransactionDetailResponse> result = transaccionService.buscarPorFiltros(filtros);
        return ResponseEntity.ok(result);
    }
}