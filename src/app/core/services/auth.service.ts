import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private LOGIN_URL='http://localhost:8080/auth/login';
  private REGISTER_URL='http://localhost:8080/auth/register';
  private tokenKey = 'authToken';

  constructor(private HttpClient: HttpClient, private router:Router) { }

  register(username: string, password: string, firstname: string, lastname: string): Observable<any> {
    return this.HttpClient.post<any>('http://localhost:8080/auth/register', { 
      username, 
      password, 
      firstname, 
      lastname 
    }).pipe(
      tap(response => {
        console.log('Usuario registrado:', response);
      }),
      catchError(error => {
        console.error('Error en registro:', error);
        return throwError(() => new Error('Error al registrar usuario'));
      })
    );
  }

  login(username: string, password: string): Observable<any>{
    return this.HttpClient.post<any>(this.LOGIN_URL, {username, password}).pipe(
      tap(response => {
        if(response.token){
          console.log(response.token);
          this.setToken(response.token);
        }
      })
    )
  }

  private setToken(token: string): void{
    localStorage.setItem(this.tokenKey, token);
  }

  public getToken(): string | null {
    if (typeof window !== 'undefined'){
      return localStorage.getItem(this.tokenKey)
    }else{
      return null
    }
  }

  // Método para obtener el nombre del usuario desde el token
  getUserName(): string {
    const token = this.getToken();
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1])); // Decodifica el JWT
      console.log('Token Payload:', payload);  // Verifica qué contiene el token
  
      // Extrae el nombre antes del @ en el correo electrónico
      const email = payload.sub || '';
      const name = email.split('@')[0];  // Obtiene la parte antes del @
      return name || 'Usuario';
    }
    return 'Usuario';
  }


  isAuthenticated(): boolean {
    const token = this.getToken();
    if(!token){
      return false;
    }
    const payload = JSON.parse(atob(token.split('.')[1]));
    const exp = payload.exp * 1000;
    return Date.now() < exp;

  }

  logout(): void{
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/login']);
  }

}
