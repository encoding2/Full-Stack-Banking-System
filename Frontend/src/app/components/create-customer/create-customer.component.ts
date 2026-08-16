import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-create-customer',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './create-customer.component.html',
  styleUrl: './create-customer.component.css'
})
export class CreateCustomerComponent {
  form = { name: '', email: '', phone: '', dateOfBirth: '', aadhaar: '', pan: '', kycStatus: true };
  result = signal<any>(null);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) { }

  submit() {
    this.loading.set(true);
    this.result.set(null);
    this.error.set('');
    this.customerService.createCustomer(this.form).subscribe({
      next: (res) => { this.result.set(res); this.loading.set(false); },
      error: (err) => { this.error.set(err?.error?.message || 'Failed to create customer'); this.loading.set(false); }
    });
  }

  reset() {
    this.form = { name: '', email: '', phone: '', dateOfBirth: '', aadhaar: '', pan: '', kycStatus: true };
    this.result.set(null);
    this.error.set('');
  }
}
