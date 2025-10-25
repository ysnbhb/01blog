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
  selectedFile!: File | null;
  error!: string;
  previewUrl!: string | null;
  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files[0]) {
      const file = input.files[0];
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        this.previewUrl = e.target?.result as string;
      };
      reader.readAsDataURL(file);
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
      this.router.navigate(['/']);
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
          return;
        }

        this.user = await res.json();
        console.log('User loaded:', this.user);
        this.router.navigate(['/']);
      } catch (error) {}
    }
  }

  removeFile(): void {
    this.selectedFile = null;
    this.previewUrl = null;
    const fileInput = document.getElementById('image') as HTMLInputElement;
    if (fileInput) {
      fileInput.value = '';
    }
  }
}
