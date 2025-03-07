import { Component, OnDestroy, OnInit } from '@angular/core';
import { TransactionDetailResponse, TransactionResponse } from '../../../core/models/transactionResponse';
import { TransactionFilter } from '../../../core/models/transactionFilter';
import { Page } from '../../../core/models/page';
import { TransactionService } from '../../../core/services/transaction-service.service';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-table',
  standalone: false,
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})

export class TableComponent implements OnInit {
  transactions: TransactionResponse[] = [];
  transactionDetails: { [key: number]: TransactionDetailResponse[] } = {};
  totalRecords: number = 0;
  currentPageRecords: number = 0;
  loading: boolean = true;

  public currentPage: number = 0;
  public pageSize: number = 20;
  private currentFilter: TransactionFilter | any = null;

  modalVisible: boolean = false;
  filtroBusqueda: any = {
    portId: '',
    folio: '',
    fechaInicio: null,
    fechaActualizacion: null,
    estatus: null,
    donador: null,
    receptor: null
  };

  estatusOptions = [
    { label: 'Todos', value: null },
    { label: 'COMPLETADO', value: 'COMPLETADO' },
    { label: 'EN PROGRESO', value: 'EN_PROGRESO' }
  ];

  donadorOptions = [
    { label: 'MAXCOM TELECOMUNICACIONES', value: 144 },
    { label: 'TELMEX', value: 151 },
    { label: 'IZZI TELECOM', value: 153 }
  ];
  receptorOptions = [
    { label: 'MEGACABLE COMUNICACIONES', value: 150 },
    { label: 'AT&T MEXICO', value: 152 },
    { label: 'AXTEL', value: 154 }
  ];


  constructor(
    private transactionService: TransactionService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    const defaultFilter: TransactionFilter = {
      fechaInicio: '2024-12-01T00:00:00',
      fechaFin: '2024-12-31T23:59:59',
      page: 0,
      size: this.pageSize
    };
    this.applyFilters(defaultFilter);
  }

  loadTransactions(): void {
    this.loading = true;
    this.transactionService.getTransactions(this.currentPage, this.pageSize).subscribe({
      next: (response: any) => {
        this.transactions = response.content;
        this.totalRecords = response.totalElements;
        this.currentPageRecords = response.totalElements < this.pageSize ? response.totalElements : this.pageSize;
        if (response.totalElements === 0) {
          this.messageService.add({
            severity: 'info',
            summary: 'Advertencia',
            detail: 'Sin datos disponibles'
          });
        }
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading transactions:', error);
        this.loading = false;
      },
    });
  }

  /* 
  aplicar filtros a las transacciones y luego obtener los datos actualizados desde el 
  servidor con base en esos filtros. Además, gestiona la paginación
  */
  applyFilters(filter: TransactionFilter): void {
    this.loading = true;
    this.currentPage = 0; // Reiniciamos a la primera página cuando se aplica un filtro nuevo
    this.currentFilter = filter; // Guardamos el filtro actual

    filter.page = this.currentPage;
    filter.size = this.pageSize;

    this.transactionService.getTransactionsByFilter(filter).subscribe({
      next: (response: Page<TransactionResponse>) => {
        this.transactions = response.content;
        this.totalRecords = response.totalElements;
        this.currentPageRecords = response.totalElements < this.pageSize ? response.totalElements : this.pageSize;

        if (response.totalElements === 0) {
          this.messageService.add({
            severity: 'info',
            summary: 'Advertencia',
            detail: 'Sin datos disponibles'
          });
        }
        this.loading = false;
      },
      error: (error) => {
        console.error('Error applying filters:', error);
        this.loading = false;
      },
    });
  }

  onPageChange(event: any): void {
    console.log(event);
    this.currentPage = event.page;
    this.pageSize = event.rows;

    if (this.currentFilter) {
      // Si hay un filtro activo, actualizamos el filtro con la nueva página y tamaño
      this.currentFilter.page = this.currentPage;
      this.currentFilter.size = this.pageSize;

      this.transactionService.getTransactionsByFilter(this.currentFilter).subscribe({
        next: (response: Page<TransactionResponse>) => {
          this.transactions = response.content;
          this.totalRecords = response.totalElements;
          this.currentPageRecords = response.totalElements < this.pageSize ? response.totalElements : this.pageSize;
          this.loading = false;
        },
        error: (error) => {
          console.error('Error applying filters:', error);
          this.loading = false;
        },
      });
    } else {
      // Si no hay filtro activo, recargamos la lista general
      this.loadTransactions();
    }
  }

  loadDetails(portId: number): void {  
    // Si ya se han cargado detalles, ocultarlos al hacer clic nuevamente
    if (!this.transactions.some(transaction => transaction.portId === portId)) {
      this.messageService.add({
        severity: 'warn',
        summary: 'Advertencia',
        detail: `El portId ${portId} no existe en la base de datos`
      });
      return;
    }

    if (this.transactionDetails[portId]) {
      delete this.transactionDetails[portId];
      return;
    }

    // Llamada a la API para obtener los detalles
    this.transactionService.getTransactionDetails(portId).subscribe({
      next: (details) => {
        console.log(`Detalles recibidos para portId ${portId}:`, details);

        if (details.length > 0) {
          this.transactionDetails[portId] = details;  // Guarda los detalles
        } else {
          this.messageService.add({
            severity: 'warn',
            summary: 'Advertencia',
            detail: 'Sin detalles disponibles'
          });
        }
      },
      error: (error) => {
        console.error('Error cargando detalles:', error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudieron cargar los detalles'
        });
      }
    });
  }

  mostrarModal() {
    this.modalVisible = true;
  }

  cerrarModal() {
    this.modalVisible = false;
  }

  limpiarFiltros() {
    this.filtroBusqueda = {
      portId: '',
      folio: '',
      fechaInicio: null,
      fechaActualizacion: null,
      estatus: null,
      donador: null,
      receptor: null
    };
  }


  filtrarTransacciones() {
    // Convertir portId a número si es que tiene algún valor
  if (this.filtroBusqueda.portId) {
    this.filtroBusqueda.portId = Number(this.filtroBusqueda.portId);
  }
    console.log("Filtros aplicados:", this.filtroBusqueda);
    this.applyFilters(this.filtroBusqueda);
    this.cerrarModal();
  }

  // Función que utiliza el endpoint de filtrado especializado
  filtrarTransaccionesDesdeAPI(): void {
    this.transactionService.filtrarPorFiltros(this.filtroBusqueda).subscribe({
      next: (data: TransactionDetailResponse[]) => {
        console.log("Datos filtrados recibidos:", data);
        // Transformamos TransactionDetailResponse a TransactionResponse
        this.transactions = data.map(detail => ({
          portId: detail.portId,
          folio: detail.folio || "SIN FOLIO",
          proceso: detail.origenDestino || "DESCONOCIDO",
          estatus: detail.destino || "DESCONOCIDO",
          fechaInicio: detail.fechaFase || new Date().toISOString(),
          fechaActualizacion: new Date().toISOString()
        }));
        console.log("Datos mapeados:", this.transactions);
      },
      error: (error) => {
        console.error("Error al filtrar:", error);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudieron aplicar los filtros'
        });
      }
    });
  }

  

}