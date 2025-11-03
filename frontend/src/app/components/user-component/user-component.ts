import { Component, Input, OnInit } from '@angular/core';
import { UserRes } from '../../../model/User.model';
import { Admin } from '../../services/admin';
import { UserView } from '../user-view/user-view';
import { FormsModule } from '@angular/forms';
import { User } from '../../services/user';

@Component({
  selector: 'app-user-component',
  imports: [UserView, FormsModule],
  templateUrl: './user-component.html',
  styleUrl: './user-component.css',
})
export class UserComponent implements OnInit {
  users: UserRes[] = [];
  copyUsers: UserRes[] = [];
  searchQuery = '';
  pageSize = 6;
  totalPages = 1;
  @Input() totalUser = 0;
  currentPage = 1;
  isLoading = true;
  constructor(private admin: Admin, private user: User) {}

  ngOnInit(): void {
    this.getUsers();
    this.totalPages = this.totalUser / this.pageSize;
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

  setPage(currentPage: number) {
    this.currentPage = currentPage;
    // console.log(currentPage);

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
