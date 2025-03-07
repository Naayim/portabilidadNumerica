import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from '../models/page';
import { TransactionDetailResponse, TransactionResponse } from '../models/transactionResponse';
import { TransactionFilter } from '../models/transactionFilter';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

// Usamos el endpoint de tu API de transacciones. Asegúrate de que el puerto y la ruta sean correctos.
private readonly apiUrl = 'http://localhost:8081/api/transacciones';

constructor(private http: HttpClient) {}

/**
 * Obtiene una lista paginada de transacciones sin filtros.
 * Útil para debug o carga inicial.
 */
getTransactions(page: number = 0, size: number = 10): Observable<Page<TransactionResponse>> {
  let params = new HttpParams()
    .set('page', page.toString())
    .set('size', size.toString());
  
  return this.http.get<Page<TransactionResponse>>(this.apiUrl, { params });
}

/**
 * Obtiene una lista paginada de transacciones aplicando filtros.
 * Por ejemplo, para utilizar en Postman se usaría:
 * http://localhost:8081/api/transacciones?fechaInicio=2023-12-11T00:00:00&fechaFin=2025-12-11T23:59:59
 */
getTransactionsByFilter(filter: TransactionFilter): Observable<Page<TransactionResponse>> {
  let params = new HttpParams()
    .set('page', (filter.page || 0).toString())
    .set('size', (filter.size || 10).toString());

  if (filter.portId != null) {
    params = params.set('portId', filter.portId.toString());
  }
  if (filter.folio) {
    params = params.set('folio', filter.folio);
  }
  if (filter.telefono) {
    params = params.set('telefono', filter.telefono);
  }
  if (filter.idMensaje) {
    params = params.set('idMensaje', filter.idMensaje);
  }
  if (filter.fechaInicio) {
    params = params.set('fechaInicio', filter.fechaInicio);
  }
  if (filter.fechaFin) {
    params = params.set('fechaFin', filter.fechaFin);
  }
  if (filter.flujo != null) {
    params = params.set('flujo', filter.flujo.toString());
  }
  if (filter.estatus != null) {
    params = params.set('estatus', filter.estatus.toString());
  }
  if (filter.donadorId != null) {
    params = params.set('donadorId', filter.donadorId.toString());
  }
  if (filter.receptorId != null) {
    params = params.set('receptorId', filter.receptorId.toString());
  }

  return this.http.get<Page<TransactionResponse>>(this.apiUrl, { params });
}

 // Nuevo método para obtener detalles de una transacción
 getTransactionDetails(portId: number): Observable<TransactionDetailResponse[]> {
  return this.http.get<TransactionDetailResponse[]>(`/api/transacciones/details?portId=${portId}`);
}


  filtrarPorFiltros(filtros: any): Observable<TransactionDetailResponse[]> {
    return this.http.post<TransactionDetailResponse[]>(`${this.apiUrl}/filtrar`, filtros);
  }

}
