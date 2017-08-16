import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Customer } from '../customer';

import * as AppConst from '../app.constants';

@Injectable()
export class CustomerService {

  private headers = new Headers({ 'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'});

  constructor(private http: Http) { }

  getCustomers(): Promise<Customer[]> {
    return this.http.get(AppConst.getAllCustomerUrl)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

  getCustomer(id: string): Promise<Customer> {

    const url = `${AppConst.getCustomersByIdUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${AppConst.deleteCustomerByIdUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  create(customer: Customer): Promise<void> {
    delete customer.customerId;
    return this.http
      .post(AppConst.createCustomerUrl, JSON.stringify(customer), {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(customer: Customer): Promise<Customer> {
    const url = `${AppConst.updateCustomerByIdUrl}/${customer.customerId}`;
    return this.http.post(url, JSON.stringify(customer), {headers: this.headers})
      .toPromise()
      .then(() => customer)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }



  getCustomerByFirstName(firstName: string): Promise<Customer[]> {

    const url = `${AppConst.getCustomersByFirstNameUrl}/${firstName}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

  getCustomerByLastName(lastName: string): Promise<Customer[]> {

    const url = `${AppConst.getCustomersByLastNameUrl}/${lastName}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

  getCustomerByPostalCode(postalCode: string): Promise<Customer[]> {

    const url = `${AppConst.getCustomersByPostalCodeUrl}/${postalCode}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

  getCustomerByEmail(email: string): Promise<Customer[]> {

    const url = `${AppConst.getCustomersByEmailIdUrl}/${email}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

  getCustomerByPhone(phone: string): Promise<Customer[]> {

    const url = `${AppConst.getCustomersByPhoneNoUrl}/${phone}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

  getCustomerByStatus(status: string): Promise<Customer[]> {

    const url = `${AppConst.getCustomersByStatus}/${status}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

  getCustomerByCategory(category: string): Promise<Customer[]> {

    const url = `${AppConst.getCustomersByCategory}/${category}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Customer[])
      .catch(this.handleError);
  }

}
