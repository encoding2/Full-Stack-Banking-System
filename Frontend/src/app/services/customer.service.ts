import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CustomerService {
  private base = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  createCustomer(data: any): Observable<any> {
    return this.http.post(`${this.base}/customers`, data);
  }

  createAccount(customerId: number, data: any): Observable<any> {
    return this.http.post(`${this.base}/customers/${customerId}/accounts`, data);
  }

  getCustomerById(id: number): Observable<any> {
    return this.http.get(`${this.base}/customers/${id}`);
  }

  getAllCustomers(): Observable<any> {
    return this.http.get(`${this.base}/customers`);
  }

  findByEmail(email: string): Observable<any> {
    return this.http.get(`${this.base}/customers/email/${email}`);
  }

  getAccountSummary(accountNumber: string): Observable<any> {
    return this.http.get(`${this.base}/customers/account-summary/${accountNumber}`);
  }

  getCustomersWithMaxBalance(): Observable<any> {
    return this.http.get(`${this.base}/customers/max-balance`);
  }

  findByPhone(phone: string): Observable<any> {
    return this.http.get(`${this.base}/customers/phone/${phone}`);
  }
}
