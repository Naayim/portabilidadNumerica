package com.tel.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TransactionDetailResponse {

    private Long portId;
    private String idMensaje;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime fechaFase;
    private String telefono;    
    private String donadorNombre;
    private String receptorNombre;
    private String origenDestino;
    private String codigoAck;
    private String xmlMessage;
    

}
