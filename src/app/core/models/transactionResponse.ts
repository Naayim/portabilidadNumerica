export interface TransactionResponse {
    portId: number;
    folio: string;
    proceso: string;  // o del tipo de enum que uses
    estatus: string;  // idem
    fechaInicio: string;  // se espera que sea una fecha en formato ISO o parseable
    fechaActualizacion: string;

}

export interface TransactionDetailResponse {

    portId: number;
    folio: string;
    telefono:string;
    fechaFase: string;
    origenDestino: string;
    destino: string;
    ack: string;
    mensaje: string;
    estatus?: string;  // Se puede hacer opcional
    fechaActualizacion?: string;  // Se puede hacer opcional
}