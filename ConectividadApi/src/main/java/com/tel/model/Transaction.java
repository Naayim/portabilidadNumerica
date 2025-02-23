package com.tel.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacciones", uniqueConstraints = @UniqueConstraint(columnNames = "folio"))
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Renombrado a "id" para claridad

    @Column(nullable = false, unique = true)
    private String folio;

    @Enumerated(EnumType.STRING)
    private Proceso proceso; // Usa enums

    @Enumerated(EnumType.STRING)
    private Estatus estatus;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false)
    private String telefono;

    private String idMensaje;

    @Column(nullable = false)
    private String flujo;

    @ManyToOne
    @JoinColumn(name = "donador_id")
    private Catalog donador;

    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Catalog receptor;

    // Enums
    public enum Proceso {
        RECEPCIONES, DONACIONES
    }

    public enum Estatus {
        EN_PROGRESO, COMPLETADO
    }
}