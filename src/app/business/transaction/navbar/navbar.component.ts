import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: false,

  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  userName: string = '';
  @Output() abrirBusqueda = new EventEmitter<void>();

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.userName = this.authService.getUserName();
  }

  loadUserName(): void {
    this.userName = this.authService.getUserName();
  }

  activarBusqueda(): void {
    // Aquí iría la lógica para activar la búsqueda especializada
    console.log('Iniciando búsqueda especializada...');
    this.abrirBusqueda.emit();
  }

  logout(): void {
    this.authService.logout();
  }

  // Método que será llamado cuando se presione el botón de refrescar
  onRefreshClick(): void {
    window.location.reload();  // Refresca la tabla
  }
}
