package com.tel.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.tel.dto.FiltroBusquedaDTO;
import com.tel.model.TransactionDetail;

import jakarta.persistence.criteria.Predicate;

public class TransaccionSpecification {
    
    public static Specification<TransactionDetail> filtrarPorCriterios(FiltroBusquedaDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getPortId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("portId"), filtro.getPortId()));
            }
            if (filtro.getFolio() != null && !filtro.getFolio().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("folio"), "%" + filtro.getFolio() + "%"));
            }
            if (filtro.getTelefono() != null && !filtro.getTelefono().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("telefono"), filtro.getTelefono()));
            }
            if (filtro.getIdMensaje() != null && !filtro.getIdMensaje().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("idMensaje"), filtro.getIdMensaje()));
            }
            if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
                predicates.add(criteriaBuilder.between(root.get("fechaFase"), filtro.getFechaInicio(), filtro.getFechaFin()));
            }
            if (filtro.getFlujo() != null) {
                predicates.add(criteriaBuilder.equal(root.get("origenDestino"), filtro.getFlujo()));
            }
            if (filtro.getEstatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("estatus"), filtro.getEstatus()));
            }
            if (filtro.getDonadorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("donadorId"), filtro.getDonadorId()));
            }
            if (filtro.getReceptorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("receptorId"), filtro.getReceptorId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
