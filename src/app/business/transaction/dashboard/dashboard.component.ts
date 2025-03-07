import { Component, ViewChild } from '@angular/core';
import { TableComponent } from '../table/table.component';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  @ViewChild('tableComponent') tableComponent!: TableComponent;

  // Método para abrir el filtro desde el Navbar
  abrirFiltroBusqueda() {
    console.log('Abriendo modal de filtros desde Dashboard...');
    // Llamamos al método del TableComponent que abre el modal
    this.tableComponent.mostrarModal();
  }

  // Recibir los filtros y aplicarlos a la tabla
  filtrarTransacciones(filtros: any) {
    console.log('Aplicando filtros:', filtros);
    // Aquí puedes pasar los filtros al servicio que carga la tabla
  }
}
