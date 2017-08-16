import 'rxjs/add/operator/switchMap';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { Customer} from '../customer';
import { CustomerService} from '../customer-service/customer.service';

@Component({
  selector: 'app-customer-search',
  templateUrl: './customer-search.component.html',
  styleUrls: ['./customer-search.component.css']
})
export class CustomerSearchComponent implements OnInit {

  customers: Customer[] = [];

  submitted = false;

  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit() {
  }

  searchFilters = ["First Name", "Last Name", "Postal Code", "Email", "Phone Number", "Status", "Category"];


  model = {
    searchFilter:null,
    searchString:null
  };

  setSearchFilter(value) {
    var searchPlan = this.searchFilters.find(p => p == value);
    if(searchPlan) { this.model.searchFilter = searchPlan; }
  }

  goBack(): void {
    this.location.back();
  }

  searchCustomer(model){
    this.submitted = true;
    if(model.searchFilter==="First Name"){
      this.route.paramMap
        .switchMap((params: ParamMap) => this.customerService.getCustomerByFirstName(model.searchString))
        .subscribe(customers => this.customers = customers);
    }else if(model.searchFilter==="Last Name"){
      this.route.paramMap
        .switchMap((params: ParamMap) => this.customerService.getCustomerByLastName(model.searchString))
        .subscribe(customers => this.customers = customers);
    }else if(model.searchFilter==="Postal Code"){
      this.route.paramMap
        .switchMap((params: ParamMap) => this.customerService.getCustomerByPostalCode(model.searchString))
        .subscribe(customers => this.customers = customers);
    }else if(model.searchFilter==="Email"){
      this.route.paramMap
        .switchMap((params: ParamMap) => this.customerService.getCustomerByEmail(model.searchString))
        .subscribe(customers => this.customers = customers);
    }else if(model.searchFilter==="Phone Number"){
      this.route.paramMap
        .switchMap((params: ParamMap) => this.customerService.getCustomerByPhone(model.searchString))
        .subscribe(customers => this.customers = customers);
    }else if(model.searchFilter==="Status"){
      this.route.paramMap
        .switchMap((params: ParamMap) => this.customerService.getCustomerByStatus(model.searchString))
        .subscribe(customers => this.customers = customers);
    }else if(model.searchFilter==="Category"){
      this.route.paramMap
        .switchMap((params: ParamMap) => this.customerService.getCustomerByCategory(model.searchString))
        .subscribe(customers => this.customers = customers);
    }

  }
}
