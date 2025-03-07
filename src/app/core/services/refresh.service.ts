import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RefreshService {

  private refreshTableSource = new Subject<void>();  // Emitirá un evento sin valor

  // Observable para que los componentes se suscriban
  refreshTable$ = this.refreshTableSource.asObservable();

  constructor() { }

  // Método para activar el evento de refrescar
  triggerRefresh(): void {
    this.refreshTableSource.next();
  }
}
