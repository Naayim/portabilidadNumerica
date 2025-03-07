import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: false,
  
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  username: string = '';
  password: string = '';
  firstname: string = '';
  lastname: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register(): void {
    this.authService.register(this.username, this.password, this.firstname, this.lastname).subscribe({
      next: () => {
        console.log('Registro exitoso');
        this.router.navigate(['/login']);
      },
      error: (err) => console.error('Error en el registro', err)
    });
  }

}
