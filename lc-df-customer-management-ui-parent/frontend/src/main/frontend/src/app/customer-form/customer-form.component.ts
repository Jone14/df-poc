import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { CustomerService } from '../customer-service/customer.service';

import { Customer } from '../customer';

@Component({
  selector: 'customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent implements OnInit {

  customer : Customer;
  //model = new Customer('123', 'Joseph', 'Panddian', 'Chennai', 'India', '600041', 'jonejsph@gmail.com', '9003252079', 'ACTIVE', 'GOLD');
  model = new Customer('', '', '', '', '', '', '', '', '', '');
  submitted = false;

  constructor(private customerService: CustomerService,
              private route: ActivatedRoute,
              private location: Location
  ) { }

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.customerService.getCustomer(params.get('id')))
      .subscribe(customer => this.customer = customer);
  }

  onSubmit() {
    this.submitted = true;

  }

  newCustomer() {
    this.model = new Customer('', '', '', '', '', '', '', '', '', '');
  }

  goBack(): void {
    this.location.back();
  }

  addNewCustomer(customerObject){
    delete customerObject.customerId;
    this.customerService.create(customerObject).then(() => this.goBack());
    alert("Customer Added Successfully !");
  }

}
