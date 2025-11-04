import { Component, EventEmitter, Input, Output } from '@angular/core';

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
  banUser() {
    this.ban.emit(this.u.uuid);
  }

  DeleteUser() {
    this.delete.emit(this.u.uuid);
  }
}
