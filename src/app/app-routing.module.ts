import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './business/authentication/login/login.component';
import { RegisterComponent } from './business/authentication/register/register.component';
import { TableComponent } from './business/transaction/table/table.component';
import { NavbarComponent } from './business/transaction/navbar/navbar.component';
import { DashboardComponent } from './business/transaction/dashboard/dashboard.component';
import { authenticatedGuard } from './core/guards/authenticated.guard';
import { authGuard } from './core/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full' // Esto redirige automáticamente "/" a "/login"
  },
  {// PRINCIPAL Y IMPORTANDO PARA MAS IMPORTANCIA 
    path: 'login', component: LoginComponent,
    canActivate: [authenticatedGuard]
  },
  {
    path:"register", component:RegisterComponent
  },
  {
    path:"table", component:TableComponent
  },
  {
    path:"navbar", component:NavbarComponent
  },
  {
    path:"dashboard", component:DashboardComponent,
    canActivate: [authGuard]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
