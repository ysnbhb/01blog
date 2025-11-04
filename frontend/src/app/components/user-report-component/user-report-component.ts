import { Component, EventEmitter, Output } from '@angular/core';
import { UserReportView } from '../user-report-view/user-report-view';
import { Admin } from '../../services/admin';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../services/user';
import { DeleteUserPopup } from '../delete-user-popup/delete-user-popup';
import { BanPopup } from '../ban-popup/ban-popup';

@Component({
  selector: 'app-user-report-component',
  imports: [UserReportView, FormsModule, DeleteUserPopup, BanPopup],
  templateUrl: './user-report-component.html',
  styleUrl: './user-report-component.css',
})
export class UserReportComponent {
  constructor(private admin: Admin, private router: Router, private user: User) {}
  users: any[] = [];
  filteredUsers: any[] = [];
  uuid!: string;
  searchQuery = '';
  deleteUser!: boolean;
  isLoading = false;

  ngOnInit() {
    this.loadReportedUsers();
  }

  loadReportedUsers() {
    this.isLoading = true;
    this.admin.getReportUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.filteredUsers = this.users;
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.isLoading = false;
  }

  viewReports(uuid: string) {
    this.router.navigate(['/admin/reports/user', uuid]);
  }

  dUser(uuid: string) {
    this.deleteUser = true;
    this.uuid = uuid;
  }
  setUuid(uuid: string) {
    this.uuid = uuid;
  }

  confirm(action: boolean) {
    if (action) console.log(this.uuid);

    this.admin.banUser(this.uuid).subscribe({
      next: (ban) => {
        this.users = this.users.map((user) => {
          if (user.uuid == this.uuid) {
            return { ...user, status: !ban ? 'ACTIVE' : 'BANNED' };
          }
          return user;
        });
        this.uuid = '';
      },
      error: (err) => {
        this.uuid = '';
      },
    });
  }

  confirmDelete(action: boolean) {
    if (action) {
      this.user.deleteUser(this.uuid).subscribe({
        next: () => {
          this.users = this.users.filter((user) => user.uuid !== this.uuid);
          this.uuid = '';
          this.deleteUser = false;
        },
        error: (err) => {
          console.log(err);
          this.uuid = '';
          this.deleteUser = false;
        },
      });
    } else {
      this.uuid = '';
      this.deleteUser = false;
    }
  }

  search() {
    if (!this.searchQuery.trim()) {
      this.users = this.filteredUsers;
    } else {
      this.users = this.users.filter((user) => {
        return (
          (user.name + ' ' + user.lastanme).indexOf(this.searchQuery) >= 0 ||
          user.username.indexOf(this.searchQuery) >= 0
        );
      });
    }
  }
}
