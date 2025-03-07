import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-search-filter',
  standalone: false,
  
  templateUrl: './search-filter.component.html',
  styleUrl: './search-filter.component.css'
})
export class SearchFilterComponent {

  modalVisible: boolean = false;
  
  @Output() filtrar = new EventEmitter<any>();

  filtroBusqueda: any = {
    portId: '',
    folio: '',
    telefono: '',
    idMensaje: '',
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
    { label: 'MAXCOM TELECOM', value: 144 },
    { label: 'MEGACABLE COMUNICACIONES', value: 153 }
  ];

  receptorOptions = [
    { label: 'TELMEX', value: 151 },
    { label: 'IZZI TELECOM', value: 152 }
  ];

  mostrarModal() {
    this.modalVisible = true;
  }

  cerrarModal() {
    this.modalVisible = false;
  }

  buscarTransacciones() {
    console.log("Enviando filtros:", this.filtroBusqueda);
    this.filtrar.emit(this.filtroBusqueda);  // Enviamos los filtros al componente padre (TableComponent)
    this.cerrarModal();
  }

  limpiarFiltros() {
    this.filtroBusqueda = {
      portId: '',
      folio: '',
      telefono: '',
      idMensaje: '',
      fechaInicio: null,
      fechaActualizacion: null,
      estatus: null,
      donador: null,
      receptor: null
    };
  }

}
