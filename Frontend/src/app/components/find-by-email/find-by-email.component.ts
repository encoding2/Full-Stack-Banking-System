import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-find-by-email',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './find-by-email.component.html',
  styleUrl: './find-by-email.component.css'
})
export class FindByEmailComponent {
  email: string = '';
  result = signal<any>(null);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) {}

  search() {
    if (!this.email) { this.error.set('Please enter an email address'); return; }
    this.loading.set(true); this.result.set(null); this.error.set('');
    this.customerService.findByEmail(this.email).subscribe({
      next: (res) => { this.result.set(res); this.loading.set(false); },
      error: (err) => { this.error.set(err?.error?.message || 'No customer found with this email'); this.loading.set(false); }
    });
  }
}
