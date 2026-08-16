import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-find-by-phone',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './find-by-phone.component.html',
  styleUrl: './find-by-phone.component.css'
})
export class FindByPhoneComponent {
  phone: string = '';
  result = signal<any>(null);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) {}

  search() {
    if (!this.phone.trim()) { this.error.set('Please enter a phone number'); return; }
    this.loading.set(true); this.result.set(null); this.error.set('');
    this.customerService.findByPhone(this.phone.trim()).subscribe({
      next: (res) => { this.result.set(res); this.loading.set(false); },
      error: (err) => { this.error.set(err?.error?.message || 'No customer found with this phone number'); this.loading.set(false); }
    });
  }

  reset() { this.phone = ''; this.result.set(null); this.error.set(''); }
}
