import { Component, OnInit } from '@angular/core';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { Router } from '@angular/router';
import { Admin } from '../services/admin';
import { NavBar } from "../components/nav-bar/nav-bar";
import { DashboardHeader } from "../components/dashboard-header/dashboard-header";
import { UserReportComponent } from "../components/user-report-component/user-report-component";

interface ReportedUser {
  uuid: string;
  username: string;
  name: string;
  last_name: string;
  url_photo: string;
  reportCount: number;
  isBanned: boolean;
  isBanning?: boolean;
}

@Component({
  selector: 'app-user-report',
  imports: [NavBar, DashboardHeader, UserReportComponent],
  templateUrl: './user-report.html',
  styleUrl: './user-report.css',
})
export class ReportedUsersComponent implements OnInit {
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
