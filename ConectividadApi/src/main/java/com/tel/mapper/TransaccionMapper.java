package com.tel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tel.dto.TransaccionDTO;
import com.tel.dto.TransactionDetailResponse;
import com.tel.model.Transaction;
import com.tel.model.TransactionDetail;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {
    
    TransaccionMapper INSTANCE = Mappers.getMapper(TransaccionMapper.class);

    @Mapping(source = "id", target = "portId")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "donador.id", target = "donadorId")
    @Mapping(source = "donador.nombre", target = "donadorNombre")
    @Mapping(source = "receptor.id", target = "receptorId")
    @Mapping(source = "receptor.nombre", target = "receptorNombre")
    @Mapping(source = "proceso", target = "proceso")
    @Mapping(source = "estatus", target = "estatus")
    TransaccionDTO toDto(Transaction transaccion);
    
    
    @Mapping(source = "transaction.telefono", target = "telefono")
    @Mapping(source = "transaction.idMensaje", target = "idMensaje")
    @Mapping(source = "transaction.donador.nombre", target = "donadorNombre")
    @Mapping(source = "transaction.receptor.nombre", target = "receptorNombre")
    TransactionDetailResponse toDtoDetail(TransactionDetail detail);
    
}
