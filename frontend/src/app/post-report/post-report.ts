import { Component } from '@angular/core';
import { User } from '../services/user';
import { Router } from '@angular/router';
import { Admin } from '../services/admin';
import { UserRes } from '../../model/User.model';
import { NavBar } from "../components/nav-bar/nav-bar";
import { DashboardHeader } from "../components/dashboard-header/dashboard-header";
import { PostReportComponent } from "../components/post-report-component/post-report-component";

interface ReportedPost {
  postId: number;
  username: string;
  uuid: string;
  name: string;
  lastName: string;
  userPhoto: string;
  content: string;
  title: string;
  reportCount: number;
  isBanningUser?: boolean;
}

@Component({
  selector: 'app-post-report',
  imports: [NavBar, DashboardHeader, PostReportComponent],
  templateUrl: './post-report.html',
  styleUrl: './post-report.css',
})
export class PostReport {
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
