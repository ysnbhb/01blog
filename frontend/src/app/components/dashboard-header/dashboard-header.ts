import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-header',
  imports: [],
  templateUrl: './dashboard-header.html',
  styleUrl: './dashboard-header.css',
})
export class DashboardHeader {
  // @Input() user: any;

  @Input() stats: any;

  @Output() refrech = new EventEmitter();

  @Input() isRefreshing = false;

  constructor(private router: Router) {}

  refreshStats() {
    this.refrech.emit();
  }

  goToUsers() {
    this.router.navigate(['/admin/users']);
  }

  goToReportedUsers() {
    this.router.navigate(['/admin/reports/users']);
  }

  goToReportedPosts() {
    this.router.navigate(['/admin/reports/posts']);
  }
}
