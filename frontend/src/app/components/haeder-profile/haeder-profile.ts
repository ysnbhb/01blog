import { Component, Input } from '@angular/core';
import { UserRes } from '../../../model/User.model';
import { User } from '../../services/user';
import { ErrorShow } from '../error-show/error-show';
import { ReportUser } from '../report-user/report-user';
import { SuccuesShow } from '../succues-show/succues-show';
import { Admin } from '../../services/admin';
import { Router } from '@angular/router';

@Component({
  selector: 'app-haeder-profile',
  imports: [ErrorShow, ReportUser, SuccuesShow],
  templateUrl: './haeder-profile.html',
  styleUrl: './haeder-profile.css',
})
export class HaederProfile {
  @Input() user!: UserRes;
  @Input() role!: string;
  isLoading = false;
  error = '';
  succuse = '';
  report = false;
  constructor(private useser: User, private router: Router) {}
  setError(error: string) {
    this.error = error;
    setTimeout(() => {
      this.error = '';
    }, 2000);
  }
  setReport() {
    this.report = true;
  }

  setSuccus(succuse: string) {
    this.succuse = succuse;
    setTimeout(() => {
      this.succuse = '';
    }, 2000);
  }
  reported(_: boolean) {
    this.report = false;
  }
  toggleFollow() {
    this.isLoading = true;
    this.useser.follow(this.user.uuid).subscribe({
      next: (data: any) => {
        this.user.followers = data.followor;
        this.user.following = data.follwining;
        this.user.hasCon = data.isfollow;
        this.isLoading = false;
      },
      error: (err) => {
        this.setError(err.error || 'fieled to send connetion');
        this.isLoading = false;
      },
    });
  }
}
