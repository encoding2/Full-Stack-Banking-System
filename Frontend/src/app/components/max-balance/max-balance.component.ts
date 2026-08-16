import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-max-balance',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './max-balance.component.html',
  styleUrl: './max-balance.component.css'
})
export class MaxBalanceComponent implements OnInit {
  customers = signal<any[]>([]);
  maxBalance = signal<number | null>(null);
  error = signal('');
  loading = signal(false);

  constructor(private customerService: CustomerService) { }

  ngOnInit() { this.load(); }

  load() {
    this.loading.set(true); this.error.set(''); this.customers.set([]); this.maxBalance.set(null);
    this.customerService.getCustomersWithMaxBalance().subscribe({
      next: (res) => {
        const customers = res.data || res || [];
        this.customers.set(customers);
        if (customers.length > 0) {
          const balances = customers.flatMap((c: any) =>
            (c.accounts || []).map((a: any) => parseFloat(a.balance))
          );
          this.maxBalance.set(balances.length > 0 ? Math.max(...balances) : null);
        }
        this.loading.set(false);
      },
      error: (err) => { this.error.set(err?.error?.message || 'Failed to load data'); this.loading.set(false); }
    });
  }
}
