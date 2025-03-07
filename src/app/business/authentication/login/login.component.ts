import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router){

  }

  login(): void{
    this.authService.login(this.username, this.password).subscribe({
      next: () => this.router.navigate(['/dashboard']),
      error: (err) => console.error('Login erronio', err)
    })
  }

}
