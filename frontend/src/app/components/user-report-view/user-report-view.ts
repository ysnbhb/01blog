import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-report-view',
  imports: [],
  templateUrl: './user-report-view.html',
  styleUrl: './user-report-view.css',
})
export class UserReportView {
  @Input() u!: any;
  @Output() ban = new EventEmitter<string>();
  @Output() delete = new EventEmitter<string>();
  constructor(private router: Router) {}
  banUser() {
    this.ban.emit(this.u.uuid);
  }

  DeleteUser() {
    this.delete.emit(this.u.uuid);
  }

  view() {
    this.router.navigate(['admin/reports/users', this.u.uuid]);
  }
}
