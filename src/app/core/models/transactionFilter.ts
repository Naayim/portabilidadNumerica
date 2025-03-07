export interface TransactionFilter {
    page?: number;
    size?: number;
    portId?: number;
    folio?: string;
    telefono?: string;
    idMensaje?: string;
    fechaInicio?: string; // Debe enviarse en formato ISO, ej. "2023-12-11T00:00:00"
    fechaFin?: string;    // Debe enviarse en formato ISO, ej. "2025-12-11T23:59:59"
    flujo?: number;
    estatus?: number;
    donadorId?: number;
    receptorId?: number;
  }