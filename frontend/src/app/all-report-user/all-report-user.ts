import { Component, Input } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from '../services/admin';
import { FormatDatePipe } from '../pipe/format-date-pipe';

@Component({
  selector: 'app-all-report-user',
  imports: [NavBar , FormatDatePipe],
  templateUrl: './all-report-user.html',
  styleUrl: './all-report-user.css',
})
export class AllReportUser {
  reports: any[] = [];

  user!: UserRes;

  isLoading = false;

  constructor(
    private userget: User,
    private rout: Router,
    private admin: Admin,
    private route: ActivatedRoute
  ) {}
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

    this.route.paramMap.subscribe((params) => {
      const uuid = params.get('uuid');
      const postid = params.get('postid');
      console.log(postid);
      
      if (uuid) {
        this.admin.gitReasone(uuid).subscribe({
          next: (data) => {
            this.reports = data;
          },
        });
      } else if (postid) {
        this.admin.gitReasonePost(postid).subscribe({
          next: (data) => {
            this.reports = data;
          },
        });
      }
    });
  }
}
