import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { CreateAccountComponent } from './components/create-account/create-account.component';
import { GetCustomerComponent } from './components/get-customer/get-customer.component';
import { AllCustomersComponent } from './components/all-customers/all-customers.component';
import { FindByEmailComponent } from './components/find-by-email/find-by-email.component';
import { AccSummaryComponent } from './components/acc-summary/acc-summary.component';
import { MaxBalanceComponent } from './components/max-balance/max-balance.component';
import { FindByPhoneComponent } from './components/find-by-phone/find-by-phone.component';
import { authGuard } from './guards/auth.guard';
import { loginRedirectGuard } from './guards/login-redirect.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [loginRedirectGuard] },
  { path: '', redirectTo: 'create-customer', pathMatch: 'full' },
  { path: 'create-customer', component: CreateCustomerComponent, canActivate: [authGuard] },
  { path: 'create-account', component: CreateAccountComponent, canActivate: [authGuard] },
  { path: 'get-customer', component: GetCustomerComponent, canActivate: [authGuard] },
  { path: 'all-customers', component: AllCustomersComponent, canActivate: [authGuard] },
  { path: 'find-by-email', component: FindByEmailComponent, canActivate: [authGuard] },
  { path: 'acc-summary', component: AccSummaryComponent, canActivate: [authGuard] },
  { path: 'max-balance', component: MaxBalanceComponent, canActivate: [authGuard] },
  { path: 'find-by-phone', component: FindByPhoneComponent, canActivate: [authGuard] },
];
