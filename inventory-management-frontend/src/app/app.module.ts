import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgApexchartsModule } from 'ng-apexcharts';
import { NgbdModalContent, ProductsComponent } from './products/products.component';
import { SearchNamePipe } from './pipes/search-name.pipe';
import { FormsModule } from '@angular/forms'
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { EditModalComponent } from './products/edit-modal/edit-modal.component';
import { DeleteModalComponent } from './products/delete-modal/delete-modal.component';
import { AddModalComponent } from './products/add-modal/add-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    ProductsComponent,
    NgbdModalContent,
    SearchNamePipe,
    EditModalComponent,
    DeleteModalComponent,
    AddModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgApexchartsModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { 

  }

