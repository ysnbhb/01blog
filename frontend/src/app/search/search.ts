import { Component, OnInit } from '@angular/core';
import { NavBar } from '../components/nav-bar/nav-bar';
import { UserRes } from '../../model/User.model';
import { User } from '../services/user';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-search',
  imports: [NavBar, RouterLink , FormsModule],
  templateUrl: './search.html',
  styleUrl: './search.css',
})
export class Search implements OnInit {
  user!: UserRes;
  users: UserRes[] = [];
  searchQuery = '';
  isSearching = false;
  constructor(private userService: User, private router: Router) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
      },
      error: (_) => {
        this.router.navigate(['/login']);
      },
    });
  }

  search() {
    if (!this.searchQuery.trim()) return;
    this.isSearching = true;
    this.userService.searchUsers(this.searchQuery).subscribe({
      next: (data) => {
        this.users = data.map((u) => ({ ...u, isLoading: false }));
        this.isSearching = false;
      },
      error: () => {
        this.isSearching = false;
        alert('Search failed');
      },
    });
  }
}
