package com.tel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.tel.dto.FiltroBusquedaDTO;
import com.tel.dto.TransaccionDTO;
import com.tel.dto.TransactionDetailResponse;
import com.tel.mapper.TransaccionMapper;
import com.tel.model.Transaction;
import com.tel.model.TransactionDetail;
import com.tel.repository.TransaccionRepository;
import com.tel.repository.TransaccionSpecification;
import com.tel.repository.TransactionDetailRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    public final TransaccionRepository transaccionRepository;
    public final TransactionDetailRepository transactionDetailRepository;
    @Autowired
    private final TransaccionMapper transaccionMapper;

    public Page<TransaccionDTO> buscarTransacciones(FiltroBusquedaDTO filtro, Pageable pageable) {
	Specification<Transaction> spec = buildSpecification(filtro);
	return transaccionRepository.findAll(spec, pageable)
		.map(transaccionMapper::toDto);
    }
    
    public List<TransactionDetailResponse> buscarPorFiltros(FiltroBusquedaDTO filtros) {
        Specification<TransactionDetail> spec = TransaccionSpecification.filtrarPorCriterios(filtros);
        List<TransactionDetail> transacciones = transactionDetailRepository.findAll(spec);
        return transacciones.stream()
                .map(transaccionMapper::toDtoDetail)
                .collect(Collectors.toList());
    }
    
    public List<TransactionDetailResponse> buscarDetalles(FiltroBusquedaDTO filtro) {
        Specification<TransactionDetail> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtrar por portId (suponiendo que portId es de tipo String en TransactionDetail)
            if (filtro.getPortId() != null) {
                predicates.add(cb.equal(root.get("portId"), String.valueOf(filtro.getPortId())));
            }
            // Filtrar por rango de fechas (usando fechaFase en TransactionDetail)
            if (filtro.getFechaInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("fechaFase"), filtro.getFechaInicio()));
            }
            if (filtro.getFechaFin() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("fechaFase"), filtro.getFechaFin()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<TransactionDetail> details = transactionDetailRepository.findAll(spec);
        return details.stream()
                      .map(transaccionMapper::toDtoDetail)
                      .collect(Collectors.toList());
    }

    private Specification<Transaction> buildSpecification(FiltroBusquedaDTO filtro) {
	return (root, query, cb) -> {
	    System.out.println("Fecha Inicio: " + filtro.getFechaInicio());
	    System.out.println("Fecha Fin: " + filtro.getFechaFin());
	    
	    List<Predicate> predicates = new ArrayList<>();

	    // Filtro por portId
	    if (filtro.getPortId() != null) {
		predicates.add(cb.equal(root.get("portId"), filtro.getPortId()));
	    }

	    // Filtro por folio
	    if (filtro.getFolio() != null && !filtro.getFolio().isEmpty()) {
		predicates.add(cb.like(root.get("folio"), "%" + filtro.getFolio() + "%"));
	    }

	    // Filtro por teléfono
	    if (filtro.getTelefono() != null && !filtro.getTelefono().isEmpty()) {
		predicates.add(cb.equal(root.get("telefono"), filtro.getTelefono()));
	    }

	    // Filtro por idMensaje
	    if (filtro.getIdMensaje() != null && !filtro.getIdMensaje().isEmpty()) {
		predicates.add(cb.equal(root.get("idMensaje"), filtro.getIdMensaje()));
	    }

	    // Filtro por rango de fechas (fechaInicio y fechaFin)
	    /*
	     * if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
	     * predicates.add(cb.between( root.get("fechaInicio"), filtro.getFechaInicio(),
	     * filtro.getFechaFin() )); }
	     */

	    if (filtro.getFechaInicio() != null) {
		    predicates.add(cb.greaterThanOrEqualTo(root.get("fechaInicio"), 
		        filtro.getFechaInicio().truncatedTo(ChronoUnit.SECONDS))); // Elimina milisegundos
		}

		if (filtro.getFechaFin() != null) {
		    predicates.add(cb.lessThanOrEqualTo(root.get("fechaInicio"), 
		        filtro.getFechaFin().truncatedTo(ChronoUnit.SECONDS))); // Elimina milisegundos
		}

	    // Filtro por flujo
	    if (filtro.getFlujo() != null) {
		predicates.add(cb.equal(root.get("flujo"), filtro.getFlujo()));
	    }

	    // Filtro por estatus
	    if (filtro.getEstatus() != null) {
		predicates.add(cb.equal(root.get("estatus"), filtro.getEstatus()));
	    }

	    // Filtro por donadorId
	    if (filtro.getDonadorId() != null) {
		predicates.add(cb.equal(root.get("donador").get("id"), filtro.getDonadorId()));
	    }

	    // Filtro por receptorId
	    if (filtro.getReceptorId() != null) {
		predicates.add(cb.equal(root.get("receptor").get("id"), filtro.getReceptorId()));
	    }

	    return cb.and(predicates.toArray(new Predicate[0]));
	};
    }
}