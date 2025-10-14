import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../../model/User.model';

@Component({
  selector: 'app-home',
  standalone: true,   // ✅ required for loadComponent
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home implements OnInit {
  user: User | null = null;

  async ngOnInit(): Promise<void> {
    console.log('ngOnInit triggered');

    const token = localStorage.getItem('token');
    if (!token) {
      console.warn('No token found — skipping fetch');
      return;
    }

    try {
      const res = await fetch('http://localhost:8080/api/me', {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });

      if (!res.ok) {
        console.error('Failed to fetch user info:', res.statusText);
        return;
      }

      this.user = await res.json();
      console.log('User loaded:', this.user);

    } catch (error) {
      console.error('Fetch error:', error);
    }
  }
}
