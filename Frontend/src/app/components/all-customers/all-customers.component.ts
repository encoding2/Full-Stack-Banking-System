import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-all-customers',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './all-customers.component.html',
  styleUrl: './all-customers.component.css'
})
export class AllCustomersComponent implements OnInit {
  customers = signal<any[]>([]);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) {}

  ngOnInit() { this.load(); }

  load() {
    this.loading.set(true); this.error.set('');
    this.customerService.getAllCustomers().subscribe({
      next: (res) => { this.customers.set(res.data || res || []); this.loading.set(false); },
      error: (err) => { this.error.set(err?.error?.message || 'Failed to load customers'); this.loading.set(false); }
    });
  }
}
