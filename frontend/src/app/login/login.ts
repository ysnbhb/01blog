import { Component, OnInit } from '@angular/core';
import { LoginNav } from '../components/login-nav/login-nav';
import { Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../../model/User.model';

@Component({
  selector: 'app-login',
  imports: [LoginNav, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit {
  error!: string;
  user!: User;
  constructor(private router: Router) {}
  async sumbet(val: NgForm) {
    let res = await fetch('http://localhost:8080/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },

      body: JSON.stringify(val.value),
    });
    let json = await res.json();
    if (res.ok) {
      localStorage.setItem('token', json.token);
    } else {
      this.error = json.error;
    }
  }

  async ngOnInit(): Promise<void> {
    console.log('ngOnInit triggered');

    const token = localStorage.getItem('token');
    if (token) {
      try {
        const res = await fetch('http://localhost:8080/api/me', {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });
        if (!res.ok) {
          console.error('Failed to fetch user info:', res.statusText);
          this.router.navigate(['/login']);
          return;
        }

        this.user = await res.json();
        console.log('User loaded:', this.user);
        this.router.navigate(['/']);
      } catch (error) {
        // console.error('Fetch error:', error);
      }
    }
  }
}
