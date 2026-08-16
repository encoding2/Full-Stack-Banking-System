import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-acc-summary',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './acc-summary.component.html',
  styleUrl: './acc-summary.component.css'
})
export class AccSummaryComponent {
  accountNumber: string = '';
  result = signal<any>(null);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) {}

  search() {
    if (!this.accountNumber.trim()) { this.error.set('Please enter an account number'); return; }
    this.loading.set(true); this.result.set(null); this.error.set('');
    this.customerService.getAccountSummary(this.accountNumber.trim()).subscribe({
      next: (res) => { this.result.set(res); this.loading.set(false); },
      error: (err) => { this.error.set(err?.error?.message || 'Account not found'); this.loading.set(false); }
    });
  }

  reset() { this.accountNumber = ''; this.result.set(null); this.error.set(''); }
}
