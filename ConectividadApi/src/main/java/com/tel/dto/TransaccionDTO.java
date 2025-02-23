package com.tel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tel.model.Transaction.Estatus;
import com.tel.model.Transaction.Proceso;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransaccionDTO {
    
    private Long portId;
    private String folio;
    
    // enums para valores controlados
    private Proceso proceso;  // Enum: RECEPCIONES, DONACIONES
    private Estatus estatus;  // Enum: EN_PROGRESO, COMPLETADO
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicio;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaActualizacion;
    
    // Relaciones con Catalog
    private Long donadorId;
    private String donadorNombre;
    private Long receptorId;
    private String receptorNombre;
    
    // Campos adicionales
    private String telefono;
    private String idMensaje;
    
    // Usar enum si corresponde 
    private String flujo;  
    
    public String getPortIdDisplay() {
        return portId != null ? "PID-" + portId : "";
    }

}