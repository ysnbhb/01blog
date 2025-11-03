import { Component } from '@angular/core';
import { DashboardHeader } from '../components/dashboard-header/dashboard-header';
import { NavBar } from '../components/nav-bar/nav-bar';
import { User } from '../services/user';
import { Router } from '@angular/router';
import { UserRes } from '../../model/User.model';
import { Admin } from '../services/admin';
import { UserComponent } from "../components/user-component/user-component";

@Component({
  selector: 'app-users',
  imports: [DashboardHeader, NavBar, UserComponent],
  templateUrl: './users.html',
  styleUrl: './users.css',
})
export class Users {
  user!: UserRes;
  status: any;
  isRefreshing = true;

  refreshStats() {
    this.isRefreshing = true;
    this.getStust();
  }
  constructor(private userget: User, private rout: Router, private admin: Admin) {}
  ngOnInit(): void {
    this.userget.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
        if (this.user.role != 'ADMIN') {
          this.rout.navigate(['/']);
        }
      },
      error: (_) => {
        this.rout.navigate(['/login']);
        console.error('Error fetching user data');
      },
    });

    this.getStust();
  }

  getStust() {
    this.admin.getDashboardStatus().subscribe({
      next: (data: any) => {
        this.status = data;
        this.isRefreshing = false;
      },
      error: (_) => {
        console.error('Error fetching admin data');
        this.isRefreshing = false;
      },
    });
  }
}
