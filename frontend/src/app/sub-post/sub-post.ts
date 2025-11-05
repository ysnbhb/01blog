import { Component, OnInit } from '@angular/core';
import { PostsContainer } from '../components/posts-container/posts-container';
import { UserRes } from '../../model/User.model';
import { Router } from '@angular/router';
import { User } from '../services/user';
import { NavBar } from "../components/nav-bar/nav-bar";

@Component({
  selector: 'app-sub-post',
  imports: [PostsContainer, NavBar],
  templateUrl: './sub-post.html',
  styleUrl: './sub-post.css',
})
export class SubPost implements OnInit {
  user!: UserRes;
  constructor(private rout: Router, private userser: User) {}
  ngOnInit(): void {
    this.userser.getUser().subscribe({
      next: (data: UserRes) => {
        this.user = data;
      },
      error: (_) => {
        this.rout.navigate(['/login']);
      },
    });
  }
}
