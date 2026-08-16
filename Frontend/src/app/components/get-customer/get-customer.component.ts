import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-get-customer',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './get-customer.component.html',
  styleUrl: './get-customer.component.css'
})
export class GetCustomerComponent {
  customerId: number | null = null;
  result = signal<any>(null);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) {}

  search() {
    if (!this.customerId) { this.error.set('Please enter a customer ID'); return; }
    this.loading.set(true); this.result.set(null); this.error.set('');
    this.customerService.getCustomerById(this.customerId).subscribe({
      next: (res) => { this.result.set(res); this.loading.set(false); },
      error: (err) => { this.error.set(err?.error?.message || 'Customer not found'); this.loading.set(false); }
    });
  }

  reset() { this.customerId = null; this.result.set(null); this.error.set(''); }
}
