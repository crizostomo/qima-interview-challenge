import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';

  login(username: string, password: string): boolean {
    if (username === 'user' && password === 'password') {
      const token = btoa(`${username}:${password}`);
      localStorage.setItem('authToken', token);
      localStorage.setItem('isAuthenticated', 'true');
      this.isAuthenticated = true;
      return true;
    }
    return false;
  }

  logout(): void {
    localStorage.removeItem('authToken');
    localStorage.removeItem('isAuthenticated');
    this.isAuthenticated = false;
    this.router.navigate(['/login']);
  }

  constructor(private router: Router) {}
}
