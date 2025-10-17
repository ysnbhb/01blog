import { Component, OnInit } from '@angular/core';
import { LoginNav } from '../components/login-nav/login-nav';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../../model/User.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [LoginNav, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
})
export class Register implements OnInit {
  user!: User;
  selectedFile!: File;
  error!: string;
  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }
  constructor(private router: Router) {}
  async sumbet(val: NgForm) {
    let data = new FormData();
    Object.entries(val.value).forEach(([key, val]) => {
      data.append(key, val as string);
    });
    if (this.selectedFile) {
      data.append('photo', this.selectedFile);
    }

    let res = await fetch('http://localhost:8080/register', { method: 'POST', body: data });
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
        console.error('Fetch error:', error);
      }
    }
  }
}
