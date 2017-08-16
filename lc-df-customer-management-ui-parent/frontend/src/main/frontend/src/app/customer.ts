export class Customer {

  constructor(
    public customerId: string,
    public firstName: string,
    public lastName: string,
    public addressLine1: string,
    public addressLine2: string,
    public postalCode: string,
    public emailId: string,
    public phoneNumber: string,
    public status: string,
    public category: string
  ) {  }

}
