package com.tel.dto;

import com.tel.model.Transaction.Estatus;
import com.tel.model.Transaction.Proceso;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class FiltroBusquedaDTO {

    private Long portId;

    @Pattern(regexp = "^[A-Z0-9-]{5,20}$", message = "Formato de folio inválido")
    private String folio;

    @Pattern(regexp = "^\\d{10}$", message = "Teléfono debe tener 10 dígitos")
    private String telefono;

    private String idMensaje;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaFin;

    private Proceso flujo; // Usa el enum Proceso (RECEPCIONES, DONACIONES)
    private Estatus estatus; // Usa el enum Estatus (EN_PROGRESO, COMPLETADO)

    private Long donadorId;
    private Long receptorId;

    // Validación personalizada para fechas
    public boolean isValidDateRange() {
        if (fechaInicio == null || fechaFin == null) return true;
        return !fechaFin.isBefore(fechaInicio);
    }
    
    public boolean isEmpty() {
	    return portId == null 
	        && folio == null 
	        && telefono == null 
	        && idMensaje == null 
	        && fechaInicio == null 
	        && fechaFin == null 
	        && flujo == null 
	        && estatus == null 
	        && donadorId == null 
	        && receptorId == null;
	}
}