import { Component, Input, OnInit } from '@angular/core';
import { UserRes } from '../../../model/User.model';
import { Admin } from '../../services/admin';
import { UserView } from '../user-view/user-view';
import { FormsModule } from '@angular/forms';
import { User } from '../../services/user';
import { BanPopup } from '../ban-popup/ban-popup';
import { DeleteUserPopup } from '../delete-user-popup/delete-user-popup';

@Component({
  selector: 'app-user-component',
  imports: [UserView, FormsModule, BanPopup, DeleteUserPopup],
  templateUrl: './user-component.html',
  styleUrl: './user-component.css',
})
export class UserComponent implements OnInit {
  users: UserRes[] = [];
  copyUsers: UserRes[] = [];
  searchQuery = '';
  deleteUser!: boolean;
  pageSize = 6;
  totalPages = 1;
  uuid!: string;
  @Input() totalUser = 0;
  currentPage = 1;
  isLoading = true;
  constructor(private admin: Admin, private user: User) {}

  ngOnInit(): void {
    this.getUsers();
    this.totalPages = this.totalUser / this.pageSize;
  }
  dUser(uuid: string) {
    this.deleteUser = true;
    this.uuid = uuid;
  }
  getUsers() {
    this.admin.getUsers(this.currentPage - 1, this.pageSize).subscribe({
      next: (data) => {
        this.users = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.isLoading = false;
        console.log(err);
      },
    });
  }

  setUuid(uuid: string) {
    this.uuid = uuid;
  }

  confirm(action: boolean) {
    if (action)
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

  setPage(currentPage: number) {
    this.currentPage = currentPage;
    this.getUsers();
  }

  filterUsers() {
    if (this.searchQuery.trim()) {
      this.isLoading = true;
      this.user.searchUsers(this.searchQuery).subscribe({
        next: (data) => {
          this.copyUsers = this.users;
          this.users = data;
          this.isLoading = false;
        },
        error: () => {
          this.isLoading = false;
          this.users = this.copyUsers;
        },
      });
    } else {
      this.users = this.copyUsers;
    }
  }
}
