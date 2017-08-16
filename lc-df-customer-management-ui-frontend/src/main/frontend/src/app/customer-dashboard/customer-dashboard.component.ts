

import { Component, OnInit } from '@angular/core';

import { Customer } from '../customer';
import { CustomerService } from '../customer-service/customer.service';

@Component({
  selector: 'customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: [ './customer-dashboard.component.css' ]
})

export class CustomerDashboardComponent implements OnInit {

  customers: Customer[] = [];

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomers().then(customers =>  this.customers = customers);
  }
}

