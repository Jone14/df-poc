import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { CustomerRoutingModule } from './customer-routing/customer-routing.module';

import { AppComponent } from './app.component';
import { CustomerFormComponent } from './customer-form/customer-form.component';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';
import { CustomerDetailComponent } from './customer-detail/customer-detail.component';
import { CustomerSearchComponent } from './customer-search/customer-search.component';
import { CustomerService } from './customer-service/customer.service';
import { CustomerComponent } from './customer/customer.component';


@NgModule({
  declarations: [
    AppComponent,
    CustomerFormComponent,
    CustomerDashboardComponent,
    CustomerDetailComponent,
    CustomerSearchComponent,
    CustomerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CustomerRoutingModule
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
