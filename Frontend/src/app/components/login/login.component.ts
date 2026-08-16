import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';
  error = signal('');
  loading = signal(false);
  sessionExpiredMsg = signal('');

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['sessionExpired'] === 'true') {
        this.sessionExpiredMsg.set('Your session has expired. Please sign in again.');
      }
    });
  }

  submit() {
    if (!this.username || !this.password) { this.error.set('Username and password are required'); return; }
    this.loading.set(true); this.error.set(''); this.sessionExpiredMsg.set('');
    this.authService.login(this.username, this.password).subscribe({
      next: () => { this.loading.set(false); this.router.navigate(['/create-customer']); },
      error: (err) => { this.error.set(err?.error?.message || 'Invalid username or password'); this.loading.set(false); }
    });
  }
}
