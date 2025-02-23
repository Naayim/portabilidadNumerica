package com.tel.mapper;

import com.tel.dto.TransaccionDTO;
import com.tel.dto.TransactionDetailResponse;
import com.tel.model.Catalog;
import com.tel.model.Transaction;
import com.tel.model.TransactionDetail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T17:03:35-0600",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class TransaccionMapperImpl implements TransaccionMapper {

    @Override
    public TransaccionDTO toDto(Transaction transaccion) {
        if ( transaccion == null ) {
            return null;
        }

        TransaccionDTO transaccionDTO = new TransaccionDTO();

        transaccionDTO.setPortId( transaccion.getId() );
        transaccionDTO.setTelefono( transaccion.getTelefono() );
        transaccionDTO.setDonadorId( transaccionDonadorId( transaccion ) );
        transaccionDTO.setDonadorNombre( transaccionDonadorNombre( transaccion ) );
        transaccionDTO.setReceptorId( transaccionReceptorId( transaccion ) );
        transaccionDTO.setReceptorNombre( transaccionReceptorNombre( transaccion ) );
        transaccionDTO.setProceso( transaccion.getProceso() );
        transaccionDTO.setEstatus( transaccion.getEstatus() );
        transaccionDTO.setFechaActualizacion( transaccion.getFechaActualizacion() );
        transaccionDTO.setFechaInicio( transaccion.getFechaInicio() );
        transaccionDTO.setFlujo( transaccion.getFlujo() );
        transaccionDTO.setFolio( transaccion.getFolio() );
        transaccionDTO.setIdMensaje( transaccion.getIdMensaje() );

        return transaccionDTO;
    }

    @Override
    public TransactionDetailResponse toDtoDetail(TransactionDetail detail) {
        if ( detail == null ) {
            return null;
        }

        TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();

        transactionDetailResponse.setTelefono( detailTransactionTelefono( detail ) );
        transactionDetailResponse.setIdMensaje( detailTransactionIdMensaje( detail ) );
        transactionDetailResponse.setDonadorNombre( detailTransactionDonadorNombre( detail ) );
        transactionDetailResponse.setReceptorNombre( detailTransactionReceptorNombre( detail ) );
        transactionDetailResponse.setCodigoAck( detail.getCodigoAck() );
        transactionDetailResponse.setFechaFase( detail.getFechaFase() );
        transactionDetailResponse.setOrigenDestino( detail.getOrigenDestino() );
        transactionDetailResponse.setPortId( detail.getPortId() );
        transactionDetailResponse.setXmlMessage( detail.getXmlMessage() );

        return transactionDetailResponse;
    }

    private Long transaccionDonadorId(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        Catalog donador = transaction.getDonador();
        if ( donador == null ) {
            return null;
        }
        Long id = donador.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String transaccionDonadorNombre(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        Catalog donador = transaction.getDonador();
        if ( donador == null ) {
            return null;
        }
        String nombre = donador.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private Long transaccionReceptorId(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        Catalog receptor = transaction.getReceptor();
        if ( receptor == null ) {
            return null;
        }
        Long id = receptor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String transaccionReceptorNombre(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        Catalog receptor = transaction.getReceptor();
        if ( receptor == null ) {
            return null;
        }
        String nombre = receptor.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String detailTransactionTelefono(TransactionDetail transactionDetail) {
        if ( transactionDetail == null ) {
            return null;
        }
        Transaction transaction = transactionDetail.getTransaction();
        if ( transaction == null ) {
            return null;
        }
        String telefono = transaction.getTelefono();
        if ( telefono == null ) {
            return null;
        }
        return telefono;
    }

    private String detailTransactionIdMensaje(TransactionDetail transactionDetail) {
        if ( transactionDetail == null ) {
            return null;
        }
        Transaction transaction = transactionDetail.getTransaction();
        if ( transaction == null ) {
            return null;
        }
        String idMensaje = transaction.getIdMensaje();
        if ( idMensaje == null ) {
            return null;
        }
        return idMensaje;
    }

    private String detailTransactionDonadorNombre(TransactionDetail transactionDetail) {
        if ( transactionDetail == null ) {
            return null;
        }
        Transaction transaction = transactionDetail.getTransaction();
        if ( transaction == null ) {
            return null;
        }
        Catalog donador = transaction.getDonador();
        if ( donador == null ) {
            return null;
        }
        String nombre = donador.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String detailTransactionReceptorNombre(TransactionDetail transactionDetail) {
        if ( transactionDetail == null ) {
            return null;
        }
        Transaction transaction = transactionDetail.getTransaction();
        if ( transaction == null ) {
            return null;
        }
        Catalog receptor = transaction.getReceptor();
        if ( receptor == null ) {
            return null;
        }
        String nombre = receptor.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }
}
