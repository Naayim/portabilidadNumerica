import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './business/authentication/login/login.component';
import { RegisterComponent } from './business/authentication/register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './business/transaction/dashboard/dashboard.component';
import { TableComponent } from './business/transaction/table/table.component';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { NavbarComponent } from './business/transaction/navbar/navbar.component';
import { SearchFilterComponent } from './business/transaction/search-filter/search-filter.component';
import { DropdownModule } from 'primeng/dropdown';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { DatePickerModule } from 'primeng/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    TableComponent,
    NavbarComponent,
    SearchFilterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    DropdownModule,
    SelectButtonModule,
    ReactiveFormsModule,
    AppRoutingModule,
    TableModule,
    PaginatorModule,
    CalendarModule,
    InputTextModule,
    SelectModule,
    DatePickerModule,
    BrowserAnimationsModule,

  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
