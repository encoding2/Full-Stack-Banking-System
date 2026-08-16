import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-create-account',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent {
  customerId: number | null = null;
  form = { accountNumber: '', balance: '', accountTypeId: '' };
  result = signal<any>(null);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) {}

  submit() {
    if (!this.customerId) { this.error.set('Customer ID is required'); return; }
    this.loading.set(true); this.result.set(null); this.error.set('');
    this.customerService.createAccount(this.customerId, this.form).subscribe({
      next: (res) => { this.result.set(res); this.loading.set(false); },
      error: (err) => { this.error.set(err?.error?.message || 'Failed to create account'); this.loading.set(false); }
    });
  }

  reset() { this.form = { accountNumber: '', balance: '', accountTypeId: '' }; this.customerId = null; this.result.set(null); this.error.set(''); }
}
