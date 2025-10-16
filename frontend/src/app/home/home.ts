import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../../model/User.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home implements OnInit {
  user: User | null = null;
  text = '<h1> life </h1>';
  constructor(private router: Router) {}
  click() {
    alert('clicl');
  }

  async ngOnInit(): Promise<void> {
    console.log('ngOnInit triggered');

    const token = localStorage.getItem('token');
    if (!token) {
      console.warn('No token found — skipping fetch');
      return;
      this.router.navigate(['/login']);
    }

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
    } catch (error) {
      console.error('Fetch error:', error);
    }
  }
}
